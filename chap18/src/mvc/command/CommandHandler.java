package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler { // 각각의 요청에 대해 CommandHandler 인터페이스를 구현
	public String process(HttpServletRequest req, HttpServletResponse res)
	throws Exception;
}
