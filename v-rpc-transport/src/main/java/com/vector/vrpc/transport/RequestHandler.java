package com.vector.vrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求
 */
public interface RequestHandler {
    void onRequest(InputStream recive, OutputStream toResponse);
}
