package com.remeber.rpccommon.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
  * @author remember
  * @date 2020/2/12 16:27
 * 反射工具类
  */
public class ReflectionUtil {

    /*
    根据class创建对象
     */
    public static <T> T newInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    /*
    获取类的Public方法
     */
    public static Method[] getPublicMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods(); //该方法获得了当前类的所有方法，所以下面需要进行过滤。
        List<Method> publicMethods = new ArrayList<>();
        for(Method method : methods){
            if(Modifier.isPublic(method.getModifiers())){
                publicMethods.add(method);
            }
        }
        return publicMethods.toArray(new Method[0]);
    }

    public static Object invoke(Object o,Method method,Object... args){
        try {
            return method.invoke(o,args); //invoke方法的第一个参数表示要调的哪一个类的该方法，第二个参数是所有的参数。
                                          //如果调用的是一个静态方法，因为静态方法不属于任何对象，所以传入NULL即可
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
