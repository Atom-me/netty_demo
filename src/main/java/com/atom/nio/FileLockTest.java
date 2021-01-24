package com.atom.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁可以是shared(共享锁)或者exclusive(排他锁)。不是所有的平台都以同一种方式实现文件锁，不同的操作系统可能不同，同一操作系统上的不同文件系统也可能不同。
 * 有些操作系统只提供协同锁，有些只提供强制锁，有些则都提供。
 * <p>
 * 文件锁是以文件为单位的，不是以通道，也不是线程。所以文件锁不适合同一个多个线程访问的情形。如果一个线程获得了给定文件的排他锁，
 * 第二个线程请求打开了一个新的channel，请求获得排他锁，请求会被批准。
 * 但如果这两个线程运行在不同的JVM中，第二个线程会阻塞，因为锁往往是根据进程来进行裁决，而不是线程。锁工作于一个文件，而不是单独的文件处理器或是通道。
 * <p>
 *
 * @author Atom
 */
public class FileLockTest {
    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        //获取文件锁, 三个参数
//        不带参数的lock方法，等效于
//        fileChannel.lock(0L,Long.MAX_VALUE, false);
        FileLock fileLock = fileChannel.lock(3, 6, true);

        System.out.println("fileLock 是否有效：" + fileLock.isValid());
        System.out.println("文件锁是否是共享锁：" + fileLock.isShared());

        fileLock.release();
        randomAccessFile.close();

    }
}
