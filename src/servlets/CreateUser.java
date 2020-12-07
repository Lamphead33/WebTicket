package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import dao.UserDao;


/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	public void compareString( ) {
//		String pass1 = DOM ;
//		String pass2
//		
//	}
//	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// grab params from create user form
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String answer = req.getParameter("answer");
		String role = req.getParameter("role");
		int setFalse = 0;
		int setTrue = 1;
		
		System.out.print(role);
		Integer roleInt = Integer.valueOf(role); 
		
		
		User u = new User();
		u.setUserName(username);
		u.setPassword(password);
		u.setAnswer(answer);
		
		
		// set all roles to false
		u.setIsEmployee(setFalse);
		u.setIsTech(setFalse);
		u.setIsAdmin(setFalse);
		
		// then do check of role param and set corresponding to true
		if (roleInt == 1) {
			u.setIsEmployee(setTrue);
			System.out.print("Adding new Employee");
		}
		else if (roleInt == 2) {
			u.setIsTech(setTrue);
			System.out.print("Adding new Tech");
		}
		else if (roleInt == 3){
			u.setIsAdmin(setTrue);
			System.out.print("Adding new Admin");
		}
		else {
			System.out.print("Something Went Wrong");

		}

		
		UserDao userDao = new UserDao();
		userDao.create(u);
		
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
