package com.vector.vrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author smq
 */

public class JSONEncoder implements Encoder {
    @Override
    public byte[] encode(Object o) {
        return JSON.toJSONBytes(o);
    }
}
