package com.vector.vrpc.common.utils;

import java.lang.reflect.Method;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestClass aClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(aClass);
    }

    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1, methods.length);
        String name = methods[0].getName();
        assertEquals("b", name);
    }
    @Test
    public void invoke(){
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method method0 = methods[0];
        TestClass testClass = new TestClass();
        Object invoke = ReflectionUtils.invoke(testClass, method0);
        assertEquals("b", invoke);
        Method method1 = methods[1];
        Object invoke1 = ReflectionUtils.invoke(null, method1);
        assertEquals("d", invoke1);

    }
}