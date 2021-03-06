package com.boot.cloudadmin.common.validator;

import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.contants.GlobalContants;
import com.boot.cloudadmin.common.exception.GlobalException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2016-03-13 15:50
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws GlobalException  校验不通过，则报RRException异常
     */
    public static R validateEntity(Object object, Class<?>... groups)
            throws GlobalException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<Object> constraint:  constraintViolations){
                return R.error(GlobalContants.VALIDATOR_CODE,constraint.getMessage());
            }
        }
        return R.ok();
    }
}
