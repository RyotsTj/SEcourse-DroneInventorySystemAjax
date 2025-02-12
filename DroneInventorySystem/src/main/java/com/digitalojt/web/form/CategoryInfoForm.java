package com.digitalojt.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.validation.CategoryInfoFormValidator;

import lombok.Data;

/**
 * 分類情報管理画面のフォームクラス
 *
 * @author takaki
 *
 * @NotBlank 必須入力（空欄・スペースの禁止）
 * @Size 文字数制限（最大15文字）
 *
 */
@Data
@CategoryInfoFormValidator
public class CategoryInfoForm {
    @NotBlank(message = ErrorMessage.CATEGORY_NAME_REQUIRED)
    @Size(max = 15, message = ErrorMessage.CATEGORY_NAME_INVALID_LENGTH)
    private String categoryName;
}
