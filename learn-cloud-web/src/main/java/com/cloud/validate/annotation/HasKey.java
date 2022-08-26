package com.cloud.validate.annotation;

import com.cloud.validate.HasKeyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.*;

/**
 * 自定义注解
 * <code>@Constraint</code> 指定解析注解的处理类
 *
 * @author Wang
 * @date 2022/8/26
 */
@Documented
@Constraint(validatedBy = HasKeyValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasKey {

    String message() default "{com.cloud.validate.annotation.HasKey.message}";
    Class<?>[] groups() default {};
    String[] values() default {};
    Class<? extends Payload>[] payload() default {};

}
