package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dto.Member;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter("/AutoLoginFilter")
public class AutoLoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AutoLoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession(true);
        if (session.getAttribute("login") != null) {
            chain.doFilter(request, response);
            httpResponse.sendRedirect("index.do");
            return;
        }

        // 쿠키에서 사용자 정보 가져오기
	        String id = null;
	        String email = null;
	        Cookie[] cookies = httpRequest.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if (cookie.getName().equals("AutoLogin")) {
	                	String input = cookie.getValue();
	                	String[] loginInfo = input.split("/");
	                	id = loginInfo[0];
	                	email = loginInfo[1];
	                	Member member = MemberDao.getInstance().selectForLogin(id, email);
	                	session.setAttribute("login", member);
	                }
	            }
	        }
	        
        

        // 필터 체인 진행
        chain.doFilter(request, response);
        httpResponse.sendRedirect("index.do");
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
