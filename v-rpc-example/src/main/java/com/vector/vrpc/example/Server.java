package com.vector.vrpc.example;

import com.vector.vrpc.RpcServer;

/**
 * @author smq
 */

public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcSevice.class, new CalcSeviceImpl());
        server.start();
    }

}
