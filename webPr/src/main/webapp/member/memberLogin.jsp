<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE HTML>
<HTML>
<HEAD>
<TITLE>Login</TITLE>
</HEAD>

<BODY>
	<header>로그인 페이지</header>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" maxl   b ength="100"
				style="width: 250px;" placeholder="아이디를 입력하세요"></input>
			<td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pw" maxlength="100" style="width: 250px;" placeholder="비밀번호를 입력하세요"></input>
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
