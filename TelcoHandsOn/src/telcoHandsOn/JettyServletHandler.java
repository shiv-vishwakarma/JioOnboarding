package telcoHandsOn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jio.telco.framework.resource.ResourceBuilder;

public class JettyServletHandler extends HttpServlet {

	/**
	 * 
	 */
	
	final static Logger loggerABC = LogManager.getLogger(JettyServletHandler.class);
	private static final long serialVersionUID = 1L;
	PlayersService playerService = new PlayersService();
	
	public void printlog(String msg, String mname) {
		ResourceBuilder.logger().setLogType(com.jio.telco.framework.logger.Constants.LOG_LEVEL_INFO)
				.setClassMethodName(this.getClass().getName() + ":" + mname)
				.setLogger(loggerABC).setServiceStatusDescription(msg)
				.writeLog();
	}
	
	public void printExceptionlog(String msg, String mname, Exception e) {
		ResourceBuilder.logger().setLogType(com.jio.telco.framework.logger.Constants.LOG_LEVEL_ERROR)
				.setClassMethodName(this.getClass().getName() + ":" + mname)
				.setLogger(loggerABC).setException(e)
				.setErrorMessage("Exception Message").setServiceStatusDescription(msg).writeExceptionLog();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			playerService.createList();
			printlog("Logging the get method", "doGet");
			ArrayList<Players> list = (ArrayList<Players>) playerService.getPlayersList();
			response.getWriter().write(list.toString());
		}catch(Exception e){
			printExceptionlog("Exception in doGet: ", "doGet", e);
		}
		
		// response.getWriter().println("<h1> Welcome to GET method of
		// JettyServletHandler </h1>");
		// return list;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		String p = IOUtils.toString(request.getInputStream());
		playerService.createList();
		System.out.println(p);
		// String status = playerService.addPlayer(p);
		// response.getWriter().write(status.toString());
		// response.getWriter().println("<h1> Welcome to GET method of
		// JettyServletHandler </h1>");
		// return list;
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		// playerService.createList();
		String p = IOUtils.toString(request.getInputStream());
		System.out.println("Name p: " + p);
		printlog("Logging the get method", "doDelete");
		String sts = playerService.deletePlayer(p);
		response.getWriter().write(sts);
	}

}
