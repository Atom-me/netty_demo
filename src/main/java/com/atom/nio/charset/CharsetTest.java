package com.atom.nio.charset;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author Atom
 */
public class CharsetTest {
    public static void main(String[] args) throws Exception {
        String inputFile = "CharsetTest_in.txt";
        String outputFile = "CharsetTest_out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        long inputLength = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        //内存映射文件
        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        //创建 UTF-8 编码解码器
//        Charset charset = Charset.forName("UTF-8");
        Charset charset = Charset.forName("iso-8859-1");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        //decode bytebuffer to charBuffer
        //使用 iso-8859-1 解码，这只是一个中间过程
        CharBuffer charBuffer = decoder.decode(inputData);

        // 中文 "你好" 从第12个字符开始，我们打印第12个字符，肯定是乱码，因为 iso-8859-1 无法表示中文，会取中文的第一个字节用 iso-8859-1 来表示，即乱码
        System.out.println(charBuffer.get(12));

        //把charBuffer编码成 bytebuffer,然后写入文件
        //前面使用 iso-8859-1 ，这里输出文件不会乱码
        ByteBuffer outputData = encoder.encode(charBuffer);

//        ByteBuffer outputData = Charset.forName("utf-8").encode(charBuffer);
        outputFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();
//
//        System.out.println("============================================");
//        Charset.availableCharsets().forEach((key, value) -> System.out.println(key + "," + value));
//        System.out.println("============================================");


    }
}
