package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import bean.User;
import dao.UserDao;

/**
 * Servlet implementation class ViewTicket
 */
@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter writer = res.getWriter();
		HttpSession session=req.getSession(false);
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<link rel=\"stylesheet\" href=\"style.css\">");
		writer.println("<meta charset=\"ISO-8859-1\">");
		writer.println("<script src=\"https://kit.fontawesome.com/8f78e01f5a.js\" crossorigin=\"anonymous\"></script>");
		writer.println("<title>Manage Users</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<div class=\"topnav\">");
		writer.println("<a href=\"/WebTicketApp/hub.html\">Hub</a>");
		writer.println("<a href=\"/WebTicketApp/help.html\">Help</a>");
		writer.println("<a class=\"active\" href=\"/WebTicketApp/ManageUsers\">Users</a>");
		writer.println("<div class=\"dropdown\"><button class=\"dropbtn\">Roles<i class=\"fa fa-caret-down\"></i></button><div class=\"dropdown-content\"><a href=\"#\">Employee</a><a href=\"#\">Technician</a><a href=\"#\">Administrator</a></div></div>");
		writer.println("<div class=\"logout-container\">");
		writer.println("<form action=\"/WebTicketApp/index.html\">");
		writer.println("<button class=\"fill\" type=\"submit\"><i class=\"fas fa-door-open fa-x\"></i> Logout</button>");
		writer.println("</form>");
		writer.println("</div>");
		writer.println("</div>");
		writer.println("<h1>Manage Users:</h1>");		
		ArrayList<User> list = UserDao.getAllUsers();
		writer.print("<table id=\"ticktable\">");
		writer.println("<tr><th>User ID</th><th>User Name</th><th>Employee?</th><th>Technician?</th><th>Admin?</th><th>Delete User?</th></tr>");
		for(User u:list){
			writer.println("<form method=\"post\" action=\"/WebTicketApp/DeleteUser\""
					+"<tr><td>"
					// Hidden fields to grab ticket values
					+"<input type=\"hidden\" id=\"userID\" name=\"userID\" value=\"" + u.getUserID() +"\" />"
					+"<input type=\"hidden\" id=\"userName\" name=\"userName\" value=\"" + u.getUserName() +"\" />"
					+"<input type=\"hidden\" id=\"userName\" name=\"userName\" value=\"" + u.getPassword() +"\" />"
					+"<input type=\"hidden\" id=\"userName\" name=\"userName\" value=\"" + u.getAnswer() +"\" />"
					+"<input type=\"hidden\" id=\"userIsEmployee\" name=\"userIsEmployee\" value=\"" + u.getIsEmployee() +"\" />"
					+"<input type=\"hidden\" id=\"userIsTech\" name=\"userIsTech\" value=\"" + u.getIsTech() +"\" />"
					+"<input type=\"hidden\" id=\"userIsAdmin\" name=\"userIsAdmin\" value=\"" + u.getIsAdmin() +"\" />"
					+u.getUserID()+"</td><td>"
					+u.getUserName()+"</td><td>"
					+u.getIsEmployee()+"</td><td>"
					+u.getIsTech()+"</td><td>"
					+u.getIsAdmin()+"</td><td>"
					+" <input type=\"submit\" value=\"Delete User\" id=\"submit\" class=\"button\" />"+"</td><td>"
					+"</form>");
			writer.println("</tr>");
		}
		writer.println("</table>");
		writer.println("<div class=\"spacerLg\"></div>");
		writer.println( "<button type=\"button\">Update User Status</button>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

