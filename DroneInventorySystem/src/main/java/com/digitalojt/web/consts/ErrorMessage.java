package com.digitalojt.web.consts;

/**
 * エラーメッセージ定数クラス
 * 
 * @author dotlife
 *
 */
public class ErrorMessage {
	
	// ログイン情報の入力に誤りがあった場合に、出力するエラーメッセージのID
	public static final String  LOGIN_WRONG_INPUT = "login.wrongInput";

	// すべての項目が空の場合のエラーメッセージ
	public static final String ALL_FIELDS_EMPTY_ERROR_MESSAGE = "allField.empty";

	// 空文字検索に関するエラーメッセージ
	public static final String UNEXPECTED_INPUT_ERROR_MESSAGE = "unexpected.input";

	// 不正な文字列を使用した検索に関するエラーメッセージ
	public static final String INVALID_INPUT_ERROR_MESSAGE = "invalid.input";

	// 文字超過に関するエラーメッセージ
	public static final String CENTER_NAME_LENGTH_ERROR_MESSAGE = "centerName.length.wrongInput";
	
    // 禁止文字が含まれている場合のエラー
    public static final String CENTER_NAME_FORBIDDEN_ERROR_MESSAGE = "centerName.name.forbidden";

    // 必須項目が未設定の場合のエラー
    public static final String CENTER_NAME_FIELDS_EMPTY_MESSAGE = "centerName.requiredFields.empty";
    
    // 半角数字＋ハイフンのフォーマットエラー
    public static final String CENTER_NAME_HALFWIDTH_HYPHEN_ERROR_MESSAGE = "centerName.halfWidth.numbers.hyphen";
    
    // 半角数字のみのフォーマットエラー
    public static final String CENTER_NAME_HALFWIDTH_ERROR_MESSAGE = "centerName.halfWidth.numbers";
    
    // 予期せぬエラー
    public static final String CENTER_UNEXPECTED_ERROR_MESSAGE = "centerName.unexpected.error";
	
	// 空欄の場合のエラーメッセージキー
	public static final String CATEGORY_NAME_REQUIRED = "category.name.required";
	
	// 禁止文字チェック（{ } ; = $ & ）が含まれている場合のエラーメッセージキー
	public static final String CATEGORY_NAME_FORBIDDEN = "category.name.forbidden";
	
	// 文字数が制限を超えた場合のエラーメッセージキー
	public static final String CATEGORY_NAME_INVALID_LENGTH = "category.name.length";
	
}
