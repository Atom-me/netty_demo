package com.atom.nio;

import io.netty.buffer.ByteBuf;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author Atom
 */
public class ScatteringGatheringTest {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        //读
        while (true) {


            int bytesRead = 0;
            //如果读到的字节数小于能接收到的总的长度的时候就不断的读
            while (bytesRead < messageLength) {
                long r = socketChannel.read(buffers);
                bytesRead += r;

                //每次实际读到的信息
                System.out.println("bytes read:" + bytesRead);

                Arrays.stream(buffers).map(buffer -> "position: " + buffer.position() + ", limit: " + buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.stream(buffers).forEach(Buffer::flip);


            //写
            long bytesWritten = 0;
            while (bytesWritten < messageLength) {
                long w = socketChannel.write(buffers);
                bytesWritten += w;
            }

            Arrays.stream(buffers).forEach(Buffer::clear);

            System.out.println("byte read: " + bytesRead + ", byte written : " + bytesWritten + ", messageLength : " + messageLength);

        }


    }
}
