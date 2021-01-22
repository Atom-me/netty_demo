package com.atom.nio;

import java.nio.ByteBuffer;

/**
 * @author Atom
 */
public class ReadOnlyBufferTest {
    public static void main(String[] args) {

        /**
         * ByteBuffer 不能直接new创建对象，必须使用 allocate方法
         * allocate 方法 默认创建的就是 HeapByteBuffer 实现类
         */
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //class java.nio.HeapByteBuffer
        System.out.println(buffer.getClass());

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        /**
         *  buffer.asReadOnlyBuffer() 方法 返回一个新的只读的buffer。 HeapByteBufferR
         */
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        //class java.nio.HeapByteBufferR
        System.out.println(readOnlyBuffer.getClass());

        /**
         * 只读buffer，不可以写数据，写数据方法的实现就是直接抛出异常
         */
//        readOnlyBuffer.put((byte) 2);

    }
}
