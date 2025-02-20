/**
 * 更新用 登録ボタン押下時の処理
 */
document.addEventListener("DOMContentLoaded", function () {
	// 更新ボタン、フォーム、確認フラグの入力フィールドを取得
	const registerBtn = document.getElementById("registerBtn");  // 更新ボタンの要素を取得
	const form = document.getElementById("updateForm");  // フォームの要素を取得
	const confirmFlagInput = document.getElementById("confirmFlag");  // 確認フラグの入力フィールドを取得
	const errorContainer = document.getElementById("errorContainer");  // エラーを表示

	// 登録ボタンがクリックされたときの処理
	registerBtn.addEventListener("click", function (event) {
	  event.preventDefault(); // フォームが自動で送信されるのを防ぐ
	  console.log("登録ボタンがクリックされました"); // デバッグ用ログ

	  // フォームデータを送信するためにFormDataオブジェクトを作成
	  const formData = new FormData(form);
	  console.log("送信するフォームデータ:", formData); // フォームデータの内容をログ出力

	  // 最初の非同期リクエストでサーバーにフォームデータを送信
	fetch("/admin/centerInfo/update", {
	    method: "PATCH",  // HTTPメソッドはPOST
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
	        console.log("更新処理が成功しました"); // 成功時のログ
	        
	        // ユーザーに確認ダイアログを表示（登録内容を確認）
	        const confirmDialog = confirm(
	          "更新内容を確認してください。更新してよろしいですか？"
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
	          fetch("/admin/centerInfo/update", {
	            method: "PATCH",  // HTTPメソッドはPOST
	            body: formDataForResend, // 再送信するフォームデータを設定
	          })
	            .then((response) => response.text())  // サーバーからのレスポンスをテキストとして受け取る
	            .then((data) => {
	              console.log("再送信後のサーバーからのデータ:", data); // 再送信後のデータをログ出力
	              // サーバーから「success」が返ってきた場合、登録完了
	              if (data.includes("success")) {
	                alert("更新が完了しました");  // 登録完了のメッセージを表示
				  window.location.href = "/admin/centerInfo"; // 一覧画面へリダイレクト
	              }
	            })
	            .catch((error) => {
	              console.error("再送信中のエラー:", error);  // 再送信中のエラーをコンソールに表示
	              alert("通信エラーが発生しました");  // ユーザーにエラーメッセージを表示
				  errorContainer.innerHTML = `<div>${data}</div>`;
				  errorContainer.style.display = "block";
	            });
	        }
	      } else {
	        console.log("更新に失敗しました"); // 失敗時のログ
	        alert("更新に失敗しました");  // サーバーから失敗のメッセージが返ってきた場合
			errorContainer.innerHTML = `<div>${data}</div>`;
			errorContainer.style.display = "block";
	      }
	    })
	    .catch((error) => {
	      console.error("通信エラー:", error);  // 通信エラーが発生した場合のログ
	      alert("通信エラーが発生しました");  // ユーザーにエラーメッセージを表示
		  errorContainer.innerHTML = `<div>${data}</div>`;
		  errorContainer.style.display = "block";
	    });
	})
  });