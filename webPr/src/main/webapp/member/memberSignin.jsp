<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
<TITLE>Signin</TITLE>
<style>
nav {
	width: 15%;
	height: 300px;
	float: left;
	background-color: blue;
}

section {
	width: 70%;
	height: 300px;
	float: left;
	background: olivedrab;
}

aside {
	width: 15%;
	height: 300px;
	float: left;
	background: orange;
}

footer {
	width: 100%;
	height: 100%;
	text-align: center;
	clear: both;
	background: plum;
}

body {
	background-color: mistyrose;
	margin: 0px;
	padding: 0px;
}

table {
	margin: 0px;
	padding: 0px;
}

th {
	background-color: yellow;
	font-weight: bold;
}
</style>
</HEAD>

<BODY>
	<header>회원가입 페이지</header>

	<form name="frm" action="memberSigninAction.jsp" method="post">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="memberId" maxlength="50"
					style="width: 100px;"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="memberPw" maxlength="50"
					style="width: 50px;"></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="memberPwIsRight"
					maxlength="50" style="width: 50px;"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="memberName" maxlength="50"
					style="width: 100px;"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="memberEmail" maxlength="50"
					style="width: 150px;"></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type="number" name="memberPhoneNumber"
					maxlength="50" style="width: 150px;"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><select name="memberAddress" style="width: 100px;">
						<option value="서울">서울</option>
						<option value="대전" selected>대전</option>
						<option value="부산">부산</option>
						<option value="인천">인천</option>
						<option value="광주">광주</option>
				</select></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type="radio" name="memberGender" value="남성">남성
					<input type="radio" name="memberGender" value="여성" checked>여성
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="number" name="memberBirth" maxlength="50"
					style="width: 100px;">예)20240920</td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="checkbox" name="memberHobby" value="야구">야구
					<input type="checkbox" name="memberHobby" value="농구">농구 <input
					type="checkbox" name="memberHobby" value="축구">축구</td>
			</tr>

			<tr>
				<td colspan=2 style="text-align: center;"><input type="submit"
					name="btn" value="회원정보 저장하기"> <input type="reset"
					name="btn" value="초기화"></td>
			</tr>
		</table>
	</form>
</BODY>
</HTML>
