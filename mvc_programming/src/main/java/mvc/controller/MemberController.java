package mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dao.MemberDao;
import mvc.vo.MemberVo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/MemberController") // 서블릿 : 자바로 만든 웹페이지(접속주소 : /MemberController)
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String location; // 멤버변수(전역) 초기화 => 이동할 페이지
	public MemberController(String location) {
		this.location = location;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 넘어온 모든 값은 이 곳에서 처리해서 분기한다. - Controller 역할

//		System.out.println("값이 넘어오나요 ?");
		// 전체 주소를 추출
		// String uri = request.getRequestURI();
		// System.out.println("uri : " + uri); // uri :
		// /mvc_programming/member/memberSigninAction.aws
		// String[] location = uri.split("/");
		String paramMethod = ""; // 전송방식이 sendRedirect면 S, forward면 F
		String url = "";

		if (location.equals("memberSigninAction.aws")) {
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");
			String memberPwIsRight = request.getParameter("memberPwIsRight");
			String memberName = request.getParameter("memberName");
			String memberGender = request.getParameter("memberGender");
			String memberBirth = request.getParameter("memberBirth");
			String memberAddress = request.getParameter("memberAddress");
			String memberPhone = request.getParameter("memberPhone");
			String memberEmail = request.getParameter("memberEmail");

			String[] memberHobby = request.getParameterValues("memberHobby");
			String memberInHobby = "";
			for (int i = 0; i < memberHobby.length; i++) {
				memberInHobby = memberInHobby + memberHobby[i] + ", ";
			}

			MemberDao md = new MemberDao();
			// 객체 안에 생성해놓은 멤버 메서드를 호출해서 값을 꺼낸다.
			int value = md.memberInsert(memberId, memberPw, memberName, memberGender, memberBirth, memberAddress,
					memberPhone, memberEmail, memberInHobby);

			// value값이 1이면 입력성공, 0이면 입력실패
			// 1이면 성공했기때문에 다른 페이지로 이동시키고, 0이면 다시 회원가입페이지로 간다.
			String msg = "";
			HttpSession session = request.getSession();

			if (value == 1) {// index.jsp 파일은 web.xml 웹 설정파일에 기본등록되어있어 생략 가능
				msg = "회원가입되었습니다.";
				session.setAttribute("msg", msg);
				url = request.getContextPath() + "/"; // request.getContextPath() : 프로젝트이름
				/* response.sendRedirect(pageUrl); */ // 전송방식 sendRedirect는 요청받으면 다시 그쪽으로 가라고 지시하는
				// 방법

			} else {
				msg = "회원가입 중 오류가 발생했습니다.";
				session.setAttribute("msg", msg);
				url = request.getContextPath() + "/member/memberSignin.jsp";
				/* response.sendRedirect(pageUrl); */ // SendRedirect방식은 새롭게 다른 쪽으로 가라고 지시
			}
			paramMethod = "S"; // 밑에서 sendRedirect 방식으로 처리

			// System.out.println("msg는 ? " + msg);
		} else if (location.equals("memberSignin.aws")) {
			// System.out.println("들어왔나 ?");
			url = "/member/memberSignin.jsp";
			// RequestDispatcher rd = request.getRequestDispatcher(uri2);
			// System.out.println("객체가 생겼니 ?" + rd);
			// rd.forward(request, response); // 포워드방식 : 내부 안에서 넘겨서 토스하겠다는 뜻
			paramMethod = "F"; // 하단에서 포워드로 처리합니다.
		} else if (location.equals("memberLogin.aws")) {
			// System.out.println("들어왔나 ?");
			url = "/member/memberLogin.jsp";
			// RequestDispatcher rd = request.getRequestDispatcher(uri3);
			// rd.forward(request, response);
			paramMethod = "F"; // 하단에서 포워드로 처리합니다.
		} else if (location.equals("memberLoginAction.aws")) {
			// System.out.println("들어왔나 ?");
			String memberId = request.getParameter("id");
			String memberPw = request.getParameter("pw");
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLoginCheck(memberId, memberPw);
			// System.out.println("mv객체가 생겼나요 ? " + mv);

			if (mv == null) {
				url = request.getContextPath() + "/member/memberLogin.aws";
				paramMethod = "S";
				// response.sendRedirect(request.getContextPath() + "/member/memberLogin.aws");
				// // 해당 주소로 다시 가세요. 해당하는 값이
				// 없을 때
			} else {
				// 해당되는 로그인 사용자가 있으면 세션에 회원정보 담아서 메인으로 가라
				String mid = mv.getMemberId(); // 아이디 꺼내기
				int midx = mv.getMidx(); // 회원번호 꺼내기
				String mname = mv.getMemberName(); // 이름 꺼내기

				HttpSession session = request.getSession();
				session.setAttribute("mid", mid);
				session.setAttribute("midx", midx);
				session.setAttribute("mname", mname);

				url = request.getContextPath() + "/";
				paramMethod = "S";

				// response.sendRedirect(request.getContextPath() + "/"); // 로그인되었으면 메인으로 가라
			}
		} else if (location.equals("memberLogOut.aws")) {
			// System.out.println("memberLogout");

			// 세션 삭제
			HttpSession session = request.getSession();
			session.removeAttribute("mid");
			session.removeAttribute("midx");
			session.removeAttribute("mname");
			session.invalidate();

			url = request.getContextPath() + "/";
			paramMethod = "S";

			// response.sendRedirect(request.getContextPath() + "/");
		} else if (location.equals("memberList.aws")) {
			// System.out.println("memberList");

			// 1. 메서드 불러서 처리하는 코드를 만들어야한다.
			MemberDao md = new MemberDao(); // 객체생성
			ArrayList<MemberVo> alist = md.memberSelectAll();

			request.setAttribute("alist", alist);

			// 2. 보여줄 페이지를 forward 방식으로 보여준다. 공유의 특징을 가지고 있음.

			url = "/member/memberList.jsp";
			paramMethod = "F";
			/*
			 * RequestDispatcher rd = request.getRequestDispatcher(uri4);
			 * rd.forward(request, response);
			 */
		} else if (location.equals("memberIdCheck.aws")) {
			// System.out.println("memberIdCheck");
			String memberId = request.getParameter("memberId");

			MemberDao md = new MemberDao();
			int cnt = md.memberIdCheck(memberId);

			// System.out.println("cnt : " + cnt);
			PrintWriter out = response.getWriter();
			out.println("{\"cnt\":\"" + cnt + "\"}");
		}

		if (paramMethod.equals("F")) {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (paramMethod.equals("S")) {
			response.sendRedirect(url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}