package com.remember.transportrpc;

import com.remember.proprc.Peer;

import java.io.InputStream;

/**
  * @author remember
  * @date 2020/2/13 13:15
 * 服务端
 * 1.启动，监听端口
 * 2.收到请求并处理
 * 3.关闭监听
  */
public interface TransportServer {

    void init(int port,RequestHandler handler);

    void start();

    void stop();

}
