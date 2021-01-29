/*
 * Copyright (c) 2009, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.java;

import static org.graalvm.compiler.nodeinfo.InputType.Association;
import static org.graalvm.compiler.nodeinfo.InputType.Memory;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.InputType;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.DeoptimizingNode;
import org.graalvm.compiler.nodes.FrameState;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.AbstractMemoryCheckpoint;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;

import jdk.vm.ci.code.BailoutException;

/**
 * The {@code AccessMonitorNode} is the base class of both monitor acquisition and release.
 * <p>
 * The Java bytecode specification allows non-balanced locking. Graal does not handle such cases and
 * throws a {@link BailoutException} instead during graph building.
 */
@NodeInfo(allowedUsageTypes = {Memory})
public abstract class AccessMonitorNode extends AbstractMemoryCheckpoint implements SingleMemoryKill, DeoptimizingNode.DeoptBefore, DeoptimizingNode.DeoptAfter {

    public static final NodeClass<AccessMonitorNode> TYPE = NodeClass.create(AccessMonitorNode.class);
    @OptionalInput(InputType.State) FrameState stateBefore;
    @Input ValueNode object;
    @Input(Association) MonitorIdNode monitorId;

    /**
     * Additional information already loaded from {@link #object} in an early lowering stage to
     * facilitate value numbering and high-level optimizations. The value is VM-dependent.
     */
    @OptionalInput private ValueNode objectData;

    protected AccessMonitorNode(NodeClass<? extends AccessMonitorNode> c, ValueNode object, MonitorIdNode monitorId, boolean biasable) {
        super(c, StampFactory.forVoid());
        this.object = object;
        this.monitorId = monitorId;
        this.biasable = biasable;
    }

    protected AccessMonitorNode(NodeClass<? extends AccessMonitorNode> c, ValueNode object, MonitorIdNode monitorId) {
        this(c, object, monitorId, true);
    }

    @Override
    public boolean canDeoptimize() {
        return true;
    }

    @Override
    public FrameState stateBefore() {
        return stateBefore;
    }

    @Override
    public void setStateBefore(FrameState f) {
        updateUsages(stateBefore, f);
        stateBefore = f;
    }

    public ValueNode object() {
        return object;
    }

    public void setObject(ValueNode lockedObject) {
        updateUsages(this.object, lockedObject);
        this.object = lockedObject;
    }

    public ValueNode getObjectData() {
        return objectData;
    }

    public void setObjectData(ValueNode objectData) {
        updateUsages(this.objectData, objectData);
        this.objectData = objectData;
    }

    public MonitorIdNode getMonitorId() {
        return monitorId;
    }

    protected boolean biasable = true;

}
