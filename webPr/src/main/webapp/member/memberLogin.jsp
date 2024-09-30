<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE HTML>
<HTML>
<HEAD>
<TITLE>Login</TITLE>
<style>
body {
	background-color: #f0f2f5;
	display: flex;
	justify-content: center;
	align-items: center;
}

table {
	background-color: white;
	border-radius: 8px;
	padding: 20px;
	box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
	width: 400px;
}

td {
	padding: 10px;
}

input[type="text"], input[type="password"] {
	width: 100%, padding:10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 14px;
}

input[type="button"], input[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: #f5a841;
	color: #7e393c;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	cursor: pointer;
}

input[type="button"]:hover, input[type="submit"]:hover {
	background-color: #e1523c
}

header {
	font-size: 24px;
	text-align: center;
	margin-bottom: 20px;
	color: #d991d2;
}
</style>
</HEAD>

<BODY>
	<header>로그인 페이지</header>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" maxlength="100"
				style="width: 250px;" placeholder="아이디를 입력하세요"></input>
			<td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pw" maxlength="100"
				style="width: 250px;" placeholder="비밀번호를 입력하세요"></input>
			<td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><input type="button"
				name="okay" value="확인"></td>
		</tr>
		<tr>
			<td style="text-align: center;"><input type="button"
				name="searchId" value="아이디찾기"></td>
			<td style="text-align: center;"><input type="button"
				name="searchPw" value="비밀번호찾기"></td>
		</tr>
	</table>
</BODY>
</HTML>
