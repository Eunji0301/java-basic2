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
import mvc.dao.CommentDao;
import mvc.vo.BoardVo;
import mvc.vo.CommentVo;
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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String location; // 멤버변수(전역) 초기화 => 이동할 페이지

	public CommentController(String location) {
		this.location = location;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (location.equals("commentList.aws")) {

			String bidx = request.getParameter("bidx");
			CommentDao cd = new CommentDao();
			ArrayList<CommentVo> alist = cd.commentSelectAll(Integer.parseInt(bidx));

			int cidx = 0;
			String cwriter = "";
			String ccontents = "";
			String writeday = "";
			String delyn = "";
			int midx = 0;

			String str = "";
			for (int i = 0; i < alist.size(); i++) {
				cidx = alist.get(i).getCidx();
				cwriter = alist.get(i).getCwriter();
				ccontents = alist.get(i).getCcontents();
				writeday = alist.get(i).getWriteday();
				delyn = alist.get(i).getDelyn();
				midx = alist.get(i).getMidx();
				str += "{\"cidx\" : \"" + cidx + "\", \"cwriter\" : \"" + cwriter + "\", \"ccontents\" : \"" + ccontents
						+ "\", \"writeday\" : \"" + writeday + "\", \"delyn\" : \"" + delyn + "\", \"midx\" : \"" + midx
						+ "\"}";
				// {"cidx" : "1", ...}
			}
			PrintWriter out = response.getWriter();
			out.println("[" + str + "]");

		} else if (location.equals("commentWriteAction.aws")) {
			// System.out.println("commentWriteAction.aws");

			String cwriter = request.getParameter("cwriter");
			System.out.println("cwriter : " + cwriter);
			String ccontents = request.getParameter("ccontents");
			System.out.println("ccontents : " + ccontents);
			String bidx = request.getParameter("bidx");
			System.out.println("bidx : " + bidx);
			String midx = request.getParameter("midx");
			System.out.println("midx : " + midx);

			CommentVo cv = new CommentVo();
			cv.setCwriter(cwriter);
			cv.setCcontents(ccontents);
			cv.setBidx(Integer.parseInt(bidx));
			cv.setMidx(Integer.parseInt(midx));

			// Comment 객체생성
			CommentDao cd = new CommentDao();
			int value = cd.commentInsert(cv);

			PrintWriter out = response.getWriter();
			String str = "{\"value\" : \"" + value + "\"}";
			out.println(str);

		} else if (location.equals("commentDeleteAction.aws")) {
			String cidx = request.getParameter("cidx");
			System.out.println("cidx : " + cidx);

			// delyn Y로 업데이트하는 메서드를 만들어서 호출
			CommentDao cd = new CommentDao();
			int value = cd.commentDelete(Integer.parseInt(cidx));

			// 그리고 나서 화면에 실행성공여부를 json 파일로 보여준다.
			PrintWriter out = response.getWriter();
			String str = "{\"value\" : \"" + value + "\"}";
			out.println(str);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public String getUserIp(HttpServletRequest request) throws Exception {

		String ip = null;
		// HttpServletRequest request = ((ServletRequestAttributes)
		// RequestContextHolder.currentRequestAttributes()).getRequest();

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