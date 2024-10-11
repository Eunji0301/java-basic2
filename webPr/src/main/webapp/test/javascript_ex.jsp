<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예제문제</title>
</head>
<body>
	<script>
		function search() {
			let sp = document.getElementsByTagName("span"); // 여러 개의 span태그 컬렉션 집합 객체변수
			for (let i = 0; i < sp.length; i++) {
				let entitysp = sp[i];
				entitysp.style.color = "orange";
				entitysp.style.fontSize = "30px";
			}
			return;
		}
	</script>
	1. 다음 문제에서 사람 이름에 해당하는 단어는 글자크기를 30px로 작성하고 색상은 orange로 표현
	<hr>
	<span>이순신</span> 장군은 배 13척으로 명량해전을 대승으로 이끌었다.
	<br>
	<span>홍길동</span>은 아버지를 아버지라 부르지 못하고 가출했다.
	<br>
	<span>강감찬</span>은 귀주대첩의 유명한 장군이다.
	<br>
	<span>김한수</span>는 내 학교 선배였다.
	<br>
	<button type="button" onclick="search()">사람찾기</button>
</body>
</html>