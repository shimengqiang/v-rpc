package com.vector.vprc.client;

import com.vector.vrpc.codec.Decoder;
import com.vector.vrpc.codec.Encoder;
import com.vector.vrpc.common.utils.ReflectionUtils;
import java.lang.reflect.Proxy;

/**
 * @author smq
 */

public class RpcClient {
    private PpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new PpcClientConfig());
    }

    public RpcClient(PpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecodeClass());
        this.selector = ReflectionUtils.newInstance(config.getSelectorClass());
        this.selector.init(this.config.getServers(),
            this.config.getConnectCount(),
            this.config.getTransportClass());
    }

    public <T> T getPorxy(Class<T> tClass){
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
            new Class[]{tClass},
            new RemoteInvoker(tClass, encoder, decoder, selector) );
    }
}
