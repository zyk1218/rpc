package com.remember.transportrpc;

import com.remember.proprc.Peer;

import java.io.InputStream;

/**
  * @author remember
  * @date 2020/2/13 13:15
 * 客户端：
 *  1.创建链接
 *  2.发送数据，并且等待响应
 *  3.关闭链接
  */
public interface TransportClient {
    void connect(Peer perr);

    InputStream write(InputStream data);

    void close();
}
