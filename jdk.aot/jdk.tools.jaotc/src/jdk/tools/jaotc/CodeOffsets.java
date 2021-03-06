/*
 * Copyright (c) 2016, 2020, Oracle and/or its affiliates. All rights reserved.
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



package jdk.tools.jaotc;

import java.util.List;

import org.graalvm.compiler.code.CompilationResult;
import org.graalvm.compiler.hotspot.HotSpotMarkId;

final class CodeOffsets {
    private final int entry;
    private final int verifiedEntry;
    private final int exceptionHandler;
    private final int deoptHandler;

    private CodeOffsets(int entry, int verifiedEntry, int exceptionHandler, int deoptHandler) {
        this.entry = entry;
        this.verifiedEntry = verifiedEntry;
        this.exceptionHandler = exceptionHandler;
        this.deoptHandler = deoptHandler;
    }

    static CodeOffsets buildFrom(List<CompilationResult.CodeMark> marks) {
        int entry = 0;
        int verifiedEntry = 0;
        int exceptionHandler = -1;
        int deoptHandler = -1;

        for (CompilationResult.CodeMark mark : marks) {
            HotSpotMarkId markId = (HotSpotMarkId) mark.id;
            switch (markId) {
                case UNVERIFIED_ENTRY:
                    entry = mark.pcOffset;
                    break;
                case VERIFIED_ENTRY:
                    verifiedEntry = mark.pcOffset;
                    break;
                case OSR_ENTRY:
                    // Unhandled
                    break;
                case EXCEPTION_HANDLER_ENTRY:
                    exceptionHandler = mark.pcOffset;
                    break;
                case DEOPT_HANDLER_ENTRY:
                    deoptHandler = mark.pcOffset;
                    break;
                default:
                    break; // Ignore others
            }
        }
        return new CodeOffsets(entry, verifiedEntry, exceptionHandler, deoptHandler);
    }

    int entry() {
        return entry;
    }

    int verifiedEntry() {
        return verifiedEntry;
    }

    int exceptionHandler() {
        return exceptionHandler;
    }

    int deoptHandler() {
        return deoptHandler;
    }
}
