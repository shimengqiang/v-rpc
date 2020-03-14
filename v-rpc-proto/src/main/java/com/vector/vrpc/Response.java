package com.vector.vrpc;

import lombok.Data;

/**
 * @author smq
 */
@Data
public class Response {
    /**
     * 0 成功
     */
    private int code = 0;
    private String message = "ok";
    private Object data;
}
