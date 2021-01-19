package com.atom.netty.chatexample.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author Atom
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 保存所有于服务端已经建立连接的客户端channel对象
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * <strong>Please keep in mind that this method will be renamed to
     * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
     * <p>
     * Is called for each message of type {@link String}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取发送消息本身的channel对象
        Channel channel = ctx.channel();
        //遍历channelGroup 对象
        channelGroup.forEach(ch -> {
            if (channel != ch) {//如果不是自己发的，返回发送者的IP和消息
                ch.writeAndFlush(channel.remoteAddress() + "发送的消息：" + msg + "\n");
            } else {
                ch.writeAndFlush("【自己】：" + msg + "\n");
            }
        });

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //广播，通知所有机器
        channelGroup.writeAndFlush("【服务器】- " + channel.remoteAddress() + " 加入\n");
        //先广播，在加入，这样就不会通知到自己
        channelGroup.add(channel);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //广播
        channelGroup.writeAndFlush("【服务器】- " + channel.remoteAddress() + " 离开\n");
        System.err.println(channelGroup.size());

//        channelGroup.remove(channel);//netty 会自动调用移除此channel
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.err.println(channel.remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.err.println(channel.remoteAddress() + " 下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
