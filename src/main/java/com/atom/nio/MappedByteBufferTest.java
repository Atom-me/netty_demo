package com.atom.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件的内存映射 byteBuffer
 * <p>
 * 我们可以将整个文件或者文件的一部分映射到内存中，
 * 我们不需要直接跟磁盘打交道，只需要跟内存打交道即可。
 * <p>
 * 我们对内存所做的任何修改就会被写到磁盘上。可以实现非常迅速的IO操作。
 * 用于内存映射文件的内存属于Java堆外内存。
 *
 * @author Atom
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();
        //获取 mappedByteBuffer
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        //对文件的修改直接在内存里面修改，然后由操作系统完成写操作。
        // 修改mapperByteBuffer内容,需要在文件管理器里查看文件内容改变。
        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(3, (byte) 'b');


        randomAccessFile.close();


    }
}
