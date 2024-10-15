<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체크박스 연습하기</title>
<script>
	let sum = 0;
	function calc(cBox) {
		if (cBox.checked) {
			sum += parseInt(cBox.value); // 숫자형으로 바꾼다.
		} else {
			sum -= parseInt(cBox.value);
		}
		document.getElementById("sumtext").value = sum;
	}
</script>
</head>
<body>
	<form>
		<input type="checkbox" name="hat" value="10000" onclick="calc(this)">모자
		1만원 <input type="checkbox" name="shoe" value="30000"
			onclick="calc(this)">구두 3만원 <input type="checkbox" name="bag"
			value="80000" onclick="calc(this)">명품가방 8만원
	</form>
	<br> 지불하실 금액
	<input type="text" id="sumtext" value="0">
</body>
</html>