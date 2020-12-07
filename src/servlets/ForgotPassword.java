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
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// grab params from login form
		String username = req.getParameter("username");
		String answer = req.getParameter("answer");
		
		// instantiate PasswordResetController and attempt reset
		PasswordResetController resetController = new PasswordResetController(username, answer);
		try {
			if (resetController.reset()) {
				// display main hub
				String page = getHtmlString(req.getServletContext().getRealPath("/changePass.html"));
				resp.getWriter().write(page);
			}
			else {
				// Display error message
				resp.getWriter().write(resetController.resetDenied());
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

