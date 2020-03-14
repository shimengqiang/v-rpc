package com.vector.vrpc.codec;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSONEncoderTest {

    @Test
    public void encode() {
        JSONEncoder encoder = new JSONEncoder();
        TestBean bean = new TestBean("cathy", 18);
        byte[] encode = encoder.encode(bean);
        JSONDecoder decoder = new JSONDecoder();
        TestBean decode = decoder.decode(encode, TestBean.class);

        assertEquals(bean, decode);

    }
}
