package mvc.hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class HelloHandler implements CommandHandler { //hello라는 요청에 대해 CommandHandler 인터페이스를 구현

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("hello", "안녕하세요!");
		//핸들러가 표현될 페이지의 경로를 입력
		return "/WEB-INF/view/hello.jsp";
	}

}
