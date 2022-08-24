package com.cloud.config;

import com.cloud.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Wang
 * @date 2022/8/24
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle {

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public String handleApiException(ApiException apiException) {
        log.error("捕获到接口异常：" + apiException.getMessage());
        return apiException.getMessage();
    }

}
