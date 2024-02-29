package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingFile extends HttpServlet {

    // <커맨드, 핸들러인스턴스> 매핑 정보 저장
    private Map<String, CommandHandler> commandHandlerMap = 
    		new HashMap<>();

    public void init() throws ServletException {
    	// web.xml의 초기 설정값을 불러와(init Param 참조)
    	// properties의 핸들러 목록을 prop에 로드하고 
    	// prop을 iterator로 돌려 핸들러 클래스 파일의 객체를 생성해 Map에 넣는 과정
        String configFile = getInitParameter("configFile"); 
        Properties prop = new Properties();
        
      //web.xml에서 설정한 configFile값인 commandHandler.properties 파일 경로를 configFilePath에 저장
        String configFilePath = getServletContext().getRealPath(configFile);
        
      //경로에 있는 properties 파일의 문자를 그대로 읽음(commandHandler.properties)
        try (FileReader fis = new FileReader(configFilePath)) {  
            prop.load(fis); // 읽은 문자열을 prob에 로드 
        } catch (IOException e) {
            throw new ServletException(e);
        }
        
        //prop의 keyset을 읽는 iterator 선언 후 가동
        //keyset = key값만을 반환해 set을 만드는 메서드 
        //올바른 프로퍼티 파일 형식을 가정한다면, = 기호 이후의 값은 해당 키에 대한 값(value)으로 간주되어 keySet에 반영되지 않음
        Iterator keyIter = prop.keySet().iterator();
        while (keyIter.hasNext()) {
        	
        	// 불러온 Key값(=hello)을 command에 대입하고 
            String command = (String) keyIter.next(); 
            //getProperty() 메서드를 사용해 command(=hello)와 대응하는 값(=mvc.hello.HelloHandler)을 반환함   
            String handlerClassName = prop.getProperty(command);  
            try {
            	//mvc.hello.HelloHandler 경로에 있는 핸들러 파일의 객체 생성 
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance =  // handlerClassName과 일치하는 경로에 있는 핸들러의 객체를 생성
                        (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance); // 핸들러의 객체를 맵에 넣음 
            } catch (ClassNotFoundException | InstantiationException 
            		| IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    	//html문서에서 request 객체를 통해 넘겨받은 cmd를 변수에 대입  
    	//cmd는 웹에서 넘겨받은 요소의 name이며 이는 얼마든지 바꿀 수 있다 
        String command = request.getParameter("cmd"); 
        
      //넘겨받은 cmd 값을 키로 맵에 저장했던 핸들러를 불러옴
        CommandHandler handler = commandHandlerMap.get(command); 
        
        // null 체크 후 null인 경우 NullHandler생성
        if (handler == null) {
            handler = new NullHandler();
        }
        String viewPage = null;
        try {
        	//핸들러의 구현메소드 process를 실행한 결과는 request객체에 set되고 
        	//메소드의 리턴값으로 핸들러의 내용이 표현될 페이지를 받아와 viewPage에 저장
            viewPage = handler.process(request, response);
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        if (viewPage != null) {
        	//viewPage에 저장된 표현될 페이지로 setAttribute되어있는 request객체를 포워딩함  
	        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	        dispatcher.forward(request, response);
        }
    }
}
