package com.cloud.validate;

import com.cloud.validate.annotation.HasKey;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Set;

/**
 * @author Wang
 * @date 2022/8/26
 */
public class HasKeyValidator implements ConstraintValidator<HasKey, Map<String, Object>> {

    private String[] keys = new String[]{};

    @Override
    public void initialize(HasKey hasKey) {
        keys = hasKey.values();
    }

    @Override
    public boolean isValid(Map<String, Object> map, ConstraintValidatorContext constraintValidatorContext) {
        if (map != null) {
            Set<?> objects = map.keySet();
            for (String key : keys) {
                if (!objects.contains(key)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
