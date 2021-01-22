package com.atom.nio;

import java.nio.ByteBuffer;

/**
 * Slice buffer 与原有buffer共享相同的底层数组
 *
 * @author Atom
 */
public class SliceBufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);
        }


        //slice方法其实是用于分割缓存区的，该方法创建了一个从原始缓冲区的当前位置开始的新缓冲区，并且其容量是原始缓冲区的剩余元素数量（limit-position）；
        // 指定分割区间 2到6
        byteBuffer.position(2);
        byteBuffer.limit(6);

        ByteBuffer sliceBuffer = byteBuffer.slice();

        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i, b);
        }

        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());

        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());
        }
    }
}
