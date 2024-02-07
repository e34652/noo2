package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.util.Map;
import java.net.URLEncoder; 
import java.net.URLDecoder; 
import java.io.IOException; 

public class Cookies {
	
	private Map<String, Cookie> cookieMap = new java.util.HashMap<String, Cookie>(); 
	//해시맵 객체 생성 후 다형성 구현, 변수명 cookieMap		
	//String = 키, Cookie = 밸류
	
	public Cookies(HttpServletRequest request) {// 클라이언트(이용자)가 HTTP 요청을 보내면 클라이언트의 정보를 담은 HttpServletRequest 객체를 받아 처리 
		Cookie[] cookies = request.getCookies();// request 변수안에 들어있는 쿠키들로 이루어진 쿠키 배열 cookies 선언  
		if (cookies != null) { // 쿠키가 포함되어 있다면
			for (int i = 0 ; i < cookies.length ; i++) { 
				// for문으로 모든 인덱스를 돌며 쿠키'맵'에 쿠키를 추가함
				// map에서 중복되는 쿠키는 더욱 최근의 쿠키로 덮어씌워짐
				cookieMap.put(cookies[i].getName(), cookies[i]); // 쿠키 이름
			}
		}
	}

	public Cookie getCookie(String name) {
		return cookieMap.get(name);
		//각각의 사용자들이 같은 이름의 쿠키를 받게됨, 서버마다 쿠키의 고유한 이름을 정해놓음
		//같은 웹서버를 이용하는 웹 어플리케이션은 모두 동일한 이름의 쿠키를 주고받을 수 있음 << 도메인과 경로가 같음 
	}
	
	public String getValue(String name) throws IOException {
		Cookie cookie = cookieMap.get(name); // 쿠키의 key 값으로 value를 불러와 cookie에 대입 
		if (cookie == null) { // 클라이언트 정보가 들어있지 않을 경우 null 반환 
			return null;
		}
		return URLDecoder.decode(cookie.getValue(), "utf-8"); 
		// cookie의 값을 utf-8 방식으로 디코딩해 원래의 형태로 복원
		// 쿠키를 생성할 때 안전한 전송을 위해 인코딩을 했기 때문
	} 

	public boolean exists(String name) {
		return cookieMap.get(name) != null; // 해당 name 값을 가진 쿠키가 있는지 없는지 true false로 반환
	}

	public static Cookie createCookie(String name, String value) // 안쓰임 어떤 용도인지?
	throws IOException {
		return new Cookie(name, URLEncoder.encode(value, "utf-8"));
	}

	public static Cookie createCookie(String name, String value, String path,
		int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String domain, // 안쓰임 어떤 용도인지?
			String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}

}
