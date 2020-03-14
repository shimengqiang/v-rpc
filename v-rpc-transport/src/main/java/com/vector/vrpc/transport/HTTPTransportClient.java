package com.vector.vrpc.transport;

import com.vector.vrpc.Peer;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;

/**
 * @author smq
 */

public class HTTPTransportClient implements TransportClient {
    private String url;
    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");

            connection.connect();
            IOUtils.copy(data, connection.getOutputStream());

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                return connection.getInputStream();
            }else {
                return connection.getErrorStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
