/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.gc;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.InputType;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.FixedWithNextNode;
import org.graalvm.compiler.nodes.memory.address.AddressNode;
import org.graalvm.compiler.nodes.spi.Lowerable;
import org.graalvm.compiler.nodes.spi.LoweringTool;

@NodeInfo
public abstract class WriteBarrier extends FixedWithNextNode implements Lowerable {

    public static final NodeClass<WriteBarrier> TYPE = NodeClass.create(WriteBarrier.class);
    @Input(InputType.Association) AddressNode address;

    protected WriteBarrier(NodeClass<? extends WriteBarrier> c, AddressNode address) {
        super(c, StampFactory.forVoid());
        this.address = address;
    }

    @Override
    public void lower(LoweringTool tool) {
        assert graph().getGuardsStage().areFrameStatesAtDeopts();
        tool.getLowerer().lower(this, tool);
    }

    public AddressNode getAddress() {
        return address;
    }
}
