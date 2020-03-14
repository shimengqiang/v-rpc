package com.vector.vrpc;

import com.vector.vrpc.codec.Decoder;
import com.vector.vrpc.codec.Encoder;
import com.vector.vrpc.codec.JSONDecoder;
import com.vector.vrpc.codec.JSONEncoder;
import com.vector.vrpc.common.utils.ReflectionUtils;
import com.vector.vrpc.transport.RequestHandler;
import com.vector.vrpc.transport.TransportServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * @author smq
 */

@Data
@Slf4j
public class RpcServer {

    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;

        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        this.encoder = ReflectionUtils.newInstance(config.getEncodeClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceCalss, T bean){
        this.serviceManager.register(interfaceCalss, bean);
    }

    public void start(){
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResponse) {
            Response response = new Response();
            try {
                byte[] bytes = IOUtils.readFully(recive, recive.available());
                Request request = decoder.decode(bytes, Request.class);
                log.info(" request {}", request);
                ServiceInstance serviceInstance = serviceManager.lockup(request);
                Object res = serviceInvoker.invode(serviceInstance, request);
                response.setData(res);

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                response.setCode(1);
                response.setMessage("rpcserver got error "+ e.getClass().getName()+
                    ":"+ e.getMessage());
            } finally {
                byte[] outBytes = encoder.encode(response);
                try {
                    toResponse.write(outBytes);
                    log.info("rpc response ");
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }

        }
    };
}
