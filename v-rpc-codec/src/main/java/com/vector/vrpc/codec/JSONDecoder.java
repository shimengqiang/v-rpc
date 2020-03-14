package com.vector.vrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author smq
 */

public class JSONDecoder implements Decoder {
    @Override public <T> T decode(byte[] bytes, Class<T> tClass) {
        return JSON.parseObject(bytes, tClass);
    }
}
