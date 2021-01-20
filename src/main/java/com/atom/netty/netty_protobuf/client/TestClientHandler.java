package com.atom.netty.netty_protobuf.client;

import com.atom.netty.netty_protobuf.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author Atom
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       /* MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                .setName("atom")
                .setAddress("beijng")
                .setAge(44)
                .build();*/

        MyDataInfo.MyMessage.Builder myMessage = null;
        int randomInt = new Random().nextInt(3);

        if (randomInt == 0) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder()
                            .setName("atom")
                            .setAddress("beijing")
                            .setAge(11));
        } else if (randomInt == 1) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder()
                            .setName("one dog")
                            .setAge(11));
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder()
                            .setName("one cat")
                            .setCity("new york"));
        }


        ctx.writeAndFlush(myMessage);
    }
}
