package com.atom.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 从一个文件里读取数据，写入到另一个文件
 *
 * @author Atom
 */
public class FileChannelTest4 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(20);
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
