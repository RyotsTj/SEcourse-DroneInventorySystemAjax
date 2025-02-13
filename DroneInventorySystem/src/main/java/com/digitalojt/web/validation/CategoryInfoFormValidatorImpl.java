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

    // 禁止文字の定数宣言（@でカバーできないもの）
	private static final String FORBIDDEN_CHARACTERS = " 　{}()'*;=$&";
	
    @Override
    public boolean isValid(CategoryInfoForm form, ConstraintValidatorContext context) {
    	
        if (form == null || form.getCategoryName() == null) {
            return true;
        }

        // 禁止文字チェック
        if (forbiddenCharacterExists(form.getCategoryName().trim())) {
            addErrorMessage(context, ErrorMessage.CATEGORY_NAME_FORBIDDEN);
            return false;
        }

        return true;
    }
    
    /**
     * 禁止文字が含まれているかを判定
     * 
     * @param input 判定対象の文字列
     * @return 禁止文字が含まれている場合は true
     */
    private boolean forbiddenCharacterExists(String input) {
        for (char c : FORBIDDEN_CHARACTERS.toCharArray()) {
            if (input.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
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