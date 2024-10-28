package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvc.dbcon.Dbconn;
import mvc.vo.MemberVo;

public class MemberDao { // MVC방식으로 가기 전 첫번째 model1 방식
	private Connection conn; // 전역변수로 사용. 페이지 어느 곳에서도 사용할 수 있다.
	private PreparedStatement pstmt;

	public MemberDao() { // 생성자를 통해 db연결해서 메서드 사용
		Dbconn dbconn = new Dbconn(); // DB 객체 생성
		conn = dbconn.getConnection(); // 메서드 호출해서 연결객체를 ㅏㄱ져옴
	}
	// 어디서나 접근 가능 public 리턴값타입은 숫자형int = 메서드타입과 같음

	public int memberInsert(String memberId, String memberPw, String memberName, String memberGender,
			String memberBirth, String memberAddress, String memberPhoneNumber, String memberEmail,
			String memberInHobby) {
		int value = 0; // 메서드 지역변수 결과값을 담는다.
		String sql = "";
		// PreparedStatement pstmt = null; // 쿼리 구문클래스 선언

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
			pstmt.setString(9, memberInHobby); // 구문객체 실행하면 성공시 1, 실패시 0 리턴
			value = pstmt.executeUpdate();
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
	}

	// 로그인을 통해 회원정보를 담아오는 메서드
	public MemberVo memberLoginCheck(String memberId, String memberPw) {
		MemberVo mv = null;

		String sql = "SELECT * FROM member WHERE memberId = ? AND memberPw = ?";
		ResultSet rs = null; // DB에서 결과 데이터를 받아오는 전용 클래스

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rs = pstmt.executeQuery();

			if (rs.next() == true) { // 커서가 이동해서 데이터 값이 있으면 if(rs.next())와 같은 표현
				String memberid = rs.getString("memberId"); // 결과값에서 아이디값을 뽑는다.
				int midx = rs.getInt("midx"); // 결과값에서 회원번호를 뽑는다.
				String membername = rs.getString("memberName");

				mv = new MemberVo(); // 화면에 가지고 갈 데이터를 담을 vo 객체 생성
				mv.setMemberId(memberid); // 옮겨담는다.
				mv.setMidx(midx);
				mv.setMemberName(membername);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mv;
	}

	public ArrayList<MemberVo> memberSelectAll() {
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		String sql = "SELECT * FROM member WHERE delYN = 'N' ORDER BY midx DESC";
		ResultSet rs = null; // DB값을 가져오기 위한 전용 클래스
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 커서가 다음으로 이동해서 첫 글이 있느냐 물어보고 true이면 진행
				int midx = rs.getInt("midx");
				String memberId = rs.getString("memberId");
				String memberName = rs.getString("memberName");
				String writeDay = rs.getString("writeDay");
				String memberGender = rs.getString("memberGender");
				
				MemberVo mv = new MemberVo(); // 첫 행부터 mv에 옮겨담기
				mv.setMidx(midx);
				mv.setMemberId(memberId);
				mv.setMemberName(memberName);
				mv.setWriteDay(writeDay);
				mv.setMemberGender(memberGender);
				alist.add(mv); // ArrayList 객체에 하나씩 추가한다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alist;
	}
	
	public int memberIdCheck(String memberId) {
		MemberVo mv = null;

		String sql = "SELECT COUNT(*) AS cnt FROM member WHERE memberId = ?";
		ResultSet rs = null; // DB에서 결과 데이터를 받아오는 전용 클래스
		int cnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 커서가 이동해서 데이터 값이 있으면 if(rs.next())와 같은 표현
				cnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
}