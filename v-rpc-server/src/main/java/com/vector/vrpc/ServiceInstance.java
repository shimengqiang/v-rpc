package com.vector.vrpc;

import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 服务实例
 *
 * @author smq
 */
@Data
@AllArgsConstructor
public class ServiceInstance {

    private Object target;
    private Method method;

}
