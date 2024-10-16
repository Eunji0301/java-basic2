<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery 연습하기</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<script>
		$(document).ready(function() {
			// alert("hello world!");
			$("button").click(function() {
				$("p").hide();
			});

			// 태그를 부를 때 태그셀렉터 호출
			$("button").dblclick(function() {
				$("p").show();
			});

			// 아이디를 부를 때에는 $ 표시를 한다.
			$("#btn").click(function() {
				$("#divid").html("");
			});

			/* 양식
			$("셀렉터").메서드(function() {
				코드작성;
			}); */

			$("#btn2").click(function() {
				$("#div1").fadeIn();
				$("#div2").fadeIn("slow");
				$("#div3").fadeIn(3000);
			});
			
			$("#filep").click(function() {
				$("#panel").slideDown("slow");
			});
		});
	</script>
	<p>저는 홍길동입니다.</p>
	<button>클릭하면 글이 사라집니다.</button>

	<div id="divid">안녕하세요</div>
	<button id="btn">클릭해보세요</button>

	<button id="btn2">서서히 나타나다</button>
	<div id="div1" style="width: 80px; height: 80px; background-color: red; display: none;"></div>
	<br>
	<div id="div2" style="width: 80px; height: 80px; background-color: green; display: none;"></div>
	<br>
	<div id="div3" style="width: 80px; height: 80px; background-color: blue; display: none;"></div>
	
	<div id="filep" style="padding: 5px; text-align: center; background-color: #e5eecc; border: 1px solid #c3c3c3">클릭하면 아래로 펼쳐집니다.</div>
	<div id="panel" style="padding: 50px; display: none;">쥬르륵</div>
</body>
</html>