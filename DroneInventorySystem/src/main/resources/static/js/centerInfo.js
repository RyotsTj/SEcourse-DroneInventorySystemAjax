/**
 * 登録ボタン押下時の処理
 */
document.addEventListener("DOMContentLoaded", function () {
	
	  // 既存データの色　薄いグレー
	document.querySelectorAll(".form-control").forEach(input => {
	    if (input.value.trim() !== "") {
	        input.style.color = "#B0B0B0"; // ここを変更（薄いグレー）
	    }
	    input.addEventListener("input", function () {
	        this.style.color = "black"; // ユーザーが入力すると黒に変更
	    });
	});
	
	function handleActionButton(buttonId, action, url, method) {
		const button = document.getElementById(buttonId);
	    if (!button) {
	        console.error(`ボタンが見つかりません: ${buttonId}`);
	        return;
	    }
		const confirmFlagInput = document.getElementById("confirmFlag");  // 確認フラグの入力フィールドを取得
		const form = document.getElementById("updateForm");
		
		// 登録ボタンがクリックされたときの処理
		button.addEventListener("click", function (event) {
		  event.preventDefault(); // フォームが自動で送信されるのを防ぐ
		  console.log("ボタンがクリックされました"); // デバッグ用ログ
		  console.log(`処理開始: ${action} (URL: ${url}, METHOD: ${method})`);
		  
		  // フォームデータを送信するためにFormDataオブジェクトを作成
		  const formData = new FormData(form);
		  console.log("送信するフォームデータ:", formData); // フォームデータの内容をログ出力
		  
		  // 最初の非同期リクエストでサーバーにフォームデータを送信
		  fetch(url, {
		    method: method,  // HTTPメソッド
		    body: formData, // フォームデータをリクエストのボディに設定
		  })
		  .then((response) => {
		    console.log("サーバーからのレスポンスを受け取る:", response); // レスポンスの内容をログ出力
		    return response.text(); // サーバーからのレスポンスをテキストとして受け取る
		  })
		  .then((data) => {
		    console.log("サーバーからのデータ:", data); // サーバーから返ってきたデータをログ出力
		    // サーバーから「success」が返ってきた場合、次の処理を行う
		    if (data.includes("success")) {
		      console.log("登録処理が成功しました"); // 成功時のログ
		      
		      // ユーザーに確認ダイアログを表示（登録内容を確認）
		      const confirmDialog = confirm(
		        `${action === "register" ? "登録" : action === "update" ? "更新" : "削除"}内容を確認してください。${action === "register" ? "登録" : action === "update" ? "更新" : "削除"}してもよろしいですか？`
		      );
		      console.log("確認ダイアログの選択:", confirmDialog ? "OK" : "キャンセル"); // ダイアログ選択結果をログ出力
			  // ユーザーが「OK」を押した場合
			  if (confirmDialog) {
			    // 確認フラグを「true」に設定（最終確認が終わったことを示す）
			    confirmFlagInput.value = "true";
			    console.log("確認フラグが設定されました"); // 確認フラグ設定のログ

			    // 再送信のためにフォームデータを再度作成
			    const formDataForResend = new FormData(form);
			    console.log("再送信するフォームデータ:", formDataForResend); // 再送信するフォームデータをログ出力

			    // 再度フォームデータをサーバーに送信
			    fetch(url, {
			    method: method,  // HTTPメソッドはPOST
			      body: formDataForResend, // 再送信するフォームデータを設定
			    })
				.then((response) => response.text())  // サーバーからのレスポンスをテキストとして受け取る
				.then((data) => {
				  console.log("再送信後のサーバーからのデータ:", data); // 再送信後のデータをログ出力
				  // サーバーから「success」が返ってきた場合、登録完了
				  if (data.includes("success")) {
					alert(`${action === "register" ? "登録" : action === "update" ? "更新" : "削除"}が完了しました`);
					window.location.href = "/admin/centerInfo";
				  }
				})
				.catch((error) => {
				console.error(`再送信中のエラー (${action}):`, error);
				alert("処理に失敗しました");  // サーバーから失敗のメッセージが返ってきた場合
				alert(data.replace(/<br\s*\/?>/g, "\n"));
				});
			}
		} else {
          console.log(`${action === "register" ? "登録" : action === "update" ? "更新" : "削除"}に失敗しました`); // 失敗時のログ
          alert(`${action === "register" ? "登録" : action === "update" ? "更新" : "削除"}に失敗しました`);  // サーバーから失敗のメッセージが返ってきた場合
		  alert(data.replace(/<br\s*\/?>/g, "\n"));
        }
      })
      .catch((error) => {
		console.error(`通信エラー (${action}):`, error);
		alert("予期せぬエラーを検知しました。システム管理者へ問い合わせください。");
      });
  });
  }
      handleActionButton("registerBtn", "register", "/admin/centerInfo/register", "POST");
      handleActionButton("updateBtn", "update", "/admin/centerInfo/update", "PATCH");
      handleActionButton("deleteBtn", "delete", "/admin/centerInfo/delete", "PATCH");
  });
  
  // キャンセルボタンの処理
  function goBack() {
      window.location.href = "/admin/centerInfo";
  }
  
  function autoExpand(textarea) {
      textarea.style.height = "auto";
      textarea.style.height = textarea.scrollHeight + "px";
  }
