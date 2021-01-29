/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tools.javac.jvm;

import com.sun.tools.javac.code.Symbol.ClassSymbol;
import com.sun.tools.javac.code.Symbol.ModuleSymbol;
import com.sun.tools.javac.code.Symbol.PackageSymbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.jvm.PoolConstant.NameAndType;
import com.sun.tools.javac.util.ByteBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Name.NameMapper;
import com.sun.tools.javac.util.Names;

import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Class;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Double;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Dynamic;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Fieldref;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Float;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Integer;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_InterfaceMethodref;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_InvokeDynamic;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Long;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_MethodHandle;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Methodref;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_MethodType;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Module;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_NameandType;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Package;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_String;
import static com.sun.tools.javac.jvm.ClassFile.CONSTANT_Utf8;
import static com.sun.tools.javac.jvm.ClassFile.internalize;

/**
 * Pool interface towards {@code ClassReader}. Exposes methods to decode and read javac entities
 * from the constant pool.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class PoolReader {

    private final ClassReader reader;
    private final ByteBuffer buf;
    private final Names names;
    private final Symtab syms;

    private ImmutablePoolHelper pool;

    PoolReader(ByteBuffer buf) {
        this(null, buf, null, null);
    }

    PoolReader(ClassReader reader, Names names, Symtab syms) {
        this(reader, reader.buf, names, syms);
    }

    PoolReader(ClassReader reader, ByteBuffer buf, Names names, Symtab syms) {
        this.reader = reader;
        this.buf = buf;
        this.names = names;
        this.syms = syms;
    }

    /**
     * Get a class symbol from the pool at given index.
     */
    ClassSymbol getClass(int index) {
        return pool.readIfNeeded(index);
    }

    /**
     * Get class name without resolving
     */
    <Z> Z peekClassName(int index, NameMapper<Z> mapper) {
        return peekName(buf.getChar(pool.offset(index)), mapper);
    }

    /**
     * Get package name without resolving
     */
    <Z> Z peekPackageName(int index, NameMapper<Z> mapper) {
        return peekName(buf.getChar(pool.offset(index)), mapper);
    }

    /**
     * Get module name without resolving
     */
    <Z> Z peekModuleName(int index, NameMapper<Z> mapper) {
        return peekName(buf.getChar(pool.offset(index)), mapper);
    }

    /**
     * Get a module symbol from the pool at given index.
     */
    ModuleSymbol getModule(int index) {
        return pool.readIfNeeded(index);
    }

    /**
     * Get a module symbol from the pool at given index.
     */
    PackageSymbol getPackage(int index) {
        return pool.readIfNeeded(index);
    }

    /**
     * Peek a name from the pool at given index without resolving.
     */
    <Z> Z peekName(int index, Name.NameMapper<Z> mapper) {
        return getUtf8(index, mapper);
    }

    /**
     * Get a name from the pool at given index.
     */
    Name getName(int index) {
        return pool.readIfNeeded(index);
    }

    /**
     * Get a type from the pool at given index.
     */
    Type getType(int index) {
        return getName(index).map(reader::sigToType);
    }

    /**
     * Get a name and type pair from the pool at given index.
     */
    NameAndType getNameAndType(int index) {
        return pool.readIfNeeded(index);
    }

    /**
     * Get a class symbol from the pool at given index.
     */
    Object getConstant(int index) {
        return pool.readIfNeeded(index);
    }

    boolean hasTag(int index, int tag) {
        return pool.tag(index) == tag;
    }

    private <Z> Z getUtf8(int index, NameMapper<Z> mapper) {
        int tag = pool.tag(index);
        if (tag == CONSTANT_Utf8) {
            int offset = pool.offset(index);
            int len = pool.poolbuf.getChar(offset);
            return mapper.map(pool.poolbuf.elems, offset + 2, len);
        } else {
            throw new AssertionError("Unexpected constant tag: " + tag);
        }
    }

    private Object resolve(ByteBuffer poolbuf, int tag, int offset) {
        switch (tag) {
            case CONSTANT_Utf8: {
                int len = poolbuf.getChar(offset);
                return names.fromUtf(poolbuf.elems, offset + 2, len);
            }
            case CONSTANT_Class: {
                int index = poolbuf.getChar(offset);
                Name name = names.fromUtf(getName(index).map(ClassFile::internalize));
                return syms.enterClass(reader.currentModule, name);
            }
            case CONSTANT_NameandType: {
                Name name = getName(poolbuf.getChar(offset));
                Type type = getType(poolbuf.getChar(offset + 2));
                return new NameAndType(name, type);
            }
            case CONSTANT_Integer:
                return poolbuf.getInt(offset);
            case CONSTANT_Float:
                return poolbuf.getFloat(offset);
            case CONSTANT_Long:
                return poolbuf.getLong(offset);
            case CONSTANT_Double:
                return poolbuf.getDouble(offset);
            case CONSTANT_String:
                return getName(poolbuf.getChar(offset)).toString();
            case CONSTANT_Package: {
                Name name = getName(poolbuf.getChar(offset));
                return syms.enterPackage(reader.currentModule, names.fromUtf(internalize(name)));
            }
            case CONSTANT_Module: {
                Name name = getName(poolbuf.getChar(offset));
                return syms.enterModule(name);
            }
            default:
                throw new AssertionError("Unexpected constant tag: " + tag);
        }
    }

    /**
     * Parse all constant pool entries, and keep track of their offsets. For performance and laziness
     * reasons, it would be unwise to eagerly turn all pool entries into corresponding javac
     * entities. First, not all entries are actually going to be read/used by javac; secondly,
     * there are cases where creating a symbol too early might result in issues (hence methods like
     * {@link PoolReader#peekClassName(int, NameMapper)}.
     */
    int readPool(ByteBuffer poolbuf, int offset) {
        int poolSize = poolbuf.getChar(offset);
        int index = 1;
        offset += 2;
        int[] offsets = new int[poolSize];
        while (index < poolSize) {
            byte tag = poolbuf.getByte(offset++);
            offsets[index] = offset;
            switch (tag) {
                case CONSTANT_Utf8: {
                    int len = poolbuf.getChar(offset);
                    offset += 2 + len;
                    break;
                }
                case CONSTANT_Class:
                case CONSTANT_String:
                case CONSTANT_Module:
                case CONSTANT_Package:
                case CONSTANT_MethodType:
                    offset += 2;
                    break;
                case CONSTANT_MethodHandle:
                    offset += 3;
                    break;
                case CONSTANT_Fieldref:
                case CONSTANT_Methodref:
                case CONSTANT_InterfaceMethodref:
                case CONSTANT_NameandType:
                case CONSTANT_Integer:
                case CONSTANT_Float:
                case CONSTANT_Dynamic:
                case CONSTANT_InvokeDynamic:
                    offset += 4;
                    break;
                case CONSTANT_Long:
                case CONSTANT_Double:
                    offset += 8;
                    break;
                default:
                    throw reader.badClassFile("bad.const.pool.tag.at",
                            Byte.toString(tag),
                            Integer.toString(offset - 1));
            }
            index += sizeof(tag);
        }
        pool = new ImmutablePoolHelper(poolbuf, offsets);
        return offset;
    }

    private int sizeof(int tag) {
        switch (tag) {
            case ClassFile.CONSTANT_Double: case ClassFile.CONSTANT_Long:
                return 2;
            default:
                return 1;
        }
    }

    class ImmutablePoolHelper {

        final Object[] values;
        final int[] offsets;
        final ByteBuffer poolbuf;

        public ImmutablePoolHelper(ByteBuffer poolbuf, int[] offsets) {
            this.offsets = offsets;
            this.values = new Object[offsets.length];
            this.poolbuf = poolbuf;
        }

        private int checkIndex(int index) {
            if (index <= 0 || index >= offsets.length) {
                //pool index is outside valid range.
                throw reader.badClassFile("bad.const.pool.index", reader.currentClassFile,
                        index, offsets.length);
            } else {
                return index;
            }
        }

        int offset(int index) {
            return offsets[checkIndex(index)];
        }

        @SuppressWarnings("unchecked")
        <P> P readIfNeeded(int index) {
            Object v = values[checkIndex(index)];
            if (v != null) {
                return (P)v;
            } else {
                P p = (P)resolve(poolbuf, tag(index), offset(index));
                values[index] = p;
                return p;
            }
        }

        int tag(int index) {
            return poolbuf.elems[offset(index) - 1];
        }
    }
}