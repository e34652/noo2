package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		if (path.equals("/list.do")) {
			MemberDao mdao = MemberDao.getInstance();

			String pageNoval = request.getParameter("pageNo");
			int pageNo = 1;
			if (pageNoval != null) {
				pageNo = Integer.parseInt(pageNoval);
			}
			int total = mdao.selectCount();
			ArrayList<Member> list = mdao.selectPage((pageNo - 1) * 5, 5);
			ArticlePage articlePage = new ArticlePage(total, pageNo, 5, null, list);
			request.setAttribute("Article", articlePage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/view.do")) {
			// num과 일치하는 행 불러오기
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDao dao = BoardDao.getInstance();
			Board board = dao.selectOne(num, true);

			// content 공백 줄바꿈
			// dto의 값을 불러와 공백과 줄바꿈 처리를 한 후 setter를 통해 적용시킨다
			String title = board.getTitle().replace(" ", "&nbsp;");
			board.setTitle(title);
			String content = board.getContent().replace(" ", "&nbsp;").replace("\n", "<br>");
			board.setContent(content);

			// 포워딩 작업
			request.setAttribute("bd", board);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/view.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/login.do")) {

			// 요청은 사용자가 URL을 입력하거나, 링크를 클릭하거나, 폼을 제출하는 등의 동작을 통해 생성됨
			// 사용자의 요청(request)에 포함되어있는 id라는 파라미터를 get하여 id에 저장,
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			Member member = MemberDao.getInstance().selectForLogin(id, email);
			if (member != null) {
				HttpSession session = request.getSession(); // 세션 생성, 또는 이미 생성된 세션을 불러옴, 일반적으로 하나의 연결에서 하나의 세션만을 가짐
				session.setAttribute("member", member);
				response.sendRedirect("list.do");
			} else {
				response.sendRedirect("loginForm.jsp");
			}
		} else if (path.equals("/logout.do")) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			response.sendRedirect("loginForm.do");

		} else if (path.equals("/loginForm.do")) {
			response.sendRedirect("loginForm.jsp");

		} else if (path.equals("/write.do")) {

			String tmp = request.getParameter("num");
			int num = (tmp != null && tmp.length() > 0) ? Integer.parseInt(tmp) : 0;
			// 항상 null이나 빈 값을 어떻게 처리할지 잊지 말아야 한다

			String action = "insert.do";
			Board board = null;

			// 글 번호가 주어졌으면, 수정(update.do)으로 변경
			if (num > 0) {
				BoardDao dao = BoardDao.getInstance();
				board = dao.selectOne(num, false);
				action = "update.do?num=" + num;
			}
			request.setAttribute("board", board);
			request.setAttribute("action", action);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/write.jsp");
			dispatcher.forward(request, response);
			
		} else if (path.equals("/delete.do")) {
			int memberno = Integer.parseInt(request.getParameter("memberno"));
			MemberDao mdao = MemberDao.getInstance();
			mdao.delete(memberno);
			response.sendRedirect("list.do");
			
		} else if (path.equals("/memberUpdate.do")) {
			
			int memberno = Integer.parseInt(request.getParameter("memberno"));
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			
			MemberDao mdao = MemberDao.getInstance();
			Member member = new Member(memberno, id, email, name);
			mdao.update(member);
			
			response.sendRedirect("list.do");
			
		} else if (path.equals("/updateForm.do")) {
			int memberno = Integer.parseInt(request.getParameter("memberno"));
			
			MemberDao mdao = MemberDao.getInstance();
			Member member = mdao.selectMember(memberno);
			request.setAttribute("member", member);
			request.setCharacterEncoding("UTF-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/memberUpdateForm.jsp");
			dispatcher.forward(request, response);
			
		}else if (path.equals("/memberInput.do")) {
			
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			
			MemberDao mdao = MemberDao.getInstance();
			Member member = new Member(0, id, email, name);
			mdao.insert(member);
			response.sendRedirect("/loginForm.jsp");

			
		}
	}
}
