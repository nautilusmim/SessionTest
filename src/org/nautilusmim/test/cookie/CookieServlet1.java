package org.nautilusmim.test.cookie;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CookieServlet1 extends HttpServlet {

	private static final long serialVersionUID = 7462745859415693620L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		if(null == name || null == nickname) {
			out.println("请求参数中没有姓名和昵称<br/>");
			return;
		}
		
		if("".equals(name.trim()) || "".equals(nickname.trim())) {
			out.println("姓名和昵称不能为空白串<br/>");
		} else {
			Cookie ckName = new Cookie("name", name);
			Cookie ckNickname = new Cookie("nickname", nickname);
			ckNickname.setMaxAge(365 * 24 * 60 * 60);
			Cookie ckEmail = new Cookie("email", "test1@it315.com");
			Cookie ckPhone = new Cookie("phone", "111111");
			resp.addCookie(ckName);
			resp.addCookie(ckNickname);
			resp.addCookie(ckEmail);
			resp.addCookie(ckPhone);
		}
		
		Cookie[] cookies = req.getCookies();
		String lastNickname = "";
		for(int i = 0; null != cookies && i < cookies.length; i ++) {
			Cookie cookie = cookies[i];
			if("nickname".equals(cookie.getName())) {
				lastNickname = cookie.getValue();
				break;
			}
		}
		if("".equals(lastNickname)) {
			out.println("欢迎你，新用户<br/>");
		} else {
			out.println("欢迎你，" + lastNickname + "<br/>");
		}
		
		String cookieHeader = req.getHeader("Cookie");
		if(null == cookieHeader) {
			out.println("没有对应的Cookie Header信息");
		} else {
			out.println("Cookie Header：" + cookieHeader);
		}
	}

}
