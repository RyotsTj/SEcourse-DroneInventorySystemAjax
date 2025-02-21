package com.digitalojt.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.ModelAttributeContents;
import com.digitalojt.web.consts.Region;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.form.CenterInfoNewRegistrationForm;
import com.digitalojt.web.form.CenterInfoUpdateForm;
import com.digitalojt.web.service.CenterInfoService;
import com.digitalojt.web.util.MessageManager;

import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のコントローラークラス
 * 
 * @author dotlife
 *
 */
@Controller
@RequiredArgsConstructor
public class CenterInfoController extends AbstractController {

	/** センター情報 サービス */
	private final CenterInfoService centerInfoService;

	/** メッセージソース */
	private final MessageSource messageSource;

	/**
	 * 都道府県Enumをリストに変換
	 * 
	 * @return
	 */
	@ModelAttribute(ModelAttributeContents.REGIONS)
	public List<Region> populateRegions() {
		return Arrays.asList(Region.values());
	}

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO)
	public String index(Model model) {

		logStart(LogMessage.HTTP_GET);

		// 在庫センター情報画面に表示するデータを取得
		List<CenterInfo> centerInfoList = centerInfoService.getCenterInfoData();

		// 画面表示用に商品情報リストをセット
		model.addAttribute(ModelAttributeContents.CENTER_INFO_LIST, centerInfoList);

		logEnd(LogMessage.HTTP_GET);

		return UrlConsts.CENTER_INFO_INDEX;
	}

	/**
	 * 検索結果表示
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO_SEARCH)
	public String search(Model model, @Valid CenterInfoForm form, BindingResult bindingResult) {

		logStart(LogMessage.HTTP_GET);

		// TODO: 入力値のバリデーションチェックに引っかかる場合は、InvalidInputExceptionをthrow
		// Valid項目チェック
		if (bindingResult.hasErrors()) {

			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource,
					bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute(LogMessage.FLASH_ATTRIBUTE_ERROR, errorMsg);
			
			logValidationError(LogMessage.HTTP_POST, form + errorMsg);

			return UrlConsts.CENTER_INFO_INDEX;
		}

		// 在庫センター情報画面に表示するデータを取得
		List<CenterInfo> centerInfoList = centerInfoService.getCenterInfoData(form.getCenterName(), form.getRegion());

		// 画面表示用に商品情報リストをセット
		model.addAttribute(ModelAttributeContents.CENTER_INFO_LIST, centerInfoList);
		
		logEnd(LogMessage.HTTP_GET);

		return UrlConsts.CENTER_INFO_INDEX;
	}
	
	/**
	 * 登録処理 入力フォームに遷移
	 * /admin/centerInfo/register にアクセス時に register.html を表示
	 * 稼働ステータスをデフォルト（0:稼働中）で設定
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
    @GetMapping(UrlConsts.CENTER_INFO_REGISTER)
    public String showCreateForm(Model model) {
    	CenterInfoNewRegistrationForm form = new CenterInfoNewRegistrationForm();
        form.setOperationalStatus(0);
        model.addAttribute("centerInfoNewRegistrationForm", form);
        return UrlConsts.CENTER_INFO_REGISTER;
    }
	
	/**
	 * 登録処理 入力フォームの情報を在庫センター情報テーブルへ登録す
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
    @PostMapping(UrlConsts.CENTER_INFO_REGISTER)
    @ResponseBody
    public ResponseEntity<String> create(
            @RequestParam(value = "confirmFlag", defaultValue = "false") boolean confirmFlag, 
            @Valid @ModelAttribute CenterInfoNewRegistrationForm form, BindingResult bindingResult, 
            Model model) {
    	
    	logStart(LogMessage.HTTP_POST + " " + confirmFlag);
    	
        // 入力チェック；Valid項目チェック
        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                .map(error -> {
                    try {
                        return MessageManager.getMessage(messageSource, error.getDefaultMessage());
                    } catch (Exception e) {
                        return error.getDefaultMessage();
                    }
                })
                .collect(Collectors.joining("<br>"));
            model.addAttribute("errorMessage", errorMessages);
            return ResponseEntity.badRequest().body(errorMessages);
        }
        
    	try {
        	if (!confirmFlag) {
                boolean isValid = true;
                if (isValid) {
                    model.addAttribute("form", form);
                    logEnd(LogMessage.HTTP_POST + " チェック完了");
                    return ResponseEntity.ok("success");
                }
            }
            // サービスを呼び出しデータを登録処理し、成功時は "success" を返す
            centerInfoService.registerCenterInfo(form);
            logEnd(LogMessage.HTTP_POST);
            return ResponseEntity.ok("success");
    	} catch (Exception e) {
            logEnd("登録処理中にエラーが発生しました: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
	
	/**
	 * 更新処理 入力フォーム
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO_UPDATE + "/{centerId}")
	public String showUpdateForm(@PathVariable int centerId, Model model) {
	    CenterInfo centerInfo = centerInfoService.getCenterInfoById(centerId);
	    model.addAttribute("centerInfoUpdateForm", centerInfo);
	    return UrlConsts.CENTER_INFO_UPDATE;
	}

	
	/**
	 * 更新処理 更新入力フォームの情報を在庫センター情報テーブルへ登録す
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
    @PatchMapping(UrlConsts.CENTER_INFO_UPDATE)
    @ResponseBody
    public ResponseEntity<String> updete(
            @RequestParam(value = "confirmFlag", defaultValue = "false") boolean confirmFlag, 
            @Valid @ModelAttribute CenterInfoUpdateForm form, BindingResult bindingResult, Model model) {
    	
    	logStart(LogMessage.HTTP_PATCH + " " + confirmFlag);
    	
        // 入力チェック；Valid項目チェック
        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                .map(error -> {
                    try {
                        return MessageManager.getMessage(messageSource, error.getDefaultMessage());
                    } catch (Exception e) {
                        return error.getDefaultMessage();
                    }
                })
                .collect(Collectors.joining("<br>"));
            model.addAttribute("errorMessage", errorMessages);
            return ResponseEntity.badRequest().body(errorMessages);
        }
    	
    	try {
        	if (!confirmFlag) {
                boolean isValid = true;
                if (isValid) {
                    model.addAttribute("form", form);
                    logEnd(LogMessage.HTTP_PATCH + " チェック完了");
                    return ResponseEntity.ok("success");
                }
            }
            // confirmFlag=trueのときは最終的な処理を行う
            // サービスを呼び出しデータを登録処理し、成功時は "success" を返す
            centerInfoService.updateCenterInfo(form);
            logEnd(LogMessage.HTTP_PATCH);
            return ResponseEntity.ok("success");
    	} catch (Exception e) {
            logEnd("登録処理中にエラーが発生しました: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
    
    @PatchMapping(UrlConsts.CENTER_INFO_DELETE)
    @ResponseBody
    public ResponseEntity<String> delete(int centerId) {
    	logStart(LogMessage.HTTP_PATCH);
        try {
        	// confirmFlag=trueのときは最終的な処理を行う
            centerInfoService.deleteCenterInfo(centerId);
            logEnd(LogMessage.HTTP_PATCH);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
}
