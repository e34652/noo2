package mvjsp.jdbc.loader;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.StringTokenizer;

public class DBCPInit extends HttpServlet { //각종 커넥션을 위한 드라이버 로딩

	public void init(ServletConfig config) throws ServletException {
		try {
			String drivers = config.getInitParameter("jdbcdriver");
			StringTokenizer st = new StringTokenizer(drivers, ",");
			while (st.hasMoreTokens()) {
				String jdbcDriver = st.nextToken();
				Class.forName(jdbcDriver);
			}
			
			Class.forName("org.apache.commons.dbcp.PoolingDriver");

		} catch(Exception ex) {
			throw new ServletException(ex);
		}
	}
}
