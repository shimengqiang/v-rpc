package com.vector.vrpc;

import com.vector.vrpc.common.utils.ReflectionUtils;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    ServiceManager serviceManager;
    @Before
    public void init(){
        serviceManager = new ServiceManager();
    }
    @Test
    public void register() {
        TestInterface testInterface = new TestInterfaceImpl();
        serviceManager.register(TestInterface.class, testInterface);

    }

    @Test
    public void lockup() {
        TestInterface testInterface = new TestInterfaceImpl();
        serviceManager.register(TestInterface.class, testInterface);

        Method[] methods = ReflectionUtils.getPublicMethods(TestInterface.class);
        Method method = methods[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class, method);
        Request request = new Request();
        request.setService(serviceDescriptor);
        ServiceInstance lockup = serviceManager.lockup(request);
        assertNotNull(lockup);

    }
}