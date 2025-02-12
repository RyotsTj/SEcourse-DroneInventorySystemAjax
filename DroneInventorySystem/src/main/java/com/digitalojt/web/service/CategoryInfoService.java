package com.digitalojt.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.repository.CategoryInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報画面のサービスクラス
 *
 * @author takaki
 * 
 */
@Service
@RequiredArgsConstructor
public class CategoryInfoService {
	
	/** 分類情報テーブル リポジトリー */
	private final CategoryInfoRepository repository;

	/**
	 * 引数に合致する分類情報を取得
	 * 
	 * @return
	 */
    public List<CategoryInfo> getCategoryInfoData() {
        return repository.findAllByOrderByCategoryIdAsc();
	}
    
	/**
	 * 分類情報テーブル 名前検索
	 * 
	 * @param categoryName 名前
	 * @return 分類情報テーブルを名前検索した結果
	 */
    public List<CategoryInfo> getCategoryInfoData(String categoryName) {
        return repository.findByCategoryNameOrderByCategoryIdAsc(categoryName);
	}
}
