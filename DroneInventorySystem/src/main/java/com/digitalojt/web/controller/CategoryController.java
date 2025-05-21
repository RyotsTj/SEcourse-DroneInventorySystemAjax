package com.digitalojt.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.ModelAttributeContents;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.form.CategoryInfoForm;
import com.digitalojt.web.service.CategoryInfoService;
import com.digitalojt.web.util.MessageManager;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報管理画面コントローラークラス
 * 
 * @author takaki
 *
 */
@Controller
@RequiredArgsConstructor
public class CategoryController extends AbstractController {
	
	/** 分類情報 サービス */
	private final CategoryInfoService service;
	
	/** メッセージソース */
	private final MessageSource messageSource;
	
    /**
     * 分類情報テーブルから取得したデータを使用して画面の初期表示を実行する。
     *
     * @param model
     * @return String(Viewの名前：分類情報管理画面)
     */
    @GetMapping(UrlConsts.CATEGORY_LIST)
    public String index(Model model) {
        logStart(LogMessage.HTTP_GET);

        model.addAttribute(ModelAttributeContents.CATEGORY_LIST, service.getCategoryInfoData());
        
        logEnd(LogMessage.HTTP_GET);
        return UrlConsts.CATEGORY_LIST_INDEX;
    }
    
	/**
	 * 検索結果表示
	 * 
	 * @param model Modelオブジェクト
	 * @param form バリデーション対象のフォームオブジェクト
	 * @param bindingResult バリデーション結果
	 * @return 分類情報管理画面のビュー名
	 */
    @GetMapping(UrlConsts.CATEGORY_LIST_SEARCH)
	public String search(Model model, @Valid CategoryInfoForm form, BindingResult bindingResult) {
		logStart(LogMessage.HTTP_POST);
		
		// Valid項目チェック
		if (bindingResult.hasErrors()) {
	        // すべてのエラーメッセージを取得して1つの文字列に変換
	        String errorMsg = bindingResult.getAllErrors().stream()
	                .map(error -> MessageManager.getMessage(messageSource, error.getDefaultMessage()))
	                .collect(Collectors.joining("<br>")); 
			
			model.addAttribute(LogMessage.FLASH_ATTRIBUTE_ERROR, errorMsg);
			logValidationError(LogMessage.HTTP_POST, bindingResult.getFieldErrors().toString());
			
			model.addAttribute(ModelAttributeContents.CATEGORY_LIST, service.getCategoryInfoData());
			
			return UrlConsts.CATEGORY_LIST_INDEX;
		}
		
		// 分類情報取得処理(分類情報管理画面に表示するデータを取得)
		List<CategoryInfo> searchResults = service.getCategoryInfoData(form.getCategoryName());
		
	    // 検索結果が空の場合、メッセージを表示
	    if (searchResults.isEmpty()) {
	        model.addAttribute(LogMessage.FLASH_ATTRIBUTE_ERROR, "※データがありません。");
	        logValidationError(LogMessage.HTTP_POST, "※データがありません。");
	        model.addAttribute(ModelAttributeContents.CATEGORY_LIST, service.getCategoryInfoData());
	        return UrlConsts.CATEGORY_LIST_INDEX;
	    }
	    
		// 画面表示用にモデルへ分類情報リストをセット
		model.addAttribute(ModelAttributeContents.CATEGORY_LIST, searchResults);
		
		logEnd(LogMessage.HTTP_POST);
		return UrlConsts.CATEGORY_LIST_INDEX; 
	}
}
