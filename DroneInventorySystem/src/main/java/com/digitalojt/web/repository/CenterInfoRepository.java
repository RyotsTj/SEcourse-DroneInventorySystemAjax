package com.digitalojt.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.CenterInfo;

/**
 * センター情報テーブルリポジトリー
 *
 * @author dotlife
 * 
 */
@Repository
public interface CenterInfoRepository extends JpaRepository<CenterInfo, Integer> {

	/**
	 * 引数に合致する在庫センター情報を取得
	 * 
	 * @param centerName
	 * @param region
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM CenterInfo s WHERE " +
			"(:centerName = '' OR s.centerName LIKE %:centerName%) AND " +
			"(:region = '' OR s.address LIKE %:region%) AND " +
			"(s.operationalStatus = 0)")
	List<CenterInfo> findByCenterNameAndRegionAndStorageCapacity(
			String centerName,
			String region);
	
	/**
	 * センターIDを使ってセンター情報を取得
	 * 
	 * @param centerId
	 * @return centerIdで検索した在庫センター情報結果
	 */
	Optional<CenterInfo> findById(int centerId);
}
