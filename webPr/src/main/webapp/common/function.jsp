<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%!// 어디서나 접근 가능 public 리턴값타입은 숫자형int = 메서드타입과 같음

	public int memberInsert(Connection conn, String memberId, String memberPw, String memberName, String memberGender,
			String memberBirth, String memberAddress, String memberPhoneNumber, String memberEmail,
			String memberInHobby) {
		int value = 0; // 메서드 지역변수 결과값을 담는다.
		String sql = "";
		PreparedStatement pstmt = null; // 쿼리 구문클래스 선언

		try {
			sql = "insert into member (memberId, memberPw, memberName, "
					+ "memberGender, memberBirth, memberAddress, memberPhone, " + "memberEmail, memberHobby) "
					+ "values (?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId); // 문자형 메서드 사용
			pstmt.setString(2, memberPw); // 문자형 메서드 사용 숫자형 setInt(번호, 값);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberGender);
			pstmt.setString(5, memberBirth);
			pstmt.setString(6, memberAddress);
			pstmt.setString(7, memberPhoneNumber);
			pstmt.setString(8, memberEmail);
			pstmt.setString(9, memberInHobby); // 구문객체 실행하면 성공 시 1, 실패 시 0 리턴
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // try를 했던 catch를 했던 꼭 실행해야하는 영역
			// 객체 사라지게하고
			// db 연결 끊기
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return value;
	}%>