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
	 * @param categoryId
	 * @param categoryName 
	 * @return
	 */
	public List<CategoryInfo> getCategoryInfoData(Integer categoryId, String categoryName) {
		return repository.findByCategoryIdAndCategoryName(categoryId, categoryName);
	}
}
