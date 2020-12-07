package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// grab params from create user form
		
		String password = req.getParameter("password");
		
		
		User u = new User();
		
		u.setPassword(password);
		
		UserDao userDao = new UserDao();
		userDao.resetPassword(u);
		
		// User brought to Login after creation
		String page = getHtmlString(req.getServletContext().getRealPath("/index.html"));
		resp.getWriter().write(page);
		
	}
	
	public String getHtmlString(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line="";
		StringBuffer buffer = new StringBuffer();
		while((line=reader.readLine()) != null) {
			buffer.append(line);
		}
		
		reader.close();
		String page = buffer.toString();
		
		return page;
	}


}