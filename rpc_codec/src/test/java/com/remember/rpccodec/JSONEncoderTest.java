package com.remember.rpccodec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONEncoderTest {
    @Test
    public void encoder() throws Exception {
        TestBean testBean = new TestBean();
        testBean.setName("张三");
        testBean.setAge(18);

        //测试序列化
        JSONEncoder jsonEncoder = new JSONEncoder();
        byte[] encoder = jsonEncoder.encoder(testBean);

        //测试反序列化
        JSONDecoder jsonDecoder = new JSONDecoder();
        TestBean decode = jsonDecoder.decode(encoder, TestBean.class);

        System.out.println(decode.getAge());
        System.out.println(decode.getName());
        assertNotNull(decode);
    }

}