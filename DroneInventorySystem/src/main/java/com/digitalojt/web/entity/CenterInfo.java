package com.digitalojt.web.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * センター情報Entity
 * 
 * @author dotlife
 *
 */
@Getter
@Setter
@Entity
public class CenterInfo {

	/**
	 * センターID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int centerId;
	
	/**
	 * センター名
	 */
	@Column(nullable = false, length = 20)
	private String centerName;
	
	/**
	 * 郵便番号
	 */
	@Column(nullable = false, length = 8)
	private String postCode;
	
	/**
	 * 住所
	 */
	@Column(nullable = false, length = 255)
	private String address;
	
	/**
	 * 電話番号
	 */
	@Column(nullable = false, length = 13)
	private String phoneNumber;
	
	/**
	 * 管理者名
	 */
	@Column(nullable = false, length = 100)
	private String managerName;
	
	/**
	 * 稼働状況ステータス
	 */
	@Column(nullable = false)
	private int operationalStatus;
	
	/**
	 * 最大容量
	 */
	@Column(nullable = false, length = 10)
	private String maxStorageCapacity;
	
	/**
	 * 現在容量
	 */
	@Column(nullable = false, length = 10)
	private String currentStorageCapacity;
	
	/**
	 * 備考
	 */
	@Column(length = 200)
	private String notes;
	
	/**
	 * 論理削除フラグ
	 */
	@Column(nullable = false)
	private int deleteFlag;

	/**
	 * 更新日
	 */
	@Column(nullable = false)
	private Timestamp updateDate;

	/**
	 * 登録日
	 */
	@Column(nullable = false)
	private Timestamp createDate;
	
    // Getter/Sette
    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }
}
