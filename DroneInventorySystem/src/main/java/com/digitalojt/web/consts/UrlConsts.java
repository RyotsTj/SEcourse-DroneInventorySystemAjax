package com.digitalojt.web.consts;

/**
 * URL定数クラス
 *
 * @author dotlife
 * 
 */
public class UrlConsts {

	// ログイン
	public static final String LOGIN = "/login";

	// ログイン初期表示
	public static final String LOGIN_INDEX = "admin/login/index";

	// エラー
	public static final String ERROR = "/error";
	
	// 認証
	public static final String AUTHENTICATE = "/authenticate";
	
	// 在庫一覧画面
	public static final String STOCK_LIST = "/admin/stockList";

	// 在庫一覧画面 初期
	public static final String STOCK_LIST_INDEX = "admin/stockList/index";
	
	//分類情報管理画面
	public static final String CATEGORY_LIST = "/admin/categoryList";
	
	//分類情報管理画面　テンプレート名
	public static final String CATEGORY_LIST_INDEX = "admin/categoryList/index";
	
	//分類情報管理画面 検索
	public static final String CATEGORY_LIST_SEARCH = "/admin/categoryList/search";

	// 在庫一覧画面 検索
	public static final String STOCK_LIST_SEARCH = "/admin/stockList/search";
	
	// 在庫センター情報画面
	public static final String  CENTER_INFO = "/admin/centerInfo";

	// 在庫センター情報画面初期表示
	public static final String  CENTER_INFO_INDEX = "/admin/centerInfo/index";
	
	// 在庫センター情報画面 検索
	public static final String CENTER_INFO_SEARCH = "/admin/centerInfo/search";
	
	// 在庫センター情報画面 登録
	public static final String CENTER_INFO_REGISTER = "/admin/centerInfo/register";
	
	// 在庫センター情報画面 更新
	public static final String CENTER_INFO_UPDATE = "/admin/centerInfo/update";
	
	// 在庫センター情報画面 更新
	public static final String CENTER_INFO_UPDATE_INFO = "/admin/centerInfo/update/info";
	
	// 在庫センター情報画面 削除
	public static final String CENTER_INFO_DELETE = "/admin/centerInfo/delete";
	
	// 操作履歴画面
	public static final String  OPERATION_LOG = "/admin/operationLog";

	// 操作履歴画面初期画面
	public static final String  OPERATION_LOG_INDEX = "admin/operationLog/index";

	// 操作履歴画面 検索
	public static final String  OPERATION_LOG_SEARCH = "/admin/operationLog/search";
	
	// 認証不要画面
	public static final String[] NO_AUTHENTICATION = {LOGIN, AUTHENTICATE};
	
	// エラー
	public static final String  ERROR_VIEW = "error/error";

}
