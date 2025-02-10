package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.CategoryInfo;

/**
 * 分類情報テーブルリポジトリー
 *
 * @author takaki
 * 
 */
@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer>{
	/**
	 * 分類情報を取得
	 */
	List<CategoryInfo> findAllByOrderByCategoryIdAsc();
	
	/**
	 * 引数に合致する分類情報を取得を取得
	 * 
	 * @param categoryName
	 * @return paramで検索した結果
	 */
	List<CategoryInfo> findByCategoryName(String categoryName);
}
