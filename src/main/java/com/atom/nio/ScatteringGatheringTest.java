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
 * Java NIO开始支持scatter/gather，scatter/gather用于描述从Channel中读取或者写入到Channel的操作。
 * <p>
 * 分散(scatter): 从Channel中读取数据，"分散"的写入到多个Buffer中。
 * <p>
 * 聚集(gather): 从多个Buffer中读取数据"聚集"在一起，写入到一个Channel中。
 * <p>
 * scatter / gather经常用于需要将传输的数据分开处理的场合，例如传输一个由消息头和消息体组成的消息，
 * <p>
 * 你可能会将消息体和消息头分散到不同的buffer中，这样你可以方便的处理消息头和消息体。
 *
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
