package com.vector.vrpc;

import com.vector.vrpc.common.utils.ReflectionUtils;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理rpc暴露的服务
 *
 * @author smq
 */
@Slf4j
public class ServiceManager {

    /**
     * 注册的服务
     */
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceCalss, T bean){
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceCalss);
        for (Method method : methods) {
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceCalss, method);
            services.put(serviceDescriptor, serviceInstance);
        }
        log.info(services.toString());
    }
    public ServiceInstance lockup(Request request){
        ServiceDescriptor serviceDescriptor = request.getService();
        return services.get(serviceDescriptor);
    }
}
