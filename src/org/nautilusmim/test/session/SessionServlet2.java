package org.nautilusmim.test.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet2 extends HttpServlet {

	private static final long serialVersionUID = 3287383023900670017L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		Integer count = 0;
		HttpSession session = req.getSession();
		Integer sessionCount = (Integer)session.getAttribute("count");
		if(null != sessionCount) {
			count = sessionCount.intValue();
		}
		out.println("当前会话中发生了" + (++count) + "次访问<br/>");
		session.setAttribute("count", count);
		
		count = 0;
		ServletContext application = this.getServletContext();
		Integer applicationCount = (Integer)application.getAttribute("count");
		if(null != applicationCount) {
			count = applicationCount.intValue();
		}
		out.println("Web应用程序中发生了" + (++count) + "次访问<br/>");
		application.setAttribute("count", count);
		
		out.println("<a href='" + resp.encodeURL("SessionServlet1") +  "'>访问SessionServlet1</a>");
	}
	
}
