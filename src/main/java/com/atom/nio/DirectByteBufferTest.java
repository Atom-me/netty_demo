package com.atom.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Atom
 */
public class DirectByteBufferTest {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        /**
         * 使用 ByteBuffer.allocateDirect(20) 方法 创建 DirectByteBuffer 类
         *
         * DirectByteBuffer 类引入很多没有开源的类
         *
         *              import sun.misc.Cleaner;
         *              import sun.misc.Unsafe;
         *              import sun.misc.VM;
         *              import sun.nio.ch.DirectBuffer;
         *
         *              使用了本地方法：base = unsafe.allocateMemory(size);
         */
        ByteBuffer buffer = ByteBuffer.allocateDirect(20);

        while (true) {

            buffer.clear();//如果注释掉该行代码，会发生什么情况？会不断的追加内容写入 outputChannel

            int read = inputChannel.read(buffer);
            System.out.println("read:" + read);
            if (read == -1) {
                break;
            }

            buffer.flip();

            outputChannel.write(buffer);

        }

        inputChannel.close();
        outputChannel.close();


    }
}
