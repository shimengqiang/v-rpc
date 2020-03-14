package com.vector.vrpc;

import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务
 *
 * @author smq
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;
    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getTypeName());
        Class<?>[] parameterTypesClass = method.getParameterTypes();
        String[] parameterTypes = new String[parameterTypesClass.length];

        for (int i = 0; i < parameterTypesClass.length; i++) {
            parameterTypes[i] = parameterTypesClass[i].getName();
        }
        serviceDescriptor.setParameterTypes(parameterTypes);
        return serviceDescriptor;
    }

    @Override public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        ServiceDescriptor descriptor = (ServiceDescriptor) obj;
        return this.toString().equals(descriptor.toString());
    }

    @Override public String toString() {
        return clazz + method+ returnType + Arrays.toString(parameterTypes);
    }
}
