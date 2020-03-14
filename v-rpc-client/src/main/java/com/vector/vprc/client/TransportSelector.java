package com.vector.vprc.client;

import com.vector.vrpc.Peer;
import com.vector.vrpc.transport.TransportClient;
import java.util.List;

/**
 * 选择哪个server 去连接
 *
 * @author smq
 */

public interface TransportSelector {
    /**
     * 初始化 selector
     * @param peers
     * @param count client 与server 建立多少连接
     * @param clazz client 实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选一个 transport与server做交互
     * @return
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);

    void close();


}
