package com.vector.vrpc;

import com.vector.vrpc.codec.Decoder;
import com.vector.vrpc.codec.Encoder;
import com.vector.vrpc.codec.JSONDecoder;
import com.vector.vrpc.codec.JSONEncoder;
import com.vector.vrpc.transport.HTTPTransportServer;
import com.vector.vrpc.transport.TransportServer;
import lombok.Data;

/**
 * @author smq
 */
@Data
public class RpcServerConfig {
    private Class< ? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class< ? extends Encoder> encodeClass = JSONEncoder.class;
    private Class< ? extends Decoder> decoderClass = JSONDecoder.class;

    private int port = 3000;


}
