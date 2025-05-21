package com.digitalojt.web.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import com.digitalojt.web.consts.ErrorMessage;

/**
 * 在庫センター画面 更新/削除画面のバリデーションチェック インターフェース
 * 
 * @author takaki
 */
@Constraint(validatedBy = CenterInfoUpdateFormValidatorImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CenterInfoUpdateFormValidator {
    
	String message() default ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
