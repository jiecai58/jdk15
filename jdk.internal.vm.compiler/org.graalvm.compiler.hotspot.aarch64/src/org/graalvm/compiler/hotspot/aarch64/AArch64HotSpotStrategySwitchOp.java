/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.aarch64;

import static jdk.vm.ci.code.ValueUtil.asRegister;

import java.util.function.Function;

import org.graalvm.compiler.asm.aarch64.AArch64Assembler;
import org.graalvm.compiler.asm.aarch64.AArch64MacroAssembler;
import org.graalvm.compiler.core.common.calc.Condition;
import org.graalvm.compiler.debug.GraalError;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.LabelRef;
import org.graalvm.compiler.lir.SwitchStrategy;
import org.graalvm.compiler.lir.aarch64.AArch64ControlFlow;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.code.Register;
import jdk.vm.ci.hotspot.HotSpotMetaspaceConstant;
import jdk.vm.ci.meta.Constant;
import jdk.vm.ci.meta.Value;

final class AArch64HotSpotStrategySwitchOp extends AArch64ControlFlow.StrategySwitchOp {
    public static final LIRInstructionClass<AArch64HotSpotStrategySwitchOp> TYPE = LIRInstructionClass.create(AArch64HotSpotStrategySwitchOp.class);

    AArch64HotSpotStrategySwitchOp(SwitchStrategy strategy, LabelRef[] keyTargets, LabelRef defaultTarget, Value key, Value scratch, Function<Condition, AArch64Assembler.ConditionFlag> converter) {
        super(TYPE, strategy, keyTargets, defaultTarget, key, scratch, converter);
    }

    @Override
    public void emitCode(final CompilationResultBuilder crb, final AArch64MacroAssembler masm) {
        strategy.run(new HotSpotSwitchClosure(asRegister(key), crb, masm));
    }

    public class HotSpotSwitchClosure extends SwitchClosure {

        protected HotSpotSwitchClosure(Register keyRegister, CompilationResultBuilder crb, AArch64MacroAssembler masm) {
            super(keyRegister, crb, masm);
        }

        @Override
        protected void emitComparison(Constant c) {
            if (c instanceof HotSpotMetaspaceConstant) {
                HotSpotMetaspaceConstant meta = (HotSpotMetaspaceConstant) c;
                if (meta.isCompressed()) {
                    crb.recordInlineDataInCode(meta);
                    // masm.cmpl(keyRegister, 0xDEADDEAD);
                    throw GraalError.unimplemented();
                } else {
                    crb.recordInlineDataInCode(meta);
                    masm.movNativeAddress(asRegister(scratch), 0x0000_DEAD_DEAD_DEADL);
                    masm.cmp(64, keyRegister, asRegister(scratch));
                }
            } else {
                super.emitComparison(c);
            }
        }
    }
}
