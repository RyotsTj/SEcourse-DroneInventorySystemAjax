package com.digitalojt.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.CenterInfoNewRegistrationForm;
/**
 * 在庫センター登録フォームのバリデーションチェック
 */
public class CenterInfoNewRegistrationFormValidatorImpl implements ConstraintValidator<CenterInfoNewRegistrationFormValidator, CenterInfoNewRegistrationForm> {
    // 禁止文字のリスト
    private static final String FORBIDDEN_CHARACTERS = " 　{}()'*;=$&";

    @Override
    public boolean isValid(CenterInfoNewRegistrationForm form, ConstraintValidatorContext context) {
        if (form == null) {
            return true;
        }
        boolean valid = true;

        // センター名の禁止文字チェック
        if (form.getCenterName() != null && containsForbiddenCharacters(form.getCenterName())) {
            addErrorMessage(context, ErrorMessage.CENTER_NAME_FORBIDDEN_ERROR_MESSAGE);
            valid = false;
        }

        // 備考の禁止文字チェック
        if (form.getNotes() != null && containsForbiddenCharacters(form.getNotes())) {
            addErrorMessage(context, ErrorMessage.CENTER_UNEXPECTED_ERROR_MESSAGE);
            valid = false;
        }

        return valid;
    }

    /**
     * 文字列に禁止文字が含まれているかを判定
     */
    private boolean containsForbiddenCharacters(String input) {
        for (char c : FORBIDDEN_CHARACTERS.toCharArray()) {
            if (input.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * エラーメッセージを追加
     */
    private void addErrorMessage(ConstraintValidatorContext context, String messageKey) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("{" + messageKey + "}")
               .addConstraintViolation();
    }
}
