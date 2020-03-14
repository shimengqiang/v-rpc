package com.vector.vrpc.example;

import com.vector.vprc.client.RpcClient;

/**
 * @author smq
 */

public class Client {
    public static void main(String[] args) {
        RpcClient client  = new RpcClient();
        CalcSevice sevice = client.getPorxy(CalcSevice.class);

        int add = sevice.add(1, 3);
        int minus = sevice.minus(2, 1);
        System.out.println(add);
        System.out.println(minus);


    }

}
