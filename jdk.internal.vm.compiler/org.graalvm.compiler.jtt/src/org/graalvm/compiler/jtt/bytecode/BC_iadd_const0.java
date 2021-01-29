/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.jtt.bytecode;

import org.junit.Test;

import org.graalvm.compiler.api.directives.GraalDirectives;
import org.graalvm.compiler.jtt.JTTTest;

/*
 */
public class BC_iadd_const0 extends JTTTest {

    public static int test(int a, int b, boolean neg) {
        int x = GraalDirectives.opaque(a);
        if (!neg) {
            return x + b;
        }
        return x - b;
    }

    @Test
    public void run0() throws Throwable {
        runTest("test", 42, 1, false);
    }

    @Test
    public void run1() throws Throwable {
        runTest("test", 42, -1, false);
    }

    @Test
    public void run2() throws Throwable {
        runTest("test", 42, 1, true);
    }

    @Test
    public void run3() throws Throwable {
        runTest("test", 42, -1, true);
    }
}
