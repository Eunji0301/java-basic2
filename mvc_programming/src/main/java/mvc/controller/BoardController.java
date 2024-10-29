package mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import mvc.dao.BoardDao;
import mvc.vo.BoardVo;
import mvc.vo.Criteria;
import mvc.vo.PageMaker;
import mvc.vo.SearchCriteria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String location; // 이동할 페이지를 저장할 멤버 변수

	public BoardController(String location) {
		this.location = location; // location 초기화
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramMethod = ""; // 전송 방식: "S" (sendRedirect) 또는 "F" (forward)
		String url = ""; // 이동할 URL

		if (location.equals("boardList.aws")) { // 게시판 목록 요청

			String page = request.getParameter("page");
			if (page == null) {
				page = "1"; // 기본 페이지 번호는 1
			}
			int pageInt = Integer.parseInt(page); // 문자열을 정수로 변환

			String search = request.getParameter("search");
			String keyword = request.getParameter("keyword");
			if (keyword == null) {
				keyword = ""; // 키워드가 없으면 빈 문자열
			}

			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pageInt); // 페이지 설정
			scri.setSearch(search); // 검색 타입 설정
			scri.setKeyword(keyword); // 키워드 설정

			PageMaker pm = new PageMaker();
			pm.setScri(scri); // PageMaker에 SearchCriteria 설정

			BoardDao bd = new BoardDao();
			int boardCnt = bd.boardTotalCount(scri); // 전체 게시물 수를 가져옴
			pm.setTotalCount(boardCnt); // PageMaker에 전체 게시물 수 설정

			ArrayList<BoardVo> alist = bd.boardSelectAll(scri); // 게시물 목록 가져오기

			request.setAttribute("alist", alist); // 요청 객체에 게시물 목록 설정
			request.setAttribute("pm", pm); // 요청 객체에 PageMaker 설정

			paramMethod = "F"; // forward 방식
			url = "/board/boardList.jsp"; // 실제 이동할 내부 경로

		} else if (location.equals("boardWrite.aws")) { // 게시물 작성 요청
			System.out.println("boardWrite");

			paramMethod = "F"; // forward 방식
			url = "/board/boardWrite.jsp"; // 게시물 작성 페이지로 이동

		} else if (location.equals("boardWriteAction.aws")) {
			System.out.println("boardWriteAction");

			// 저장되는 위치
			String savePath = "D:\\dev\\eclipse-workspace\\mvc_programming\\src\\main\\webapp\\images\\";
			System.out.println(savePath);

			// 업로드 되는 파일사이즈
			int fsize = (int) request.getPart("filename").getSize();
			System.out.println("fsize:" + fsize);

			// 원본 파일이름
			String originFileName = "";
			if (fsize != 0) {
				Part filePart = (Part) request.getPart("filename"); // 넘어온 멀티파트형식의 파일을 Part클래스로 담는다
				System.out.println("filePart ==> " + filePart);

				originFileName = getFileName(filePart); // 파일이름 추출
				System.out.println("originFileName ==> " + originFileName);

				System.out.println("저장되는 위치 ==> " + savePath + originFileName);

				File file = new File(savePath + originFileName); // 파일객체 생성
				InputStream is = filePart.getInputStream(); // 파일 읽어들이는 스트림 생성
				FileOutputStream fos = null;

				fos = new FileOutputStream(file); // 파일 작성 및 완성하는 스트림생성

				int temp = -1;

				while ((temp = is.read()) != -1) { // 반복문을 돌려서 읽어드린 데이터를 output에 작성한다
					fos.write(temp);
				}
				is.close(); // input 스트림 객체 소멸
				fos.close(); // Output 스트림 객체소멸
			} else {
				originFileName = "";
			}

			// 1. 파라미터값을 넘겨받는다.
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			String writer = request.getParameter("writer");
			String password = request.getParameter("password");

			HttpSession session = request.getSession(); // 세션 객체를 불러와서
			int midx = Integer.parseInt(session.getAttribute("midx").toString());
			// 로그인할때 담았던 세션변수 midx값을 꺼낸다

			String ip = "";
			try {
				ip = getUserIp(request);
			} catch (Exception e) {
				e.printStackTrace();
			}

			BoardVo bv = new BoardVo();
			bv.setSubject(subject);
			bv.setContents(contents);
			bv.setWriter(writer);
			bv.setPassword(password);
			bv.setMidx(midx);
			bv.setFilename(originFileName); // 파일 이름 DB컬럼 추가
			bv.setIp(ip); // ip추가

			// 2.DB처리한다..
			BoardDao bd = new BoardDao();
			int value = bd.boardInsert(bv);

			if (value == 2) { // 입력성공
				paramMethod = "S";
				url = request.getContextPath() + "/board/boardList.aws";
			} else { // 실패했으면
				paramMethod = "S";
				url = request.getContextPath() + "/board/boardWrite.aws";
			}

		} else if (location.equals("boardContent.aws")) { // 게시물 내용 보기 요청

			System.out.println("boardContent");

			// 요청 값 받아오기
			String bidx = request.getParameter("bidx");
			System.out.println("bidx : " + bidx);
			int bidxInt = Integer.parseInt(bidx);

			// 게시물 조회수 업데이트 및 게시물 데이터 가져오기
			BoardDao bd = new BoardDao();
			int value = bd.boardViewCntUpdate(bidxInt);
			BoardVo bv = bd.boardSelectOne(bidxInt); // 해당 게시물 데이터 가져오기
			System.out.println("bv" + bv);
			request.setAttribute("bv", bv); // 요청 객체에 게시물 데이터 설정

			paramMethod = "F"; // forward 방식
			url = "/board/boardContent.jsp"; // 게시물 내용 페이지로 이동

		} else if (location.equals("boardModify.aws")) { // 게시물 수정 요청
			System.out.println("boardModify");

			String bidx = request.getParameter("bidx");
			int bidxInt = Integer.parseInt(bidx);
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidxInt); // 게시물 데이터 가져오기
			System.out.println("bv : " + bv);
			request.setAttribute("bv", bv); // 요청 객체에 게시물 데이터 설정

			paramMethod = "F"; // forward 방식
			url = "/board/boardModify.jsp"; // 수정 페이지로 이동

		} else if (location.equals("boardModifyAction.aws")) { // 게시물 수정 액션
			System.out.println("boardModifyAction");

			// 요청 값 받아오기
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			String writer = request.getParameter("writer");
			String password = request.getParameter("password");
			String bidx = request.getParameter("bidx");

			BoardDao bd = new BoardDao();
			int bidxInt = Integer.parseInt(bidx);
			BoardVo bv = bd.boardSelectOne(bidxInt); // 수정할 게시물 데이터 가져오기

			paramMethod = "S"; // sendRedirect 방식
			// 비밀번호 체크
			if (password.equals(bv.getPassword())) { // 비밀번호가 일치하면
				BoardVo bv2 = new BoardVo();
				bv2.setSubject(subject);
				bv2.setContents(contents);
				bv2.setWriter(writer);
				bv2.setPassword(password);
				bv2.setBidx(bidxInt); // 수정할 게시물의 bidx 설정

				int value = bd.boardUpdate(bv2); // 게시물 업데이트

				if (value == 1) {
					url = request.getContextPath() + "/board/boardContent.aws?bidx=" + bidx; // 내용 페이지로 이동
				} else {
					// 수정이 실패한 경우
					url = request.getContextPath() + "/board/boardModify.aws?bidx=" + bidx; // 수정 페이지로 이동
				}
			} else {
				// 비밀번호가 다르면
				url = request.getContextPath() + "/board/boardModify.aws?bidx=" + bidx; // 수정 페이지로 이동
			}
		} else if (location.equals("boardRecom.aws")) { // 추천 버튼 클릭 처리
			String bidx = request.getParameter("bidx");
			BoardDao bd = new BoardDao();
			int bidxInt = Integer.parseInt(bidx);
			int value = bd.boardRecomUpdate(bidxInt); // 추천수 업데이트

			// 추천 수를 페이지로 반환
			PrintWriter out = response.getWriter();
			out.println(value); // 추천 수 출력
			out.flush(); // 플러시
			out.close(); // 종료
			return;
		} else if (location.equals("boardDelete.aws")) {
			System.out.println("boardDelete");

			String bidx = request.getParameter("bidx");

			request.setAttribute("bidx", bidx);

			paramMethod = "F";
			url = "/board/boardDelete.jsp";
		} else if (location.equals("boardDeleteAction.aws")) { // 게시물 삭제 요청
			System.out.println("boardDeleteAction");

			String bidx = request.getParameter("bidx");
			String password = request.getParameter("password");

			// 처리
			BoardDao bd = new BoardDao();
			int value = bd.boardDelete(Integer.parseInt(bidx), password);
			System.out.println("value : " + value);

			paramMethod = "S"; // sendRedirect 방식
			if (value == 1) {
				url = request.getContextPath() + "/board/boardList.aws";
			} else {
				url = request.getContextPath() + "/board/boardDelete.aws?bidx=" + bidx;
			}
		} else if (location.equals("boardReply.aws")) {
			String bidx = request.getParameter("bidx");

			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(Integer.parseInt(bidx));
			int originbidx = bv.getOriginbidx();
			int depth = bv.getDepth();
			int level_ = bv.getLevel_();

			request.setAttribute("bidx", Integer.parseInt(bidx));
			request.setAttribute("originbidx", originbidx);
			request.setAttribute("depth", depth);
			request.setAttribute("level_", level_);

			paramMethod = "F";
			url = "/board/boardReply.jsp";

		} else if (location.equals("boardReplyAction.aws")) {
			System.out.println("boardReplyAction");

			// 저장되는 위치
			String savePath = "D:\\dev\\eclipse-workspace\\mvc_programming\\src\\main\\webapp\\images\\";
			System.out.println(savePath);

			// 업로드 되는 파일사이즈
			int fsize = (int) request.getPart("filename").getSize();

			// 원본 파일이름
			String originFileName = "";
			if (fsize != 0) {
				Part filePart = (Part) request.getPart("filename"); // 넘어온 멀티파트형식의 파일을 Part클래스로 담는다
				System.out.println("filePart ==> " + filePart);

				originFileName = getFileName(filePart); // 파일이름 추출
				System.out.println("originFileName ==> " + originFileName);

				System.out.println("저장되는 위치 ==> " + savePath + originFileName);

				File file = new File(savePath + originFileName); // 파일객체 생성
				InputStream is = filePart.getInputStream(); // 파일 읽어들이는 스트림 생성
				FileOutputStream fos = null;

				fos = new FileOutputStream(file); // 파일 작성 및 완성하는 스트림생성

				int temp = -1;

				while ((temp = is.read()) != -1) { // 반복문을 돌려서 읽어드린 데이터를 output에 작성한다
					fos.write(temp);
				}
				is.close(); // input 스트림 객체 소멸
				fos.close(); // Output 스트림 객체소멸
			} else {
				originFileName = "";
			}

			// 1. 파라미터값을 넘겨받는다.
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			String writer = request.getParameter("writer");
			String password = request.getParameter("password");
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");

			HttpSession session = request.getSession(); // 세션 객체를 불러와서
			int midx = Integer.parseInt(session.getAttribute("midx").toString());
//			Integer midx = (Integer) session.getAttribute("midx");
			// 로그인할때 담았던 세션변수 midx값을 꺼낸다

			String ip = "";
			try {
				ip = getUserIp(request);
			} catch (Exception e) {
				e.printStackTrace();
			}

			BoardVo bv = new BoardVo();
			bv.setSubject(subject);
			bv.setContents(contents);
			bv.setWriter(writer);
			bv.setPassword(password);
			bv.setMidx(midx);
			bv.setFilename(originFileName); // 파일 이름 DB컬럼 추가
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			bv.setIp(ip);

			BoardDao bd = new BoardDao();
			int maxbidx = bd.boardReply(bv);

			paramMethod = "S";
			if (maxbidx != 0) {
				url = request.getContextPath() + "/board/boardContent.aws?bidx=" + maxbidx;
			} else {
				url = request.getContextPath() + "/board/boardReply.aws?bidx=" + bidx;
			}
		} else if (location.equals("boardDownload.aws")) {
			System.out.println("boardDownload.aws");

			String filename = request.getParameter("filename");

			String savePath = "D:\\dev\\eclipse-workspace\\mvc_programming\\src\\main\\webapp\\images\\";

			ServletOutputStream sos = response.getOutputStream();

			String downfile = savePath + filename;
			// System.out.println("downfile:"+downfile);

			File f = new File(downfile);

			String header = request.getHeader("User-Agent");

			String fileName = "";
			response.setHeader("Cache-Control", "no-cache");
			if (header.contains("Chrome") || header.contains("Opera") || header.contains("Firefox")) {

				fileName = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

			} else if (header.contains("MSIE") || header.contains("Trident") || header.contains("Edge")) {

				fileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
				response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

			} else {
				response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			}

			FileInputStream in = new FileInputStream(f); // 파일을 버퍼로 읽어봐서 출력한다

			byte[] buffer = new byte[1024 * 8];

			while (true) {
				int count = in.read(buffer);
				if (count == -1) {
					break;
				}
				sos.write(buffer, 0, count);
			}

			in.close();
			sos.close();
		}

		// URL을 통한 페이지 이동
		if (paramMethod.equals("F")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response); // 요청 전달
		} else if (paramMethod.equals("S")) {
			response.sendRedirect(url); // 리다이렉트
		}
	}

	// 파일 이름 추출 메서드
	private String getFileName(Part part) {
		String header = part.getHeader("content-disposition");
		for (String item : header.split(";")) {
			if (item.trim().startsWith("filename")) {
				return item.substring(item.indexOf('=') + 2, item.length() - 1); // 파일 이름 반환
			}
		}
		return null; // 파일 이름이 없으면 null 반환
	}

	// 사용자 IP 주소 가져오는 메서드
	public String getUserIp(HttpServletRequest request) throws Exception {
		String ip = null;
		ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
			InetAddress address = InetAddress.getLocalHost();
			ip = address.getHostAddress();
		}
		return ip;
	}
}