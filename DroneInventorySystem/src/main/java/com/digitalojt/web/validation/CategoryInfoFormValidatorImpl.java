package com.digitalojt.web.validation;

import java.util.Locale;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.CategoryInfoForm;

/**
 * 分類情報管理画面のバリデーションチェック 実装クラス
 * 
 * @author takaki
 */
public class CategoryInfoFormValidatorImpl implements ConstraintValidator<CategoryInfoFormValidator, CategoryInfoForm> {

    private static final int MAX_CATEGORY_NAME_LENGTH = 15;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean isValid(CategoryInfoForm form, ConstraintValidatorContext context) {
        if (form == null || form.getCategoryName() == null) {
            addErrorMessage(context, ErrorMessage.CATEGORY_NAME_REQUIRED);
            return false;
        }

        String categoryName = form.getCategoryName().trim();

        // 空欄チェック
        if (categoryName.isEmpty()) {
            addErrorMessage(context, ErrorMessage.CATEGORY_NAME_REQUIRED);
            return false;
        }

        // 半角・全角スペースのみの入力を禁止
        if (categoryName.matches("^[\\s　]+$")) {
            addErrorMessage(context, ErrorMessage.CATEGORY_NAME_FORBIDDEN);
            return false;
        }

        // 文字数チェック
        if (categoryName.length() > MAX_CATEGORY_NAME_LENGTH) {
            addErrorMessage(context, ErrorMessage.CATEGORY_NAME_LENGTH);
            return false;
        }

        // 禁止文字チェック（{ } ; = $ & を含んでいるか）
        if (!categoryName.matches("^[^{};=$&]*$")) {
            addErrorMessage(context, ErrorMessage.CATEGORY_NAME_FORBIDDEN);
            return false;
        }

        return true;
    }

    /**
     * メッセージキーをバリデーションコンテキストに追加
     */
    private void addErrorMessage(ConstraintValidatorContext context, String messageKey) {
        String errorMessage = messageSource.getMessage(messageKey, null, Locale.JAPANESE);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}