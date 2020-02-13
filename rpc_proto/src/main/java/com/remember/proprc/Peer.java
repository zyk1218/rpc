package com.remember.proprc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
  * @author remember
  * @date 2020/2/12 16:11
 * 表示网络传输的一个端点
  */


@Data
@AllArgsConstructor //这个注解的意思是创建一个带所有参数的构造方法。
public class Peer {

    //远程主机的地址
    private String host;

    //端口
    private int port;
}
