package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import dao.MemberDao;
import dto.ArticlePage;
import dto.Board;
import dto.Member;

/**
 * Servlet implementation class DispatcherServlet
 */
//서버에 도착한 요청의 url이 .do로 끝날 경우 이 서블릿이 호출된다
// "/*.do"로 쓰지 않도록 주의. 이 코드는 /로 시작하고 .do로 끝나는 URL에 대한 것이며 웹 애플리케이션의 루트 디렉토리에 있는 요청만 해당된다
// 반면에 "*.do"는 웹 어플리케이션의 모든 경로가 해당된다
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatcherServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 클라이언트가 어떤 요청을 했는지 알기위해 url의 일부를 추출

		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));

		// 요청에 따른 조건문 작성
		if (path.equals("/index.do")) {
			String loginStatus = null;
			Member member = (Member) request.getSession().getAttribute("login");
			if (member != null && member.getId() != null) {
				loginStatus = "true";
				if (member.getId().equals("admin")) {
					loginStatus = "admin";
				}
			} else {
				loginStatus = "false";
			}

			request.setAttribute("loginStatus", loginStatus);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/boardList.do")) {
			Member member = (Member) request.getSession().getAttribute("login");
			if (member == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberLoginForm.jsp");
				dispatcher.forward(request, response);
			}

			BoardDao bdao = BoardDao.getInstance();

			String pageNoval = request.getParameter("pageNo");
			int pageNo = 1;
			if (pageNoval != null) {
				pageNo = Integer.parseInt(pageNoval);
			}
			int total = bdao.selectCount();
//			ArrayList<Board> list = bdao.selectList();
			ArrayList<Board> list = bdao.selectPage((pageNo - 1) * 5, 5);
			ArticlePage articlePage = new ArticlePage(total, pageNo, 5, list, null);
			System.out.println(articlePage);
			for(Board board : articlePage.getContentB()) {
			System.out.println(board);}
			request.setAttribute("ArticleB", articlePage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/boardview/boardList.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/boardView.do")) {
			// num과 일치하는 행 불러오기
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDao bdao = BoardDao.getInstance();
			Board board = bdao.selectOne(num, true);

			// content 공백 줄바꿈
			// dto의 값을 불러와 공백과 줄바꿈 처리를 한 후 setter를 통해 적용시킨다
			String title = board.getTitle().replace(" ", "&nbsp;");
			board.setTitle(title);
			String content = board.getContent().replace(" ", "&nbsp;").replace("\n", "<br>");
			board.setContent(content);

			// 포워딩 작업
			request.setAttribute("board", board);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/boardview/boardView.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/boardWrite.do")) {
			// 글 번호 값 얻기, 주어지지 않았으면 0으로 설정
			// int num = Integer.parseInt(request.getParameter("num"));
			String tmp = request.getParameter("num");
			int num = (tmp != null && tmp.length() > 0) ? Integer.parseInt(tmp) : 0;
			String action = "boardInsert.do";
			Board board = null;
			// 글 번호가 주어졌으면, 글 수정 모드
			if (num > 0) {
				BoardDao dao = BoardDao.getInstance();
				board = dao.selectOne(num, false);

				// 글 수정 모드일 때는 저장 버튼을 누르면 UPDATE 실행
				action = "boardUpdate.do?num=" + num;
			}
			request.setAttribute("board", board);
			request.setAttribute("action", action);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/boardview/boardWrite.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/boardInsert.do")) {
			HttpSession session = request.getSession(false);
			Member member = (Member) session.getAttribute("login");
			System.out.println(member);

			int memberno = member.getMemberno();
			String memberId = member.getId();
			String memberName = member.getName();
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardDao bdao = BoardDao.getInstance();
			Board board = new Board(0, title, content, null, memberno, memberName);
			bdao.insert(board, memberId);
			response.sendRedirect("boardList.do");

		} else if (path.equals("/boardUpdate.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDao bdao = BoardDao.getInstance();
			Board board = bdao.selectOne(num, false);
			bdao.update(board);
			response.sendRedirect("boardList.do");

		} else if (path.equals("/boardDelete.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDao bdao = BoardDao.getInstance();
			bdao.delete(num);
			response.sendRedirect("boardList.do");

		} else if (path.equals("/memberLogin.do")) {
			// 요청은 사용자가 URL을 입력하거나, 링크를 클릭하거나, 폼을 제출하는 등의 동작을 통해 생성됨
			// 사용자의 요청(request)에 포함되어있는 id라는 파라미터를 get하여 id에 저장,
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String check = request.getParameter("radio");

			Member member = MemberDao.getInstance().selectForLogin(id, email);
			if (member != null && member.getId() != null) {// 일치하는 회원이 있는 경우
				HttpSession session = request.getSession(true);
				session.setAttribute("login", member);

				if (member.getId().equals("admin")) { // 회원의 ID가 admin인 경우 관리자 페이지로 이동
					session.setMaxInactiveInterval(1800);
					response.sendRedirect("index.do");

				} else if (check != null && check.equals("login")) { // 자동 로그인을 위한 쿠키 생성

					String loginInfo = String.format("%s/%s", member.getId(), member.getEmail());
					Cookie cookie = new Cookie("AutoLogin", loginInfo);
					cookie.setMaxAge(60 * 60 * 24 * 15);
					cookie.setPath("/");
					response.addCookie(cookie);
					response.sendRedirect("index.do");

				} else if (check != null && check.equals("id")) { // ID 저장을 위한 쿠키 생성

					Cookie cookie = new Cookie("AutoId", id);
					cookie.setMaxAge(60 * 60 * 24 * 15);
					cookie.setPath("/");
					response.addCookie(cookie);
					response.sendRedirect("index.do");
				} else {
					response.sendRedirect("index.do");
				}
			} else {
				response.sendRedirect("memberLoginForm.do");
			}

		} else if (path.equals("/memberList.do")) {
			MemberDao mdao = MemberDao.getInstance();

			String pageNoval = request.getParameter("pageNo");
			int pageNo = 1;
			if (pageNoval != null) {
				pageNo = Integer.parseInt(pageNoval);
			}
			int total = mdao.selectCount();
			System.out.println(total);
			ArrayList<Member> list = mdao.selectPage((pageNo - 1) * 5, 5);
			ArticlePage articlePage = new ArticlePage(total, pageNo, 5, null, list);
			System.out.println(articlePage);
			request.setAttribute("ArticleM", articlePage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberList.jsp");
			dispatcher.forward(request, response);

			// memberLogout
		} else if (path.equals("/memberLogout.do")) {
			HttpSession session = request.getSession(false);

			if (session != null) {
				session.invalidate();

				Cookie cookie = new Cookie("autoLogin", "");
				cookie.setMaxAge(0);
				cookie.setPath("/"); // 쿠키의 경로를 설정하여 해당 경로의 모든 페이지에서 쿠키가 제거되도록 함
				response.addCookie(cookie);
			}
			response.sendRedirect("index.do");

		} else if (path.equals("/memberLoginForm.do")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberLoginForm.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/memberDelete.do")) {
			int memberno = Integer.parseInt(request.getParameter("memberno"));
			MemberDao mdao = MemberDao.getInstance();
			mdao.delete(memberno);
			response.sendRedirect("memberList.do");

		} else if (path.equals("/memberUpdate.do")) {

			int memberno = Integer.parseInt(request.getParameter("memberno"));
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String name = request.getParameter("name");

			MemberDao mdao = MemberDao.getInstance();
			Member member = new Member(memberno, id, email, name);
			mdao.update(member);

			response.sendRedirect("memberList.do");

		} else if (path.equals("/memberUpdateForm.do")) {
			int memberno = Integer.parseInt(request.getParameter("memberno"));

			MemberDao mdao = MemberDao.getInstance();
			Member member = mdao.selectMember(memberno);
			request.setAttribute("member", member);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberUpdateForm.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/memberInsert.do")) {

			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String name = request.getParameter("name");

			MemberDao mdao = MemberDao.getInstance();
			Member member = new Member(0, id, email, name);
			mdao.insert(member);
			response.sendRedirect("memberList.do");

		} else if (path.equals("/memberSignUp.do")) {

			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String name = request.getParameter("name");

			MemberDao mdao = MemberDao.getInstance();
			Member member = new Member(0, id, email, name);
			mdao.insert(member);
			response.sendRedirect("memberLoginForm.do");

		} else if (path.equals("/memberSignUpForm.do")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberSignUpForm.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/memberInsertForm.do")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberInsertForm.jsp");
			dispatcher.forward(request, response);
		}
	}
}
