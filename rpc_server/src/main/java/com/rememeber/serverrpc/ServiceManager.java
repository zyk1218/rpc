package com.rememeber.serverrpc;

import com.remeber.rpccommon.utils.ReflectionUtil;
import com.remember.proprc.Request;
import com.remember.proprc.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
  * @author remember
  * @date 2020/2/13 16:30
 * Service管理类：管理RPC暴露的服务
 *  1.注册服务
 *  2.查找服务
  */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor,ServiceInstance> services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }


    public <T> void register(Class<T> interfaceClass ,T bean){
        Method[] publicMethods = ReflectionUtil.getPublicMethods(interfaceClass);
        for(Method method:publicMethods){
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass,method);
            services.put(serviceDescriptor,serviceInstance);
            log.info("register service :{} {}",serviceDescriptor.getClazz(),serviceDescriptor.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor serviceDescriptor = request.getService();
        return services.get(serviceDescriptor);
    }

}
