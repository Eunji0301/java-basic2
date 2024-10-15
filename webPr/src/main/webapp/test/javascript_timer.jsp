<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOM 객체로 타이머 사용하기</title>
</head>
<body>
	<img id="img" src="../images/strawberry.png"
		onmouseover="startTimer(5000)" onmouseout="cancelTimer()">
	<button
		onclick="window.open('./javascript_size.jsp', 'test','width=300, height=300')">버튼눌러보기</button>
	<script>
		let timerID = null;
		function startTimer(time) {
			// 타이머 시작
			timerId = setTimeout("load('http://www.naver.com')", time);

			// 이미지에 마우스 올리면 나타나는 툴팁 메시지
			document.getElementById("img").title = "타이머 작동 시작...";
		}

		function cancelTimer() {
			if (timerId != null) {
				clearTimeout(timerId);
			}
		}
		function load(url) {
			window.location = url; // 현재 윈도우에 url 사이트 로드
		}
	</script>
</body>
</html>