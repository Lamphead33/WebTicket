package servlets;

import defpac.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// grab params from login form
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		// instantiate LoginController and attempt login
		LoginController LoginCon = new LoginController(username, password);
		try {
			if (LoginCon.login()) {
				// display main hub
				String page = getHtmlString(req.getServletContext().getRealPath("/hub.html"));
				resp.getWriter().write(page);
			}
			else {
				// Display error message
				resp.getWriter().write(LoginCon.loginDenied());
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// Returns HTML code as string
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
