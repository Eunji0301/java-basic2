<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키 이벤트 조작하기</title>
<style>
td {
	width: 200px;
	height: 200px;
	border: 1px solid dodgerblue;
}
</style>
<script>
	let tds;
	let index = 0;
	let prevIndex = 0;
	window.onload = function() { // 화면이 모두 로딩된 후 함수 실행
		tds = document.getElementsByTagName("td"); // 각 td 태그를 찾는다.
		tds[index].style.backgroundColor = "dodgerblue";
	}
	
	window.onkeydown = function(e) { // 키 눌림을 감지했을 때
		switch(e.key) {
		case "ArrowDown" :
			if(index / 3 >= 2) { // 맨 위로 셀이 이동한 경우
				return;
			}
			index += 3;
			break;
		case "ArrowUp" :
			if(index / 3 < 1) { // 맨 아래쪽으로 셀이 이동한 경우
				return;
			}
			index -= 3;
			break;
		
		case "ArrowLeft" :
			if(index % 3 == 0) { // 맨 왼쪽으로 셀이 이동한 경우
				return;
			}
			index--;
			break;
			
		case "ArrowRight" :
			if(index % 3 == 2) { // 맨 오른쪽으로 셀이 이동한 경우
				return;
			}
			index++;
			break;
		}
		tds[index].style.backgroundColor="dodgerblue";
		tds[prevIndex].style.backgroundColor="white";
		prevIndex = index;
	}
</script>
</head>
<body>
	<h3>화살표 키로 셀 위로 이동하기</h3>
	<hr>
	<table>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</body>
</html>