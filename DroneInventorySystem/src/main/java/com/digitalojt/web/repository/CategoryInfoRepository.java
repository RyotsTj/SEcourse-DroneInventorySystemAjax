package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	 * categoryNameで検索し、結果をcategoryIdの昇順でソートする
	 * 
	 * @param categoryName
	 * @return paramで検索した結果
	 */
	@Query("SELECT c FROM CategoryInfo c WHERE c.categoryName = :categoryName ORDER BY c.categoryId ASC")
	List<CategoryInfo> findByCategoryName(@Param("categoryName") String categoryName);
}
