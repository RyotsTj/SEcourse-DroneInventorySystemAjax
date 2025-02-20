/**
 * 更新用 削除ボタン押下時の処理
 */
document.addEventListener("DOMContentLoaded", function () {
	// 削除ボタン
  const deleteBtn = document.getElementById("deleteBtn");
  const centerIdInput = document.querySelector("input[name='centerId']");

  if (!deleteBtn || !centerIdInput) {
    console.error("削除ボタンまたは centerId が見つかりません。");
    return;
  }

  deleteBtn.addEventListener("click", async function () {
    console.log("削除ボタンがクリックされました。");

    if (!centerIdInput.value) {
      alert("削除できるセンター情報がありません。");
      return;
    }

    const isConfirmed = confirm("削除しますか？");
    if (!isConfirmed) return;

    try {
      const csrfToken = document.getElementsByName("_csrf")[0]?.value || "";
      const csrfHeader = document.getElementsByName("_csrf_header")[0]?.value || "X-CSRF-TOKEN"; 

      if (!csrfToken) {
        console.error("CSRF トークンが取得できません");
        alert("削除処理に失敗しました。）");
        return;
      }

      const response = await fetch("/admin/centerInfo/delete", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
          [csrfHeader]: csrfToken
        },
        body: `centerId=${encodeURIComponent(centerIdInput.value)}`
      });

      const data = await response.text();
      console.log("サーバーからのレスポンス:", data);

      if (data.includes("success")) {
        alert("削除が完了しました");
        window.location.href = "/admin/centerInfo";
      } else {
        alert("削除に失敗しました。サーバーレスポンス: " + data);
      }
    } catch (error) {
      console.error("削除処理エラー:", error);
      alert("通信エラーが発生しました。");
    }
  });
});