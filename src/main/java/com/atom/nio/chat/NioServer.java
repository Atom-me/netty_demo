package com.atom.nio.chat;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Atom
 */
public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        //Registers this channel with the given selector, returning a selection key.
        //将serverSocketChannel注册到 selector上。服务器端会关注连接事件
        //serverSocketChannel 关注的是连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //开始进行事件的处理
        while (true) {
            try {
                //返回selector所关注的事件的数量，是一个阻塞方法
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    //服务端会通过这个 socketChannel 和客户端进行通信
                    final SocketChannel client;
                    try {
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();
                            client.configureBlocking(false);
                            //SocketChannel 关注的是数据的读取事件
                            client.register(selector, SelectionKey.OP_READ);

                            String key = "[" + UUID.randomUUID().toString() + "]";
                            clientMap.put(key, client);

                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel) selectionKey.channel();

                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            //把客户端的数据读到 buffer里面
                            int count = client.read(readBuffer);
                            if (count > 0) {
                                readBuffer.flip();
                                Charset charset = StandardCharsets.UTF_8;
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());
                                System.out.println(client + "===>" + receivedMessage);

                                String senderKey = null;
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }

                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel socketChannel = entry.getValue();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                    writeBuffer.put((senderKey + ":" + receivedMessage).getBytes(StandardCharsets.UTF_8));
                                    writeBuffer.flip();
                                    socketChannel.write(writeBuffer);

                                }
                            }

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });

                selectionKeys.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
