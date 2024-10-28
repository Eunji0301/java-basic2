<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// System.out.println("안녕하세요");
// out.println("웹페이지에서 안녕하세요");
String msg = "";
if (session.getAttribute("msg") != null) {
	msg = (String) session.getAttribute("msg");
}
session.setAttribute("msg", "");

int midx = 0;
String memberId = "";
String memberName = "";
String alt = "";
String logMsg = "";

if (session.getAttribute("midx") != null) { // 로그인이 되었으면
	midx = (int) session.getAttribute("midx");
	memberId = (String) session.getAttribute("mid");
	memberName = (String) session.getAttribute("mname");

	alt = memberName + "님 로그인되었습니다♥";
	logMsg = "<a href = '" + request.getContextPath() + "member/memberLogOut.aws'>로그아웃</a>";

} else {
	alt = "로그인하세요.";
	logMsg = "로그인";
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
<%if (!msg.equals("")) {%>
		alert('<%=msg%>');
<%}%>
	
</script>
<style>
.main {
	width: 300px;
	height: 100px;
	text-align: center;
	border: 30px solid dodgerblue;
	margin: 50px;
	padding: 50px;
}
</style>
</head>
<body>
	<%=alt%>
	<%=logMsg%>
	<hr>
	<div class="main">환영합니다. 메인페이지입니다.</div>
	<div>
		<a href="<%=request.getContextPath()%>/member/memberSignin.aws">회원가입페이지로 이동</a>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/member/memberLogin.aws">회원로그인</a>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/member/memberList.aws">회원목록</a>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/board/boardList.aws">게시판목록</a>
	</div>
</body>
</html>