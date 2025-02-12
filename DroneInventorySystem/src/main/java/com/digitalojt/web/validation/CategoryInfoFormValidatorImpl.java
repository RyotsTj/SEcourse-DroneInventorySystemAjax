package com.digitalojt.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.CategoryInfoForm;

/**
 * 分類情報管理画面のバリデーションチェック 実装クラス
 * 
 * @author takaki
 */
public class CategoryInfoFormValidatorImpl implements ConstraintValidator<CategoryInfoFormValidator, CategoryInfoForm> {

    // 禁止文字の定数宣言（@Pattern でカバーできないもの）
	private static final String[] FORBIDDEN_CHARACTERS = { " ", "　", "{", "}", "(", ")", "'", "*", ";", "$", "=", "&" };
	
    @Override
    public boolean isValid(CategoryInfoForm form, ConstraintValidatorContext context) {
    	
        if (form == null || form.getCategoryName() == null) {
            return true;
        }

        String categoryName = form.getCategoryName().trim();

        // 禁止文字チェック（{ } ; = $ & を含んでいるか）
        for (String forbiddenChar : FORBIDDEN_CHARACTERS) {
            if (categoryName.contains(forbiddenChar)) {
                addErrorMessage(context, ErrorMessage.CATEGORY_NAME_FORBIDDEN);
                return false;
            }
        }

        return true;
    }

    /**
     * エラーメッセージをコンテキストに追加
     * 
     * @param context バリデーションコンテキスト
     * @param message エラーメッセージ
     */
    private void addErrorMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addConstraintViolation();
    }
}