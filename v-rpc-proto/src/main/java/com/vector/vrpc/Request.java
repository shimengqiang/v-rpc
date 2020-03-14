package com.vector.vrpc;

import lombok.Data;

/**
 * @author smq
 */
@Data
public class Request {
    private  ServiceDescriptor service;
    private Object[] parameters;
}
