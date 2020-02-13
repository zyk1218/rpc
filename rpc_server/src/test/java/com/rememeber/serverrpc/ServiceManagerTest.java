package com.rememeber.serverrpc;

import com.remeber.rpccommon.utils.ReflectionUtil;
import com.remember.proprc.Request;
import com.remember.proprc.ServiceDescriptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.TestCase.assertNotNull;


public class ServiceManagerTest {
    ServiceManager serviceManager;

    @Before
    public void init(){
        serviceManager = new ServiceManager();

    }

    @Test
    public void register()   {
        TestInterface bean = new TestClasss();
        serviceManager.register(TestInterface.class,bean);
    }

    @Test
    public void lookup(){
        TestInterface bean = new TestClasss();
        serviceManager.register(TestInterface.class,bean);
        Method method = ReflectionUtil.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class,method);
        Request request = new Request();
        request.setService(serviceDescriptor);

        ServiceInstance lookup = serviceManager.lookup(request);

        assertNotNull(lookup);
    }

}