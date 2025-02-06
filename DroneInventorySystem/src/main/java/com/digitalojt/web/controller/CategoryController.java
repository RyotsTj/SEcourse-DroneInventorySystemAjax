package com.digitalojt.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.Category;
import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.ModelAttributeContents;
import com.digitalojt.web.consts.UrlConsts;

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
	
    /**
     * 初期表示
     *
     * @return String(path)
     */
    @GetMapping(UrlConsts.CATEGORY_LIST)
    public String index(Model model) {
        logStart(LogMessage.HTTP_GET);

        List<String> categoryNames = Arrays.stream(Category.values())
                .map(Category::getName)
                .collect(Collectors.toList());

        model.addAttribute(ModelAttributeContents.CATEGORY_NAMES, categoryNames);
        return UrlConsts.CATEGORY_LIST_INDEX;
    }
}
