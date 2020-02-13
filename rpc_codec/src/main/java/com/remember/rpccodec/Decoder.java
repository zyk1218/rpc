package com.remember.rpccodec;

/**
  * @author remember
  * @date 2020/2/12 17:19
 * 反序列化接口
  */
public interface Decoder {
    <T> T decode(byte[] bytes,Class<T> clazz);
}
