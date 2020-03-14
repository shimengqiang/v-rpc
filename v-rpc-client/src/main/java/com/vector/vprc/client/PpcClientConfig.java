package com.vector.vprc.client;

import com.vector.vrpc.Peer;
import com.vector.vrpc.codec.Decoder;
import com.vector.vrpc.codec.Encoder;
import com.vector.vrpc.codec.JSONDecoder;
import com.vector.vrpc.codec.JSONEncoder;
import com.vector.vrpc.transport.HTTPTransportClient;
import com.vector.vrpc.transport.TransportClient;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

/**
 * @author smq
 */
@Data
public class PpcClientConfig {

    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decodeClass = JSONDecoder.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));

}
