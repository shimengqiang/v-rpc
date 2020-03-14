package com.vector.vrpc;

import com.vector.vrpc.common.utils.ReflectionUtils;

/**
 * @author smq
 */

public class ServiceInvoker {

    public Object invode(ServiceInstance serviceInstance, Request request){
        return ReflectionUtils.invoke(serviceInstance.getTarget(),
            serviceInstance.getMethod(),
            request.getParameters());
    }
}
