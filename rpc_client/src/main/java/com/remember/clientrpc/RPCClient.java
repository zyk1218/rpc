package com.remember.clientrpc;


import com.remeber.rpccommon.utils.ReflectionUtil;
import com.remember.rpccodec.Decoder;
import com.remember.rpccodec.Encoder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

@Slf4j
public class RPCClient {
    private RPCClientConfig rpcClientConfig;
    private Decoder decoder;
    private Encoder encoder;
    private TransportSelector selector;

    public RPCClient () {
        this(new RPCClientConfig());
    }

    public RPCClient(RPCClientConfig config) {
        this.rpcClientConfig = config;
        try {
            this.encoder = ReflectionUtil.newInstance(config.getEncoderClass());
            this.decoder = ReflectionUtil.newInstance(config.getDecoderClass());
            this.selector = ReflectionUtil.newInstance(config.getSelectorClass());
            this.selector.init(config.getServers(),config.getConnectCount(),config.getTransportClass());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /*
    获取代理对象
     */
    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
          getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz,encoder,decoder,selector)
        );
    }
}
