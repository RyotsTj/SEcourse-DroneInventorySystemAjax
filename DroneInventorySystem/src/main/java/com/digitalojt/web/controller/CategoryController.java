package com.digitalojt.web.controller;

import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

        model.addAttribute(ModelAttributeContents.CATEGORY_LIST, service.getCategoryInfoData().stream()
                .map(CategoryInfo::getCategoryName)
                .collect(Collectors.toList()));
        
        logEnd(LogMessage.HTTP_GET);
        return UrlConsts.CATEGORY_LIST_INDEX;
    }
    
	/**
	 * 検索結果表示
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping(UrlConsts.CATEGORY_LIST_SEARCH)
	public String search(Model model, @Valid CategoryInfoForm form, BindingResult bindingResult, String categoryName) {
		logStart(LogMessage.HTTP_POST);
		
		// Valid項目チェック
		if (bindingResult.hasErrors()) {
			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute(LogMessage.FLASH_ATTRIBUTE_ERROR, errorMsg);
			logValidationError(LogMessage.HTTP_POST, form + errorMsg);
			return UrlConsts.CATEGORY_LIST_INDEX;
		}
		
		// 分類情報取得処理(分類情報管理画面に表示するデータを取得)
		// 画面表示用に商品情報リストをセット
		model.addAttribute(ModelAttributeContents.CATEGORY_LIST, service.getCategoryInfoData(categoryName).stream()
                .map(CategoryInfo::getCategoryName)
                .collect(Collectors.toList()));
		
		logEnd(LogMessage.HTTP_POST);
		return UrlConsts.CATEGORY_LIST_INDEX; 
	}
}
