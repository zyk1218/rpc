package com.remember.transportrpc;

import java.io.InputStream;
import java.io.OutputStream;

/**
  * @author remember
  * @date 2020/2/13 13:23
 * 处理网络请求的Handler
  */
public interface RequestHandler {
    void onRequest(InputStream recive, OutputStream toResp);
}
