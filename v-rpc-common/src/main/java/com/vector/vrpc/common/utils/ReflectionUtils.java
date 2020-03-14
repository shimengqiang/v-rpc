package com.vector.vrpc.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.soap.MTOM;

/**
 * @author smq
 */

public class ReflectionUtils {

    public static <T> T newInstance(Class<T> tClass){
        try {
            return tClass.newInstance();
        }catch (Exception e){
            throw new IllegalStateException(e);
        }
    }
    public static Method[] getPublicMethods(Class clazz ){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pmethods = new ArrayList<Method>();
        for (Method method : methods) {
            if (Modifier.isPublic((method.getModifiers()))){
                pmethods.add(method);
            }
        }
        return pmethods.toArray(new Method[0]);
    }
    public static Object invoke(Object obj, Method method, Object... args){
        try {
            return method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
