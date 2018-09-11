package org.nautilusmim.test.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet2 extends HttpServlet {

	private static final long serialVersionUID = -5377923539712037699L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		Cookie ckEmail = new Cookie("email", "test2@it315.com");
		Cookie ckPhone = new Cookie("phone", "222222");
		Cookie ckSign = new Cookie("sign", "it315");
		
		resp.addCookie(ckEmail);
		resp.addCookie(ckPhone);
		resp.addCookie(ckSign);
		
		String cookieHeader = req.getHeader("Cookie");
		if(null == cookieHeader) {
			out.println("没有对应的Cookie Header信息<br/>");
		} else {
			out.println("Cookie Header：" + cookieHeader + "<br/>");
		}
		
		Cookie[] cookies = req.getCookies();
		for(int i = 0; null != cookies && i < cookies.length; i ++) {
			out.println(cookies[i].getName() + ": " + cookies[i].getValue() + "<br/>");
		}
	}

}
