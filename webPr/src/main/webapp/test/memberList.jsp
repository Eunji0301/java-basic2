<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 보기</title>
<style>
table {
	border: 1px solid blue;
}

td, th {
	border: 1px dotted green;
	padding: 10px;
	text-align: center;
}

th {
	width: 100px;
	height: 50px;
}

td {
	width: 100px;
	height: 50px;
}

thead, tfoot {
	background: darkgray;
	color: yellow;
}
</style>
</head>
<body>
	<h3>회원 목록</h3>
	<hr>
	<table>
		<thead>
			<tr>
				<th>회원번호</th>
				<th>회원아이디</th>
				<th>회원이름</th>
				<th>성별</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1111</td>
				<td>test1</td>
				<td>홍길동</td>
				<td>남</td>
				<td>2021-09-08</td>
			</tr>
			<tr>
				<td>2222</td>
				<td>test2</td>
				<td>성춘향</td>
				<td>여</td>
				<td>2019-10-21</td>
			</tr>
			<tr>
				<td>3333</td>
				<td>test3</td>
				<td>김진수</td>
				<td>남자</td>
				<td>2024-08-21</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">총 3명입니다.</td>
			</tr>
		</tfoot>

	</table>
</body>
</html>