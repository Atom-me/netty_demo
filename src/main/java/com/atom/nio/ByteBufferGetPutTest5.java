package com.atom.nio;

import java.nio.ByteBuffer;

/**
 * ByteBuffer 类型化的put和get方法
 * <p>
 * ByteBuffer 不仅仅只能往里面放字节，任何支持的原生数据类型都可以往里放
 *
 * @author Atom
 */
public class ByteBufferGetPutTest5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(5);
        buffer.putLong(500000000L);
        buffer.putDouble(14.123456);
        buffer.putChar('你');
        buffer.putShort((short) 2);
        buffer.putChar('我');

        buffer.flip();

        //get的时候也要按照put的时候使用的具体类型，否则会报错。即  ByteBuffer 类型化的put和get方法
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
