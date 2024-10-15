<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>라디오 체크박스 객체 다루기</title>
<script>
	function findChecked() {
		let found = null;
		let kcity = document.getElementsByName("city");
		// 배열과 for문은 항상 붙어다님
		for (let i = 0; i < kcity.length; i++) {
			if (kcity[i].checked == true) { // 각 값이 체크되었는지 물어봄
				found = kcity[i];
			}
		}
		if (found != null) {
			alert(found.value + "이 선택되었음");
		} else {
			alert("선택된 것이 없음");
		}
	}
</script>
</head>
<body>
	<form>
		<input type="radio" name="city" value="seoul" checked>서울 <input
			type="radio" name="city" value="busan">부산 <input type="radio"
			name="city" value="daejeon">대전 <input type="button"
			value="find checked" onclick="findChecked()">

	</form>
</body>
</html>