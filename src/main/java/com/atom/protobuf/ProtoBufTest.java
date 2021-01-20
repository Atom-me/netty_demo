package com.atom.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author Atom
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("atom")
                .setAddress("北京")
                .setAge(12)
                .build();
        byte[] student2ByteArray = student.toByteArray();
        DataInfo.Student student1 = DataInfo.Student.parseFrom(student2ByteArray);
        System.err.println(student1);

    }
}
