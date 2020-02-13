package com.rememeber.serverrpc;

import com.remember.rpccodec.Decoder;
import com.remember.rpccodec.Encoder;
import com.remember.rpccodec.JSONDecoder;
import com.remember.rpccodec.JSONEncoder;
import com.remember.transportrpc.HTTPTransportServer;
import com.remember.transportrpc.TransportServer;
import lombok.Data;

/**
  * @author remember
  * @date 2020/2/13 16:13
 * Server配置。
 *  需要配置的东西
 *      1.使用的网络模块
 *      2.使用的序列化模块
 *      3.监听的端口
  */

@Data
public class RPCServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private int port = 3000;



}
