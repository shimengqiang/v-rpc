package com.vector.vrpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 网络传输一个端点
 *
 * @author smq
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;

}
