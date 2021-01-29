/*
 * Copyright (c) 2011, 2020, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


package org.graalvm.compiler.nodes.extended;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_2;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_1;

import org.graalvm.compiler.core.common.type.PrimitiveStamp;
import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.Canonicalizable;
import org.graalvm.compiler.graph.spi.CanonicalizerTool;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ConstantNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.calc.ReinterpretNode;
import org.graalvm.compiler.nodes.java.LoadFieldNode;
import org.graalvm.compiler.nodes.spi.Lowerable;
import org.graalvm.compiler.nodes.spi.Virtualizable;
import org.graalvm.compiler.nodes.spi.VirtualizerTool;
import org.graalvm.compiler.nodes.type.StampTool;
import org.graalvm.compiler.nodes.virtual.VirtualArrayNode;
import org.graalvm.compiler.nodes.virtual.VirtualObjectNode;
import jdk.internal.vm.compiler.word.LocationIdentity;

import jdk.vm.ci.meta.Assumptions;
import jdk.vm.ci.meta.Constant;
import jdk.vm.ci.meta.JavaConstant;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.ResolvedJavaField;
import jdk.vm.ci.meta.ResolvedJavaType;

/**
 * Load of a value from a location specified as an offset relative to an object. No null check is
 * performed before the load.
 */
@NodeInfo(cycles = CYCLES_2, size = SIZE_1)
public class RawLoadNode extends UnsafeAccessNode implements Lowerable, Virtualizable, Canonicalizable {
    public static final NodeClass<RawLoadNode> TYPE = NodeClass.create(RawLoadNode.class);

    /**
     * This constructor exists for node intrinsics that need a stamp based on {@code accessKind}.
     */
    public RawLoadNode(ValueNode object, ValueNode offset, JavaKind accessKind, LocationIdentity locationIdentity) {
        this(object, offset, accessKind, locationIdentity, false);
    }

    public RawLoadNode(ValueNode object, ValueNode offset, JavaKind accessKind, LocationIdentity locationIdentity, boolean forceAnyLocation) {
        super(TYPE, StampFactory.forKind(accessKind.getStackKind()), object, offset, accessKind, locationIdentity, forceAnyLocation);
    }

    /**
     * This constructor exists for node intrinsics that need a stamp based on the return type of the
     * {@link org.graalvm.compiler.graph.Node.NodeIntrinsic} annotated method.
     */
    public RawLoadNode(@InjectedNodeParameter Stamp stamp, ValueNode object, ValueNode offset, LocationIdentity locationIdentity, JavaKind accessKind) {
        super(TYPE, stamp, object, offset, accessKind, locationIdentity, false);
    }

    protected RawLoadNode(NodeClass<? extends RawLoadNode> c, ValueNode object, ValueNode offset, JavaKind accessKind, LocationIdentity locationIdentity) {
        super(c, StampFactory.forKind(accessKind.getStackKind()), object, offset, accessKind, locationIdentity, false);
    }

    @Override
    public void virtualize(VirtualizerTool tool) {
        ValueNode alias = tool.getAlias(object());
        if (alias instanceof VirtualObjectNode) {
            VirtualObjectNode virtual = (VirtualObjectNode) alias;
            ValueNode offsetValue = tool.getAlias(offset());
            if (offsetValue.isConstant()) {
                long off = offsetValue.asJavaConstant().asLong();
                int entryIndex = virtual.entryIndexForOffset(tool.getMetaAccess(), off, accessKind());

                if (entryIndex != -1) {
                    ValueNode entry = tool.getEntry(virtual, entryIndex);
                    JavaKind entryKind = virtual.entryKind(tool.getMetaAccessExtensionProvider(), entryIndex);

                    if (virtual.isVirtualByteArrayAccess(tool.getMetaAccessExtensionProvider(), accessKind())) {
                        if (virtual.canVirtualizeLargeByteArrayUnsafeRead(entry, entryIndex, accessKind(), tool)) {
                            tool.replaceWith(VirtualArrayNode.virtualizeByteArrayRead(entry, accessKind(), stamp));
                        }
                    } else if (entry.getStackKind() == getStackKind() || entryKind == accessKind()) {

                        if (!(entry.stamp(NodeView.DEFAULT).isCompatible(stamp(NodeView.DEFAULT)))) {
                            if (entry.stamp(NodeView.DEFAULT) instanceof PrimitiveStamp && stamp instanceof PrimitiveStamp) {
                                PrimitiveStamp p1 = (PrimitiveStamp) stamp;
                                PrimitiveStamp p2 = (PrimitiveStamp) entry.stamp(NodeView.DEFAULT);
                                int width1 = p1.getBits();
                                int width2 = p2.getBits();
                                if (width1 == width2) {
                                    Node replacement = ReinterpretNode.create(p2, entry, NodeView.DEFAULT);
                                    tool.replaceWith((ValueNode) replacement);
                                    return;
                                } else {
                                    // different bit width
                                    return;
                                }
                            } else {
                                // cannot reinterpret for arbitrary objects
                                return;
                            }
                        }
                        tool.replaceWith(entry);
                    }
                }
            }
        }
    }

    @Override
    public boolean isVolatile() {
        return false;
    }

    @Override
    public Node canonical(CanonicalizerTool tool) {
        if (!isAnyLocationForced() && getLocationIdentity().isAny()) {
            ValueNode targetObject = object();
            if (offset().isConstant() && targetObject.isConstant() && !targetObject.isNullConstant()) {
                ConstantNode objectConstant = (ConstantNode) targetObject;
                ResolvedJavaType type = StampTool.typeOrNull(objectConstant);
                if (type != null && type.isArray()) {
                    JavaConstant arrayConstant = objectConstant.asJavaConstant();
                    if (arrayConstant != null) {
                        int stableDimension = objectConstant.getStableDimension();
                        if (stableDimension > 0) {
                            NodeView view = NodeView.from(tool);
                            long constantOffset = offset().asJavaConstant().asLong();
                            Constant constant = stamp(view).readConstant(tool.getConstantReflection().getMemoryAccessProvider(), arrayConstant, constantOffset);
                            boolean isDefaultStable = objectConstant.isDefaultStable();
                            if (constant != null && (isDefaultStable || !constant.isDefaultForKind())) {
                                return ConstantNode.forConstant(stamp(view), constant, stableDimension - 1, isDefaultStable, tool.getMetaAccess());
                            }
                        }
                    }
                }
            }
        }
        return super.canonical(tool);
    }

    @Override
    protected ValueNode cloneAsFieldAccess(Assumptions assumptions, ResolvedJavaField field, boolean volatileAccess) {
        return LoadFieldNode.create(assumptions, field.isStatic() ? null : object(), field, volatileAccess);
    }

    @Override
    protected ValueNode cloneAsArrayAccess(ValueNode location, LocationIdentity identity, boolean volatileAccess) {
        if (volatileAccess) {
            return new RawVolatileLoadNode(object(), location, accessKind(), identity);
        }
        return new RawLoadNode(object(), location, accessKind(), identity);
    }

    @NodeIntrinsic
    public static native Object load(Object object, long offset, @ConstantNodeParameter JavaKind kind, @ConstantNodeParameter LocationIdentity locationIdentity);
}
