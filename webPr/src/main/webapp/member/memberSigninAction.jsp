<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %> --%>
<%@ page import="java.sql.*"%>

<%
String memberId = request.getParameter("memberId");
System.out.println("memberId값은 ? " + memberId);

String memberPw = request.getParameter("memberPw");
System.out.println("memberPw값은 ? " + memberPw);

String memberPwIsRight = request.getParameter("memberPwIsRight");
System.out.println("memberPwIsRight값은 ? " + memberPwIsRight);

String memberName = request.getParameter("memberName");
System.out.println("memberName값은 ? " + memberName);

String memberEmail = request.getParameter("memberEmail");
System.out.println("memberEmail값은 ? " + memberEmail);

String memberPhoneNumber = request.getParameter("memberPhoneNumber");
System.out.println("memberPhoneNumber값은 ? " + memberPhoneNumber);

String memberAddress = request.getParameter("memberAddress");
System.out.println("memberAddress값은 ? " + memberAddress);

String memberGender = request.getParameter("memberGender");
System.out.println("memberGender값은 ? " + memberGender);

String memberBirth = request.getParameter("memberBirth");
System.out.println("memberBirth값은 ? " + memberBirth);

String[] memberHobby = request.getParameterValues("memberHobby");
String memberInHobby = "";
for (int i = 0; i < memberHobby.length; i++) {
	memberInHobby = memberInHobby + memberHobby[i] + ", ";
	System.out.println("memberHobby값은 ? " + memberHobby[i]);
}

/* 1. jsp 프로그래밍(날코딩 날코딩방법부터 -> 함수화 -> 객체화방식)
2. java/jsp 프로그래밍(model1, model2 MVC 방식으로 진화되는 방법)
3. spring 프레임워크로 프로그래밍하는 방법 */

/* Connection conn = null;
String url = "jdbc:mysql://127.0.0.1/aws0822?serverTimezone=UTC";
String user = "root";
String password = "1234";

Class.forName("com.mysql.cj.jdbc.Driver");
conn = DriverManager.getConnection(url, user, password);

System.out.println("conn:" + conn); */

// conn 객체 안에는 많은 메서드가 있는데 일단 createStatement 메서드를 사용해서 쿼리 작성
String sql = "insert into member (memberId, memberPw, memberName, "
		+ "memberGender, memberBirth, memberAddress, memberPhone, "
		+ "memberEmail, memberHobby) "
+ "values ('" + memberId + "', '" + memberPw + "', '" + memberName + "', '"
		+ memberGender + "', '" + memberBirth + "', '" + memberAddress + "', '"
		+ memberPhoneNumber + "', '" + memberEmail + "', '" + memberInHobby + "');";
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>