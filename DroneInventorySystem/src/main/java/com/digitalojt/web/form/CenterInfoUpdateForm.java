package com.digitalojt.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import com.digitalojt.web.validation.CenterInfoUpdateFormValidator;

import lombok.Getter;
import lombok.Setter;

/**
 * 在庫センター情報画面　登録/更新/削除用 入力フォーム
 * 
 * @author takaki
 *
 */
@Getter
@Setter
@CenterInfoUpdateFormValidator
public class CenterInfoUpdateForm {
	
	/**
	 * センターID
	 */
	private Integer centerId;

	/**
	 * センター名
	 */
	@NotBlank(message = "センター名：{centerName.requiredFields.empty}")
	@Size(max = 20, message = "{centerName.length.wrongInput}")
	private String centerName;
	
	/**
	 * 郵便番号
	 */
    @Pattern(regexp = "\\d{3}-\\d{4}", message = "郵便番号は{centerName.halfWidth.numbers.hyphen}")
	private String postCode;
	
	/**
	 * 住所
	 */
    @NotBlank(message = "住所；{centerName.requiredFields.empty}")
	private String address;
	
	/**
	 * 電話番号
	 */
	@Pattern(regexp = "\\d{2,4}-\\d{2,4}-\\d{4}", message = "電話番号は{centerName.halfWidth.numbers.hyphen}")
	private String phoneNumber;
	
	/**
	 * 管理者名
	 */
	@NotBlank(message = "管理者名；{centerName.requiredFields.empty}")
	private String managerName;
	
	/**
	 * 稼働ステータス
	 */
	private int operationalStatus;
	
	/**
	 * 最大容量
	 */
	@Positive(message = "最大保存容量は半角数字で入力してください。")
	private String maxStorageCapacity;
	
	/**
	 * 現在容量
	 */
	@Positive(message = "現在保存容量は半角数字で入力してください。")
	private String currentStorageCapacity;
	
	/**
	 * 備考
	 */
	private String notes;
	
	/**
	 * Getter & Setter
	 */
    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public int getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(int operationalStatus) {
        this.operationalStatus = operationalStatus;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
