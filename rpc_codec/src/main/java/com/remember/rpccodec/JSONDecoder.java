package com.remember.rpccodec;

import com.alibaba.fastjson.JSON;

/**
  * @author remember
  * @date 2020/2/13 11:41
 * 基于JSON的反序列化
  */
public class JSONDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
