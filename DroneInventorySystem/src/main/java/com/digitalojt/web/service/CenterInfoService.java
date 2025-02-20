package com.digitalojt.web.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoNewRegistrationForm;
import com.digitalojt.web.form.CenterInfoUpdateForm;
import com.digitalojt.web.repository.CenterInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のサービスクラス
 *
 * @author dotlife
 * 
 */
@Service
@RequiredArgsConstructor
public class CenterInfoService {

	/** センター情報テーブル リポジトリー */
	private final CenterInfoRepository repository;

	/**
	 * 在庫センター情報を全建検索で取得
	 * 
	 * @return
	 */
	public List<CenterInfo> getCenterInfoData() {
		return repository.findAll();
	}

	/**
	 * 引数に合致する在庫センター情報を取得
	 * 
	 * @param centerName
	 * @param region 
	 * @return
	 */
	public List<CenterInfo> getCenterInfoData(String centerName, String region) {
		return repository.findByCenterNameAndRegionAndStorageCapacity(centerName, region);
	}
	
    /**
     * 指定されたIDの在庫センター情報を取得
     * @param centerName センター名
     * @return CenterInfo
     * @throws Exception センター情報が見つからない場合
     */
    public CenterInfo getCenterInfoById(int centerId) {
        return repository.findById(centerId).get();
    }
	
	/**
	 * 在庫センター情報を新規登録する
	 * 
	 * @param centerInfo data
	 */
    @Transactional
    public void registerCenterInfo(CenterInfoNewRegistrationForm form) {
    	CenterInfo entity = new CenterInfo();
        entity.setCenterName(form.getCenterName());
        entity.setPostCode(form.getPostCode());
        entity.setAddress(form.getAddress());
        entity.setPhoneNumber(form.getPhoneNumber());
        entity.setManagerName(form.getManagerName());
        entity.setOperationalStatus(form.getOperationalStatus());
        entity.setMaxStorageCapacity(form.getMaxStorageCapacity());
        entity.setCurrentStorageCapacity(form.getCurrentStorageCapacity());
        entity.setNotes(form.getNotes());
        entity.setDeleteFlag(0);
        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        entity.setCreateDate(currentTimestamp);
        entity.setUpdateDate(currentTimestamp);

        repository.save(entity);
    }
    
	/**
	 * centerIdに紐づいた在庫センター情報を更新する
	 * 
	 * @param centerName
	 * @param postCode 
	 * @param address
	 * @param phoneNumber
	 * @param managerName
	 * @param operationalStatus
	 * @param maxStorageCapacity
	 * @param currentStorageCapacity
	 * @param notes
	 * @param updateDate
	 * @save 入力内容に記入した内容または、既存データから修正が無い場合は更新しない
	 */
    @Transactional
    public void updateCenterInfo(CenterInfoUpdateForm form) {
        CenterInfo entity = repository.findById(form.getCenterId())
                .orElseThrow(() -> new IllegalArgumentException("指定されたセンターが存在しません"));

        // 更新フォームに入力がある場合のみ更新
        if (form.getCenterName() != null && !form.getCenterName().isEmpty()) {
            entity.setCenterName(form.getCenterName());
        }
        if (form.getPostCode() != null && !form.getPostCode().isEmpty()) {
            entity.setPostCode(form.getPostCode());
        }
        if (form.getAddress() != null && !form.getAddress().isEmpty()) {
            entity.setAddress(form.getAddress());
        }
        if (form.getPhoneNumber() != null && !form.getPhoneNumber().isEmpty()) {
            entity.setPhoneNumber(form.getPhoneNumber());
        }
        if (form.getManagerName() != null && !form.getManagerName().isEmpty()) {
            entity.setManagerName(form.getManagerName());
        }
        if (form.getOperationalStatus() != entity.getOperationalStatus()) {
            entity.setOperationalStatus(form.getOperationalStatus());
        }
        if (form.getMaxStorageCapacity() != null) {
            entity.setMaxStorageCapacity(form.getMaxStorageCapacity());
        }
        if (form.getCurrentStorageCapacity() != null) {
            entity.setCurrentStorageCapacity(form.getCurrentStorageCapacity());
        }
        if (form.getNotes() != null && !form.getNotes().isEmpty()) {
            entity.setNotes(form.getNotes());
        }
        entity.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));

        repository.save(entity);
    }
    
	/**
	 * 在庫センター情報を削除する
	 * 
	 * @param dleteFlag
	 * @param updateDate
	 * @save 削除フラグ（1：削除済）　更新日時　情報更新
	 */
    @Transactional
    public void deleteCenterInfo(int centerId) {
        CenterInfo entity = repository.findById(centerId)
                .orElseThrow(() -> new IllegalArgumentException("指定されたセンターが存在しません"));
        entity.setDeleteFlag(1);
        entity.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));

        repository.save(entity);
    }
}
