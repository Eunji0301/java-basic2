<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 연습하기</title>
<body onload="alert(" 모든 객체가 생성된 후에로딩됩니다.");">
	<script>
	window.onload = function() {
		alert("모든 객체가 생성된 후에 로딩됩니다.2");
	}
	
	function calculate() {
		let exp = document.getElementById("exp"); // exp input 객체 찾기
		let result = document.getElementById("result"); // result input 객체 찾기
		result.value = eval(exp.value); // 계산해서 담기
		
		return;
	}

	function hideMenu() {
		alert("오른쪽 버튼은 사용할 수 없습니다.");
		return false;
	}
	document.oncontextmenu = hideMenu;
	
	function changeImage() {
		alert("함수에 들어왔나요 ?");
		
		let sel = document.getElementById("sel"); // select 객체 찾기
		alert("객체가 생성되었나요 ?" + sel);
		
		let img = document.getElementById("myImg"); // img 객체 찾기
		alert("객체가 생성되었나요 ?" + img);
		
/* 		img.onload = function() { // 이미지가 로딩이 되면 익명함수 동작 */
			let mySpan = document.getElementByID("mySpan");
			alert("객체가 생성되었나요 ?" + mySpan);
			mySpan.innerHTML = img.width + " X " + img.height;
/* 		} */
		let index = sel.selectedIndex; // 선택된 옵션 인덱스
		img.src = sel.options[index].value;
	}
	
	function checkFilled(obj) { // obj에 입력된 것이 없다면
		alert("checkedFilled 함수 안에 들어왔습니다.");
	
		if(obj.value == "") { // obj에 다시 포커스
			obj.focus();
		}
	}
</script>
</head>
<body>
	<form>
		식 <input type="text" id="exp" value=""> <br> 값 <input
			type="text" id="result"> <br> <input type="button"
			value="계산하기" onclick="calculate();">
		<hr>

		<p>마우스 오른쪽 버튼 클릭하기 테스트</p>
		<hr>

		<select id="sel" onchange="changeImage()">
			<option value="../images/apple.png">사과
			<option value="../images/banana.png">바나나
			<option value="../images/strawberry.png">딸기
		</select> <span id="mySpan">이미지 크기</span>
		<p>
			<img id="myImg" src="images/apple.png" title="사과">
		</p>
		<hr>

		이름 <input type="text" id="name" onblur="checkFilled(this)">
		<p>
			<!-- 그 위치를 떠날 때 감지하는 이벤트 -->
			학번 <input type="text">
	</form>
</body>
</html>