package com.remember.transportrpc;

import com.remember.proprc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
  * @author remember
  * @date 2020/2/13 13:37
 * HTTPClient
 * RPC底层可以基于HTTP也可以基于TCP
  */
public class HTTPTransportClient implements TransportClient {

    private String url;

    @Override
    public void connect(Peer perr) {
        this.url = "https://"+perr.getHost()+":"+perr.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);

            urlConnection.connect();
            IOUtils.copy(data,urlConnection.getOutputStream());

            int code = urlConnection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){
                return urlConnection.getInputStream();
            }else {
                return urlConnection.getErrorStream();
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
