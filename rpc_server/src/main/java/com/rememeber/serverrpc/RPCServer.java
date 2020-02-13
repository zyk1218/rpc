package com.rememeber.serverrpc;

import com.remeber.rpccommon.utils.ReflectionUtil;
import com.remember.proprc.Request;
import com.remember.proprc.Response;
import com.remember.rpccodec.Decoder;
import com.remember.rpccodec.Encoder;
import com.remember.transportrpc.RequestHandler;
import com.remember.transportrpc.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RPCServer {
    private RPCServerConfig rpcServerConfig;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;


    public RPCServer(RPCServerConfig rpcServerConfig){
        this.rpcServerConfig = rpcServerConfig;
        this.serviceInvoker = new ServiceInvoker();
        this.serviceManager = new ServiceManager();
        try {
            //创建net
            this.net = ReflectionUtil.newInstance(rpcServerConfig.getTransportClass());
            this.net.init(rpcServerConfig.getPort(),this.handler);
            //创建编码器
            this.encoder = ReflectionUtil.newInstance(rpcServerConfig.getEncoderClass());
            //创建解码器
            this.decoder = ReflectionUtil.newInstance(rpcServerConfig.getDecoderClass());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    public <T> void register(Class<T> interfaceClass ,T bean){
        serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] inbytes = IOUtils.readFully(recive,recive.available());
                Request request = decoder.decode(inbytes,Request.class);
                log.info("get request:{}",request);
                ServiceInstance service = serviceManager.lookup(request);
                Object invokeValue = serviceInvoker.invoke(service, request);
                response.setData(invokeValue);

            } catch (IOException e) {
                log.warn(e.getMessage(),e);
                response.setCode(1);
                response.setMessage("error message : " + e.getMessage());
            }finally {
                try {
                    byte[] outbytes = encoder.encoder(response);
                    toResp.write(outbytes);
                    log.info("response client ");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }

        }
    };
}
