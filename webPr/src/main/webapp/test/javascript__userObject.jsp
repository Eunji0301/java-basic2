<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바스크립트로 사용자 객체 만들기</title>
</head>
<body>
	<script>
		// Object 객체 생성법
		let obj = new Object(); // Object 객체 생성자 직접 호출
		obj.name = "김갑수";
		obj.age = 92;
		obj.move = move; // 함수(메서드) 이름을 말하낟.

		function move() {
			// alert("열심히 움직인다.");
			document.write("열심히 움직인다.");
			return;
		}

		document.write(obj.name + "<br>");
		document.write(obj.age + "<br>");
		obj.move();

		document.write("<br>");

		// 리터럴(값 대입 방식) 객체 생성법
		let obj2 = {
			name : "김갑수",
			age : 92,
			move : function() {
				document.write("열심히 움직인다.");
			}
		};

		document.write(obj2.name + "<br>");
		document.write(obj2.age + "<br>");
		obj.move();

		document.write("<br>");

		// 프로토타입(클래스) 함수 객체 생성
		function person() { // 프로토타입 함수(생성자 함수)
			this.name = "김갑수";
			this.age = 92;
			this.move = function() {
				document.write("열심히 달린다.");
				return;
			}

		}

		let p = new person();
		document.write(p.name + "<br>");
		document.write(p.age + "<br>");
		p.move();
	</script>
</body>
</html>