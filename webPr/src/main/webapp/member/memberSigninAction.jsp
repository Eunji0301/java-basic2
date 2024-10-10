<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %> --%>
<%@ page import="java.sql.*"%>
<%@ include file="/common/dbconn.jsp"%>
<%@ include file="/common/function.jsp"%>
<jsp:useBean id="mv" class="Vo.MemberVo" scope="page " />
<!-- scope 범위는 4가지가 있다. page(페이지 내에서만), request(전송하는 범위까지), session(서버에서 끝날때까지 로그아웃), application(프로그램이 살아있을때까지) -->

<jsp:setProperty name="mv" property="*" />

<%
/* String memberId = request.getParameter("memberId");
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
 */
/* 1. jsp 프로그래밍(날코딩 날코딩방법부터 -> 함수화 -> 객체화방식)
2. java/jsp 프로그래밍(model1, model2 MVC 방식으로 진화되는 방법)
3. spring 프레임워크로 프로그래밍하는 방법 */

/* // conn 객체 안에는 많은 메서드가 있는데 일단 createStatement 메서드를 사용해서 쿼리 작성
String sql = "insert into member (memberId, memberPw, memberName, "
		+ "memberGender, memberBirth, memberAddress, memberPhone, " + "memberEmail, memberHobby) " + "values ('"
		+ memberId + "', '" + memberPw + "', '" + memberName + "', '" + memberGender + "', '" + memberBirth + "', '"
		+ memberAddress + "', '" + memberPhoneNumber + "', '" + memberEmail + "', '" + memberInHobby + "');";

Statement stmt = conn.createStatement(); // 쿼리 구문을 동작시키는 클래스
int value = stmt.executeUpdate(sql);
// value가 0이면 미입력 1이면 입력됨 */

// PreparedStatement 클래스는 메서드화시켜 사용함

// 매개변수에 인자값 대입해서 함수호출
String[] memberHobby = request.getParameterValues("memberHobby");
String memberInHobby = "";
for (int i = 0; i < memberHobby.length; i++) {
	memberInHobby = memberInHobby + memberHobby[i] + ", ";
}

// 객체 안에 생성해놓은 멤버 메서드를 호출해서 값을 꺼낸다.
int value = memberInsert(conn, mv.getMemberId(), mv.getMemberPw(), mv.getMemberName(), mv.getMemberGender(),
		mv.getMemberBirth(), mv.getMemberAddress(), mv.getMemberPhone(), mv.getMemberEmail(), memberInHobby);

// value값이 1이면 입력성공, 0이면 입력실패
// 1이면 성공했기때문에 다른 페이지로 이동시키고, 0이면 다시 회원가입페이지로 간다.
String pageUrl = "";
String msg = "";

if (value == 1) { // 					  index.jsp파일은 web.xml 웹 설정파일에 기본등록되어있어 생략 가능
	msg = "회원가입되었습니다.";
	pageUrl = request.getContextPath() + "/"; // request.getContextPath() : 프로젝트이름
	// response.sendRedirect(pageUrl); // 전송방식 sendRedirect는 요청받으면 다시 그쪽으로 가라고 지시하는 방법
} else {
	msg = "회원가입 중 오류가 발생했습니다.";
	pageUrl = request.getContextPath() + "/member/memberSignin.jsp";
	// response.sendRedirect(pageUrl);
}
%>
<script>
alert('<%=msg%>');
// 자바스크립트로 페이지 이동시킨다. document 객체 안에 location 객체 안에 주소속성에 담아서
document.location.href="<%=pageUrl%>;"
</script>