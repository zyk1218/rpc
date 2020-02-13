package com.rememeber.serverrpc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
  * @author remember
  * @date 2020/2/13 16:28
 * 具体服务
 *  1.该服务由哪个对象提供
 *  2.该对象哪个方法暴露为服务
  */

@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;

    private Method method;
}
