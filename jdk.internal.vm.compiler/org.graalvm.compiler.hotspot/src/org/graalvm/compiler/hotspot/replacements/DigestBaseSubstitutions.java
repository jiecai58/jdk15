/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.replacements;

import static org.graalvm.compiler.hotspot.GraalHotSpotVMConfig.INJECTED_METAACCESS;
import static org.graalvm.compiler.hotspot.GraalHotSpotVMConfig.INJECTED_INTRINSIC_CONTEXT;
import static org.graalvm.compiler.hotspot.GraalHotSpotVMConfig.INJECTED_VMCONFIG;
import static org.graalvm.compiler.nodes.java.InstanceOfNode.doInstanceof;

import org.graalvm.compiler.api.replacements.ClassSubstitution;
import org.graalvm.compiler.api.replacements.Fold;
import org.graalvm.compiler.api.replacements.MethodSubstitution;
import org.graalvm.compiler.hotspot.GraalHotSpotVMConfig;
import org.graalvm.compiler.hotspot.HotSpotBackend;
import org.graalvm.compiler.nodes.ComputeObjectAddressNode;
import org.graalvm.compiler.nodes.PiNode;
import org.graalvm.compiler.nodes.extended.RawLoadNode;
import org.graalvm.compiler.replacements.ReplacementsUtil;
import org.graalvm.compiler.word.Word;
import jdk.internal.vm.compiler.word.LocationIdentity;
import jdk.internal.vm.compiler.word.WordFactory;

import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.ResolvedJavaType;

@ClassSubstitution(className = "sun.security.provider.DigestBase", optional = true)
public class DigestBaseSubstitutions {

    @MethodSubstitution(isStatic = false)
    static int implCompressMultiBlock0(Object receiver, byte[] buf, int ofs, int limit) {
        Object realReceiver = PiNode.piCastNonNull(receiver, HotSpotReplacementsUtil.methodHolderClass(INJECTED_INTRINSIC_CONTEXT));
        ResolvedJavaType sha1type = HotSpotReplacementsUtil.getType(INJECTED_INTRINSIC_CONTEXT, "Lsun/security/provider/SHA;");
        ResolvedJavaType sha256type = HotSpotReplacementsUtil.getType(INJECTED_INTRINSIC_CONTEXT, "Lsun/security/provider/SHA2;");
        ResolvedJavaType sha512type = HotSpotReplacementsUtil.getType(INJECTED_INTRINSIC_CONTEXT, "Lsun/security/provider/SHA5;");

        Word bufAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(buf, ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Byte) + ofs));
        if (useSHA1Intrinsics(INJECTED_VMCONFIG) && doInstanceof(sha1type, realReceiver)) {
            Object sha1obj = PiNode.piCastNonNull(realReceiver, sha1type);
            Object state = RawLoadNode.load(sha1obj, HotSpotReplacementsUtil.getFieldOffset(sha1type, "state"), JavaKind.Object, LocationIdentity.any());
            Word stateAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(state, ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Int)));
            return HotSpotBackend.shaImplCompressMBStub(bufAddr, stateAddr, ofs, limit);
        } else if (useSHA256Intrinsics(INJECTED_VMCONFIG) && doInstanceof(sha256type, realReceiver)) {
            Object sha256obj = PiNode.piCastNonNull(realReceiver, sha256type);
            Object state = RawLoadNode.load(sha256obj, HotSpotReplacementsUtil.getFieldOffset(sha256type, "state"), JavaKind.Object, LocationIdentity.any());
            Word stateAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(state, ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Int)));
            return HotSpotBackend.sha2ImplCompressMBStub(bufAddr, stateAddr, ofs, limit);
        } else if (useSHA512Intrinsics(INJECTED_VMCONFIG) && doInstanceof(sha512type, realReceiver)) {
            Object sha512obj = PiNode.piCastNonNull(realReceiver, sha512type);
            Object state = RawLoadNode.load(sha512obj, HotSpotReplacementsUtil.getFieldOffset(sha512type, "state"), JavaKind.Object, LocationIdentity.any());
            Word stateAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(state, ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Int)));
            return HotSpotBackend.sha5ImplCompressMBStub(bufAddr, stateAddr, ofs, limit);
        } else {
            return implCompressMultiBlock0(realReceiver, buf, ofs, limit);
        }
    }

    @Fold
    public static boolean useSHA1Intrinsics(@Fold.InjectedParameter GraalHotSpotVMConfig config) {
        return config.useSHA1Intrinsics();
    }

    @Fold
    public static boolean useSHA256Intrinsics(@Fold.InjectedParameter GraalHotSpotVMConfig config) {
        return config.useSHA256Intrinsics();
    }

    @Fold
    public static boolean useSHA512Intrinsics(@Fold.InjectedParameter GraalHotSpotVMConfig config) {
        return config.useSHA512Intrinsics();
    }
}