package com.remember.proprc;

import lombok.Data;

/**
  * @author remember
  * @date 2020/2/12 16:21
 * 表示RPC的一个请求
  */
@Data
public class Request {

    //请求的服务的类型
    private ServiceDescriptor service;

    //参数
    private Object[] parameters;
}
