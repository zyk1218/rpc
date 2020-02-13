package com.remeber.rpccommon.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilTest {
    @Test
    public void newInstance() throws Exception {
        TestClass t = ReflectionUtil.newInstance(TestClass.class);
        assertNotNull(t);
    }

    @Test
    public void getPublicMethods() throws Exception {
        Method[] methods = ReflectionUtil.getPublicMethods(TestClass.class);
        assertEquals(1,methods.length);
        System.out.println(methods[0].getName());
    }

    @Test
    public void invoke() throws Exception {
        Method[] methods = ReflectionUtil.getPublicMethods(TestClass.class);
        TestClass testClass = new TestClass();
        System.out.println(ReflectionUtil.invoke(testClass,methods[0]));
    }

}