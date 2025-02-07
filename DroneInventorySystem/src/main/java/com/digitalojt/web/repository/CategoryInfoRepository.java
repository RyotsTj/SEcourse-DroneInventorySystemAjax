package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	 * 引数に合致する分類情報を取得
	 * 
	 * @param categoryId
	 * @param categoryName 
	 *
	 */
    @Query("SELECT c FROM CategoryInfo c ORDER BY c.categoryName ASC")
    List<CategoryInfo> findByCategoryIdAndCategoryName(Integer categoryId, String categoryName);
}
