/*
 * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
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
 *
 *
 */
package java.lang.invoke;

import jdk.internal.access.JavaNioAccess;
import jdk.internal.access.SharedSecrets;
import jdk.internal.access.foreign.MemorySegmentProxy;
import jdk.internal.misc.Unsafe;
import jdk.internal.util.Preconditions;
import jdk.internal.vm.annotation.ForceInline;

import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Objects;

import static java.lang.invoke.MethodHandleStatics.UNSAFE;

// -- This file was mechanically generated: Do not edit! -- //

final class VarHandleByteArrayAsChars extends VarHandleByteArrayBase {

    static JavaNioAccess nioAccess = SharedSecrets.getJavaNioAccess();

    static final int ALIGN = Character.BYTES - 1;

    @ForceInline
    static char convEndian(boolean big, char n) {
        return big == BE ? n : Character.reverseBytes(n);
    }


    private static abstract class ByteArrayViewVarHandle extends VarHandle {
        final boolean be;

        ByteArrayViewVarHandle(VarForm form, boolean be) {
            super(form);
            this.be = be;
        }
    }

    static final class ArrayHandle extends ByteArrayViewVarHandle {

        ArrayHandle(boolean be) {
            super(ArrayHandle.FORM, be);
        }

        @Override
        final MethodType accessModeTypeUncached(AccessMode accessMode) {
            return accessMode.at.accessModeType(byte[].class, char.class, int.class);
        }

        @ForceInline
        static int index(byte[] ba, int index) {
            return Preconditions.checkIndex(index, ba.length - ALIGN, null);
        }

        @ForceInline
        static long address(byte[] ba, int index) {
            long address = ((long) index) + Unsafe.ARRAY_BYTE_BASE_OFFSET;
            if ((address & ALIGN) != 0)
                throw newIllegalStateExceptionForMisalignedAccess(index);
            return address;
        }

        @ForceInline
        static char get(VarHandle ob, Object oba, int index) {
            ArrayHandle handle = (ArrayHandle)ob;
            byte[] ba = (byte[]) oba;
            return UNSAFE.getCharUnaligned(
                    ba,
                    ((long) index(ba, index)) + Unsafe.ARRAY_BYTE_BASE_OFFSET,
                    handle.be);
        }

        @ForceInline
        static void set(VarHandle ob, Object oba, int index, char value) {
            ArrayHandle handle = (ArrayHandle)ob;
            byte[] ba = (byte[]) oba;
            UNSAFE.putCharUnaligned(
                    ba,
                    ((long) index(ba, index)) + Unsafe.ARRAY_BYTE_BASE_OFFSET,
                    value,
                    handle.be);
        }

        @ForceInline
        static char getVolatile(VarHandle ob, Object oba, int index) {
            ArrayHandle handle = (ArrayHandle)ob;
            byte[] ba = (byte[]) oba;
            return convEndian(handle.be,
                              UNSAFE.getCharVolatile(
                                      ba,
                                      address(ba, index(ba, index))));
        }

        @ForceInline
        static void setVolatile(VarHandle ob, Object oba, int index, char value) {
            ArrayHandle handle = (ArrayHandle)ob;
            byte[] ba = (byte[]) oba;
            UNSAFE.putCharVolatile(
                    ba,
                    address(ba, index(ba, index)),
                    convEndian(handle.be, value));
        }

        @ForceInline
        static char getAcquire(VarHandle ob, Object oba, int index) {
            ArrayHandle handle = (ArrayHandle)ob;
            byte[] ba = (byte[]) oba;
            return convEndian(handle.be,
                              UNSAFE.getCharAcquire(
                                      ba,
                                      address(ba, index(ba, index))));
        }

        @ForceInline
        static void setRelease(VarHandle ob, Object oba, int index, char value) {
            ArrayHandle handle = (ArrayHandle)ob;
            byte[] ba = (byte[]) oba;
            UNSAFE.putCharRelease(
                    ba,
                    address(ba, index(ba, index)),
                    convEndian(handle.be, value));
        }

        @ForceInline
        static char getOpaque(VarHandle ob, Object oba, int index) {
            ArrayHandle handle = (ArrayHandle)ob;
            byte[] ba = (byte[]) oba;
            return convEndian(handle.be,
                              UNSAFE.getCharOpaque(
                                      ba,
                                      address(ba, index(ba, index))));
        }

        @ForceInline
        static void setOpaque(VarHandle ob, Object oba, int index, char value) {
            ArrayHandle handle = (ArrayHandle)ob;
            byte[] ba = (byte[]) oba;
            UNSAFE.putCharOpaque(
                    ba,
                    address(ba, index(ba, index)),
                    convEndian(handle.be, value));
        }

        static final VarForm FORM = new VarForm(ArrayHandle.class, byte[].class, char.class, int.class);
    }


    static final class ByteBufferHandle extends ByteArrayViewVarHandle {

        ByteBufferHandle(boolean be) {
            super(ByteBufferHandle.FORM, be);
        }

        @Override
        final MethodType accessModeTypeUncached(AccessMode accessMode) {
            return accessMode.at.accessModeType(ByteBuffer.class, char.class, int.class);
        }

        @ForceInline
        static int index(ByteBuffer bb, int index) {
            MemorySegmentProxy segmentProxy = nioAccess.bufferSegment(bb);
            if (segmentProxy != null) {
                segmentProxy.checkValidState();
            }
            return Preconditions.checkIndex(index, UNSAFE.getInt(bb, BUFFER_LIMIT) - ALIGN, null);
        }

        @ForceInline
        static int indexRO(ByteBuffer bb, int index) {
            if (UNSAFE.getBoolean(bb, BYTE_BUFFER_IS_READ_ONLY))
                throw new ReadOnlyBufferException();
            return index(bb, index);
        }

        @ForceInline
        static long address(ByteBuffer bb, int index) {
            long address = ((long) index) + UNSAFE.getLong(bb, BUFFER_ADDRESS);
            if ((address & ALIGN) != 0)
                throw newIllegalStateExceptionForMisalignedAccess(index);
            return address;
        }

        @ForceInline
        static char get(VarHandle ob, Object obb, int index) {
            ByteBufferHandle handle = (ByteBufferHandle)ob;
            ByteBuffer bb = (ByteBuffer) Objects.requireNonNull(obb);
            return UNSAFE.getCharUnaligned(
                    UNSAFE.getReference(bb, BYTE_BUFFER_HB),
                    ((long) index(bb, index)) + UNSAFE.getLong(bb, BUFFER_ADDRESS),
                    handle.be);
        }

        @ForceInline
        static void set(VarHandle ob, Object obb, int index, char value) {
            ByteBufferHandle handle = (ByteBufferHandle)ob;
            ByteBuffer bb = (ByteBuffer) Objects.requireNonNull(obb);
            UNSAFE.putCharUnaligned(
                    UNSAFE.getReference(bb, BYTE_BUFFER_HB),
                    ((long) indexRO(bb, index)) + UNSAFE.getLong(bb, BUFFER_ADDRESS),
                    value,
                    handle.be);
        }

        @ForceInline
        static char getVolatile(VarHandle ob, Object obb, int index) {
            ByteBufferHandle handle = (ByteBufferHandle)ob;
            ByteBuffer bb = (ByteBuffer) Objects.requireNonNull(obb);
            return convEndian(handle.be,
                              UNSAFE.getCharVolatile(
                                      UNSAFE.getReference(bb, BYTE_BUFFER_HB),
                                      address(bb, index(bb, index))));
        }

        @ForceInline
        static void setVolatile(VarHandle ob, Object obb, int index, char value) {
            ByteBufferHandle handle = (ByteBufferHandle)ob;
            ByteBuffer bb = (ByteBuffer) Objects.requireNonNull(obb);
            UNSAFE.putCharVolatile(
                    UNSAFE.getReference(bb, BYTE_BUFFER_HB),
                    address(bb, indexRO(bb, index)),
                    convEndian(handle.be, value));
        }

        @ForceInline
        static char getAcquire(VarHandle ob, Object obb, int index) {
            ByteBufferHandle handle = (ByteBufferHandle)ob;
            ByteBuffer bb = (ByteBuffer) Objects.requireNonNull(obb);
            return convEndian(handle.be,
                              UNSAFE.getCharAcquire(
                                      UNSAFE.getReference(bb, BYTE_BUFFER_HB),
                                      address(bb, index(bb, index))));
        }

        @ForceInline
        static void setRelease(VarHandle ob, Object obb, int index, char value) {
            ByteBufferHandle handle = (ByteBufferHandle)ob;
            ByteBuffer bb = (ByteBuffer) Objects.requireNonNull(obb);
            UNSAFE.putCharRelease(
                    UNSAFE.getReference(bb, BYTE_BUFFER_HB),
                    address(bb, indexRO(bb, index)),
                    convEndian(handle.be, value));
        }

        @ForceInline
        static char getOpaque(VarHandle ob, Object obb, int index) {
            ByteBufferHandle handle = (ByteBufferHandle)ob;
            ByteBuffer bb = (ByteBuffer) Objects.requireNonNull(obb);
            return convEndian(handle.be,
                              UNSAFE.getCharOpaque(
                                      UNSAFE.getReference(bb, BYTE_BUFFER_HB),
                                      address(bb, index(bb, index))));
        }

        @ForceInline
        static void setOpaque(VarHandle ob, Object obb, int index, char value) {
            ByteBufferHandle handle = (ByteBufferHandle)ob;
            ByteBuffer bb = (ByteBuffer) Objects.requireNonNull(obb);
            UNSAFE.putCharOpaque(
                    UNSAFE.getReference(bb, BYTE_BUFFER_HB),
                    address(bb, indexRO(bb, index)),
                    convEndian(handle.be, value));
        }

        static final VarForm FORM = new VarForm(ByteBufferHandle.class, ByteBuffer.class, char.class, int.class);
    }
}
