package com.atom.netty.socket_example.server;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @author Atom
 */
public class SystemPropertyUtilTest {
    public static void main(String[] args) {
        System.out.println(SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    }
}
