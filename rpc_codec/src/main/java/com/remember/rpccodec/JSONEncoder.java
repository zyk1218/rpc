package com.remember.rpccodec;
import com.alibaba.fastjson.JSON;

/**
  * @author remember
  * @date 2020/2/13 11:40
 * 基于json的序列化
  */
public class JSONEncoder implements Encoder {
    @Override
    public byte[] encoder(Object object) {
        return JSON.toJSONBytes(object);
    }
}
