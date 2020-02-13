package com.remember.rpccodec;

/**
  * @author remember
  * @date 2020/2/12 17:19
 * 序列化接口
  */
public interface Encoder {
    byte[] encoder(Object object);
}
