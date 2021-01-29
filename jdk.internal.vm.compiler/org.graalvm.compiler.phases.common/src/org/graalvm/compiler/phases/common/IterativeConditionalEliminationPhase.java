/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.phases.common;

import org.graalvm.compiler.core.common.GraalOptions;
import org.graalvm.compiler.core.common.PermanentBailoutException;
import org.graalvm.compiler.debug.TTY;
import org.graalvm.compiler.graph.Graph.NodeEventScope;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.phases.BasePhase;
import org.graalvm.compiler.phases.common.util.EconomicSetNodeEventListener;
import org.graalvm.compiler.phases.common.util.TracingNodeEventListener;

public class IterativeConditionalEliminationPhase extends BasePhase<CoreProviders> {

    private static final boolean DEBUG_PHASE = false;
    private static final int DEBUG_MAX_ITERATIONS = 256;

    private final CanonicalizerPhase canonicalizer;
    private final boolean fullSchedule;

    public IterativeConditionalEliminationPhase(CanonicalizerPhase canonicalizer, boolean fullSchedule) {
        this.canonicalizer = canonicalizer;
        this.fullSchedule = fullSchedule;
    }

    @Override
    @SuppressWarnings("try")
    protected void run(StructuredGraph graph, CoreProviders context) {
        final int maxIterations = GraalOptions.ConditionalEliminationMaxIterations.getValue(graph.getOptions());
        EconomicSetNodeEventListener listener = new EconomicSetNodeEventListener();
        int count = 0;

        while (true) {
            count++;
            try (NodeEventScope nes = graph.trackNodeEvents(listener)) {
                new ConditionalEliminationPhase(fullSchedule).apply(graph, context);
            }
            if (listener.getNodes().isEmpty()) {
                break;
            }

            canonicalizer.applyIncremental(graph, context, listener.getNodes());
            listener.getNodes().clear();

            if (count >= maxIterations) {
                if (DEBUG_PHASE) {
                    if (count >= DEBUG_MAX_ITERATIONS - 5) {
                        TTY.println();
                        TTY.println("------------------------------------");
                        TTY.println("Iteration " + count);
                        TTY.println("Conditional elimination changed nodes: ");
                        for (Node n : listener.getNodes()) {
                            TTY.println(n.toString());
                            for (Node input : n.inputs()) {
                                TTY.println("    input: " + input);
                            }
                        }
                        TTY.println("Canonicalization with node listener: ");
                        try (NodeEventScope debugNes = graph.trackNodeEvents(new TracingNodeEventListener())) {
                            canonicalizer.applyIncremental(graph, context, listener.getNodes());
                        }
                    }
                    if (count >= DEBUG_MAX_ITERATIONS) {
                        throw new PermanentBailoutException("Number of iterations in ConditionalEliminationPhase phase exceeds %d", DEBUG_MAX_ITERATIONS);
                    }
                }
                break;
            }
        }
    }

    @Override
    public float codeSizeIncrease() {
        return 2.0f;
    }
}