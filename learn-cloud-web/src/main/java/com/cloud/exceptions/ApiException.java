package com.cloud.exceptions;

/**
 * @author Wang
 * @date 2022/8/24
 */
public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}
