package com.rememeber.serverrpc;

import com.remeber.rpccommon.utils.ReflectionUtil;
import com.remember.proprc.Request;

/**
  * @author remember
  * @date 2020/2/13 17:50
 * 调用具体服务
  */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtil.invoke(service.getTarget(),service.getMethod(),request.getParameters());
    }
}
