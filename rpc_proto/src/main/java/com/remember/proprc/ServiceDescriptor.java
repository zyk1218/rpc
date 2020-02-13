package com.remember.proprc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
  * @author remember
  * @date 2020/2/12 16:16
 * 表示服务
  */

@Data
@AllArgsConstructor
@NoArgsConstructor //这个注解的意思是创建一个不带任何参数的构造方法。
public class ServiceDescriptor {

    //类名
    private String clazz;

    //方法名
    private String method;

    //返回值类型
    private String returnType;

    //参数
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class[] parameterClass = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClass.length];
        for (int i = 0 ;i < parameterClass.length ; i++)
            parameterTypes[i] = parameterClass[i].getName();
        sdp.setParameterTypes(parameterTypes);

        return sdp;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
         if (this == obj) return true;
         if(obj == null || getClass() != obj.getClass()) return false;

         ServiceDescriptor that = (ServiceDescriptor) obj;
         return that.toString().equals(this.toString());
    }

    @Override
    public String toString() {
        return "clazz="+clazz + "method="+method + "returnType="+returnType + "parameterTypes="+Arrays.toString(parameterTypes);
    }
}
