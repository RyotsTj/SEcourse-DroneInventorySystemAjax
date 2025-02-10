package com.digitalojt.web.form;

import com.digitalojt.web.validation.CategoryInfoFormValidator;

import lombok.Data;

/**
 * 分類情報管理画面のフォームクラス
 * 
 * @author takaki
 *
 */
@Data
@CategoryInfoFormValidator
public class CategoryInfoForm {
    private String categoryName;
}
