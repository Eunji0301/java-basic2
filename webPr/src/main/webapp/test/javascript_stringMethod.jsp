<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>String 객체 메서드 사용해보기</title>
</head>
<body>
	<script>
		let str = new String("dreams come true"); // String클래스 생성자에 인자값 담아서 호출

		// indexOf
		let position = str.indexOf("come");
		document.write("come의 자리 위치는? " + position + "<br>");

		let position2 = str.indexOf("hello");
		document.write("hello의 자리 위치는? " + position2 + "<br>");

		if (position2 == -1) {
			document.write("해당 단어는 포함되어있지 않습니다." + "<br>");
		} else {
			document.write("해당하는 단어는 : " + position2 + "번째 자리에 있습니다." + "<br>");
		}

		let str3 = prompt("글자를 입력하세요.");
		let position3 = str3.indexOf("good"); // 'good'이라는 단어가 있는지 없는지 체크

		if (position3 == -1) {
			document.write("해당 단어는 포함되어있지 않습니다." + "<br>");
		} else {
			document.write("해당하는 단어는 : " + position3 + "번째 자리에 있습니다." + "<br>");
		}

		// substr함수 : 글자 자르기

		let str4 = "무궁화 꽃이 피었습니다.";
		let value = str4.substr(0, 4); // 0번부터 4개(0번부터 시작함)
		document.write("자른 값은 ? " + value + "<br>");
		let value2 = str4.substr(6, 3);
		document.write("자른 값은 ? " + value2 + "<br>");

		let value3 = str4.substring(2, 5); // 2번 다음 자리부터 5번까지
		document.write("value3 값은 ? " + value3 + "<br>");

		let s = "    안녕   ";
		let ss = s.trim();
		document.write("공백 제거하고 출력 : " + ss + "<br>");
	</script>
</body>
</html>