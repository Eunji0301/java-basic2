package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvc.dbcon.Dbconn;
import mvc.vo.BoardVo;
import mvc.vo.CommentVo;
import mvc.vo.Criteria;
import mvc.vo.MemberVo;
import mvc.vo.SearchCriteria;

public class CommentDao {

	private Connection conn; // 전역적으로 쓴다 연결객체를....
	private PreparedStatement pstmt; // 쿼리를 실행하기 위한 구문 객체

	public CommentDao() { // 생성자를 만든다 왜? DB연결하는 Dbconn 객체 생성할려고 ...생성해야 mysql 접속하니까
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
	}

	public ArrayList<CommentVo> commentSelectAll(int bidx) {
		ArrayList<CommentVo> alist = new ArrayList<CommentVo>();

		String sql = "select * from comment where delYN = 'N' and bidx = ? order by cidx desc";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 커서가 다음으로 이동해서 첫 글이 있느냐 물어보고 true면 진행 int bidx =
				int cidx = rs.getInt("cidx");
				String ccontents = rs.getString("ccontents");
				String cwriter = rs.getString("cwriter");
				String writeday = rs.getString("writeday");

				CommentVo cv = new CommentVo();

				cv.setCcontents(ccontents);
				cv.setCwriter(cwriter);
				cv.setWriteday(writeday);

				alist.add(cv);
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

	public int commentInsert(CommentVo cv) {
		int value = 0;
		
		String ccontents = cv.getCcontents();
		String csubject = cv.getCsubject();
		String cwriter = cv.getCwriter();
		int bidx = cv.getBidx();
		int midx = cv.getMidx();
		String cip = cv.getCip();

		String sql = "INSERT INTO comment(csubject, ccontents, cwriter, bidx, midx, cip) "
				+ "VALUES (?, ?, ?, ?, ?);";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, csubject);
			pstmt.setString(2, ccontents);
			pstmt.setString(3, cwriter);
			pstmt.setInt(4, bidx);
			pstmt.setInt(5, midx);
			pstmt.setString(6, cip);
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { // 각 객체도 소멸시키고 DB연결 끊는다
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	//public int commentDelete(int bidx, String password) {
		/*
		 * int value = 0; String sql =
		 * "UPDATE board SET delYN='Y' WHERE bidx = ? AND password = ?";
		 * 
		 * try { pstmt = conn.prepareStatement(sql); pstmt.setInt(1, bidx); // 해당 게시글을
		 * 찾기 위한 bidx pstmt.setString(2, password); value = pstmt.executeUpdate(); //
		 * update를 실행하고 결과 저장. 성공하면 1, 실패하면 0 } catch (SQLException e) {
		 * e.printStackTrace(); } finally { try { // 각 객체도 소멸시키고 DB연결 끊는다 pstmt.close();
		 * conn.close(); } catch (SQLException e) { e.printStackTrace(); } } return
		 * value;
		 */
	//}
}