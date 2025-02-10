package com.digitalojt.web.controller;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.ModelAttributeContents;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.service.CategoryInfoService;

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
}
