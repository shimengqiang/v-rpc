package com.vector.vrpc.codec;

/**
 * @author smq
 */

public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> tClass);
}
