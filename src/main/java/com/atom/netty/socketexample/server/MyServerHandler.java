package com.atom.netty.socketexample.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author Atom
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 客户端向服务器端发送消息的时候，这个方法就会被调用
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
        //获取客户端地址
        System.err.println(ctx.channel().remoteAddress() + " , " + msg);

        //向客户端写数据
        ctx.channel().writeAndFlush("from server : " + UUID.randomUUID());

    }


    /**
     * 如果出现异常，关闭连接
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

    }
}
