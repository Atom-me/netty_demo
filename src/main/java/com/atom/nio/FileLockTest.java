package com.atom.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
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


    }
}
