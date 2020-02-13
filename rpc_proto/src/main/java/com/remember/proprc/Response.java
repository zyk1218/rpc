package com.remember.proprc;

import lombok.Data;

/**
  * @author remember
  * @date 2020/2/12 16:22
 * 表示RPC的返回
  */
@Data
public class Response {

    //服务返回编码 0-成功 非0失败
    private int code;

    //具体错误信息
    private String message;

    //返回的数据
    private Object data;
    
    
}
