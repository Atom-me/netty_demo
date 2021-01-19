package com.atom.netty.secondexample.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author Atom
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 服务器端向客户端发送消息的时候，这个方法就会被调用
     * <strong>Please keep in mind that this method will be renamed to
     * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
     * <p>
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取服务器端地址
        System.err.println(ctx.channel().remoteAddress());
        System.err.println("client output :" + msg);

        //向服务端写数据
        ctx.writeAndFlush("from client:" + LocalDateTime.now());
    }


    /**
     * 通道激活后将调用channelActive(...)（对于TCP表示通道已连接），而收到消息后将调用channelRead(...)。
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("来自于客户端的问候");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
