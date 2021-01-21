package com.atom.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * channel读取文件
 *
 * @author Atom
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {
        //获取文件输入流
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        //获取channel对象
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        //从channel里面读数据，往buffer里写数据
        fileChannel.read(byteBuffer);

        //切换写模式
        byteBuffer.flip();

        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println("Character: " + (char) b);
        }

        fileInputStream.close();

    }
}
