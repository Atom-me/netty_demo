package com.atom.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author Atom
 */
public class NioTest1 {
    public static void main(String[] args) {
        //分配一个大小为10的缓冲区
        IntBuffer buffer = IntBuffer.allocate(10);

        //往buffer里面放 10个随机数
        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        //进行读写切换
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

    }
}
