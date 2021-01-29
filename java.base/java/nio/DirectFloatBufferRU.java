/*
 * Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.
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

// -- This file was mechanically generated: Do not edit! -- //

package java.nio;

import java.io.FileDescriptor;
import java.lang.ref.Reference;
import java.util.Objects;
import jdk.internal.access.foreign.MemorySegmentProxy;
import jdk.internal.misc.VM;
import jdk.internal.ref.Cleaner;
import sun.nio.ch.DirectBuffer;


class DirectFloatBufferRU



    extends DirectFloatBufferU

    implements DirectBuffer
{






















































































































































    // For duplicates and slices
    //
    DirectFloatBufferRU(DirectBuffer db,         // package-private
                               int mark, int pos, int lim, int cap,
                               int off, MemorySegmentProxy segment)
    {









        super(db, mark, pos, lim, cap, off, segment);
        this.isReadOnly = true;

    }

    @Override
    Object base() {
        return null;
    }

    public FloatBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        int off = (pos << 2);
        assert (off >= 0);
        return new DirectFloatBufferRU(this, -1, 0, rem, rem, off, segment);
    }

    @Override
    public FloatBuffer slice(int index, int length) {
        Objects.checkFromIndexSize(index, length, limit());
        return new DirectFloatBufferRU(this,
                                              -1,
                                              0,
                                              length,
                                              length,
                                              index, segment);
    }

    public FloatBuffer duplicate() {
        return new DirectFloatBufferRU(this,
                                              this.markValue(),
                                              this.position(),
                                              this.limit(),
                                              this.capacity(),
                                              0, segment);
    }

    public FloatBuffer asReadOnlyBuffer() {








        return duplicate();

    }






















































































































    public FloatBuffer put(float x) {









        throw new ReadOnlyBufferException();

    }

    public FloatBuffer put(int i, float x) {









        throw new ReadOnlyBufferException();

    }

    public FloatBuffer put(FloatBuffer src) {





        throw new ReadOnlyBufferException();

    }

    public FloatBuffer put(float[] src, int offset, int length) {





































        throw new ReadOnlyBufferException();

    }

    public FloatBuffer put(int index, float[] src, int offset, int length) {
































        throw new ReadOnlyBufferException();

    }

    public FloatBuffer compact() {















        throw new ReadOnlyBufferException();

    }

    public boolean isDirect() {
        return true;
    }

    public boolean isReadOnly() {
        return true;
    }













































    public ByteOrder order() {





        return ((ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN)
                ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);

    }


















}
