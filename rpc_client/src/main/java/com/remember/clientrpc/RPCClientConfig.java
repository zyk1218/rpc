package com.remember.clientrpc;

import com.remember.proprc.Peer;
import com.remember.rpccodec.Decoder;
import com.remember.rpccodec.Encoder;
import com.remember.rpccodec.JSONDecoder;
import com.remember.rpccodec.JSONEncoder;
import com.remember.transportrpc.HTTPTransportClient;
import com.remember.transportrpc.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
  * @author remember
  * @date 2020/2/13 21:56
 * RPC Client的配置类
  */
@Data
public class RPCClientConfig {
    /*
    选择哪一个网络模块
     */
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;

    /*
    序列化
     */
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    /*
    反序列化
     */
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    /*
    路由选择
     */
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    /*
    每一个peer建立的连接数
     */
    private int connectCount = 1;

    /*
    peer
     */
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1",3000)
    );

}
