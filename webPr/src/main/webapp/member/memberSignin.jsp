<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
<TITLE>Signin</TITLE>
<link href="../css/style.css" type="text/css" rel="stylesheet">
<script>
	// 버튼을 눌렀을 때 check함수 작동
	function check() {
		var fm = document.frm;

		if (fm.memberId.value == "") {
			alert("아이디를 입력해주세요.");
			return;
		} else if (fm.memberPw.value == "") {
			alert("비밀번호를 입력해주세요.");
			return;
		} else if (fm.memberPwIsRight == "") {
			alert("비밀번호 확인을 입력해주세요.");
			return;
		} else if (fm.memberPw.value != fm.memberPwIsRight.value) {
			alert("비밀번호가 일치하지 않습니다.");
			return;
		} else if (fm.memberName.value == "") {
			alert("이름을 입력해주세요.");
			return;
		} else if (fm.memberEmail.value == "") {
			alert("이메일을 입력해주세요.");
			return;
		} else if (fm.memberPhoneNumber.value == "") {
			alert("연락처를 입력해주세요.");
			return;
		} else if (fm.memberBirth.value == "") {
			alert("생년월일을 입력해주세요.");
			return;
		}

		return;
	}
</script>
</HEAD>

<BODY>
	<header>
		<a href="./memberSignin.jsp">회원가입 페이지</a>
	</header>
	<nav>
		<a href="./memberLogin.jsp">회원로그인 가기</a>
	</nav>
	<form name="frm" action="memberSigninAction.jsp" method="post">
		<table border="1">
			<tr>
				<th class="idcolor">아이디</th>
				<td><input type="text" name="memberId" maxlength="50"
					style="width: 100px;"></td>
			</tr>
			<tr>
				<th class="idcolor">비밀번호</th>
				<td><input type="password" name="memberPw" maxlength="50"
					style="width: 50px;"></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="memberPwIsRight"
					maxlength="50" style="width: 50px;"></td>
			</tr>
			<tr>
				<th id="name">이름</th>
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
				<td><input type="radio" name="memberGender" id="select1"><label
					for="select1">남성</label> <input type="radio" name="memberGender"
					checked id="select2"><label for="select2">여성</label></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="number" name="memberBirth" maxlength="50"
					style="width: 100px;">예)20240920</td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="checkbox" name="memberHobby" id="check1"><label
					for="check1">야구</label> <input type="checkbox" name="memberHobby"
					id="check2"><label for="check2">농구</label> <input
					type="checkbox" name="memberHobby" id="check3"><label
					for="check3">축구</label></td>
			</tr>

			<tr>
				<td colspan=2 style="text-align: center;">
					<button type="button" onclick="check();">
						<img
							src="https://t1.daumcdn.net/daumtop_deco/images/pctop/2023/logo_daum.png"
							width="30px" height="30px">
						<!-- 					<input type="submit" name="btn" value="회원정보 저장하기"> <input type="reset"
					name="btn" value="초기화"></td> -->
			</tr>
		</table>
	</form>
</BODY>
</HTML>
