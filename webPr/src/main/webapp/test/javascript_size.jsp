<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>윈도우 크기 조절</title>
<script type="text/javascript">
	function startScroll(interval) {
		setInterval("autoScroll()", interval);
	}

	function autoScroll() {
		window.scrollBy(0, 10); // 10px 위로 이동
	}
</script>
</head>
<body onload="startScroll(1000)">
	<h3>윈도우 크기 조절</h3>
	<hr>
	<button onclick="window.moveBy(-10,0)">left</button>
	<button onclick="window.moveBy(10,0)">right</button>
	<button onclick="self.moveBy(0,-10)">up</button>
	<button onclick="moveBy(0,10)">down</button>
	<button onclick="resizeBy(10,10)">+</button>
	<button onclick="resizeBy(-10,-10)">-</button>

	<hr>

	무
	<br> 궁
	<br> 화
	<br> 꽃
	<br> 이
	<br> 피
	<br> 었
	<br> 습
	<br> 니
	<br> 다
	<br>
	무
	<br> 궁
	<br> 화
	<br> 꽃
	<br> 이
	<br> 피
	<br> 었
	<br> 습
	<br> 니
	<br> 다
	<br>
	무
	<br> 궁
	<br> 화
	<br> 꽃
	<br> 이
	<br> 피
	<br> 었
	<br> 습
	<br> 니
	<br> 다
	<br>
	무
	<br> 궁
	<br> 화
	<br> 꽃
	<br> 이
	<br> 피
	<br> 었
	<br> 습
	<br> 니
	<br> 다
	<br>
	무
	<br> 궁
	<br> 화
	<br> 꽃
	<br> 이
	<br> 피
	<br> 었
	<br> 습
	<br> 니
	<br> 다
	<br>
	무
	<br> 궁
	<br> 화
	<br> 꽃
	<br> 이
	<br> 피
	<br> 었
	<br> 습
	<br> 니
	<br> 다
	<br>
</body>
</html>