package com.vector.vprc.client;

import com.vector.vrpc.Request;
import com.vector.vrpc.Response;
import com.vector.vrpc.ServiceDescriptor;
import com.vector.vrpc.codec.Decoder;
import com.vector.vrpc.codec.Encoder;
import com.vector.vrpc.transport.TransportClient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * @author smq
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz,Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0){
            throw new IllegalStateException("");
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        Response response = new Response();
        TransportClient client = null;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream recive = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(recive, recive.available());
            response = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            response.setCode(1);
            response.setMessage("rpcclient got error "+ e.getClass().getName()+
                ":"+ e.getMessage());
        } finally {
            if (client != null){
                selector.release(client);
            }
        }
        return response;
    }
}
