package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SubTicket;
import bean.Ticket;
import dao.TicketDao;

/**
 * Servlet implementation class ManageSubTickets
 */
@WebServlet("/ManageSubTickets")
public class ManageSubTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageSubTickets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String ticketId = req.getParameter("ticketID");
		String ticketName = req.getParameter("ticketName");
		String ticketDesc = req.getParameter("ticketDesc");
		String ticketDateCreated = req.getParameter("dateCreated");
		int id = Integer.parseInt(ticketId);
		Ticket t = new Ticket();
		t.setTicketName(ticketName);
		t.setTicketDesc(ticketDesc);
		t.setDateCreated(ticketDateCreated);
		t.setTicketID(id);
		
		PrintWriter writer = res.getWriter();
		HttpSession session=req.getSession(false);
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<link rel=\"stylesheet\" href=\"style.css\">");
		writer.println("<meta charset=\"ISO-8859-1\">");
		writer.println("<script src=\"https://kit.fontawesome.com/8f78e01f5a.js\" crossorigin=\"anonymous\"></script>");
		writer.println("<title>View Tickets</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<div class=\"topnav\">");
		writer.println("<a class=\"active\" href=\"/WebTicketApp/hub.html\">Hub</a>");
		writer.println("<a href=\"/WebTicketApp/help.html\">Help</a>");
		writer.println("<a href=\"/WebTicketApp/ManageUsers\">Users</a>");
		writer.println("<div class=\"dropdown\"><button class=\"dropbtn\">Roles<i class=\"fa fa-caret-down\"></i></button><div class=\"dropdown-content\"><a href=\"#\">Employee</a><a href=\"#\">Technician</a><a href=\"#\">Administrator</a></div></div>");
		writer.println("<div class=\"logout-container\">");
		writer.println("<form action=\"/WebTicketApp/index.html\">");
		writer.println("<button class=\"fill\" type=\"submit\"><i class=\"fas fa-door-open fa-x\"></i> Logout</button>");
		writer.println("</form>");
		writer.println("</div>");
		writer.println("</div>");
		
		writer.println("<br><br><br><center><h1>Managing subtickets for the following ticket: </h1> <b>Ticket ID: </b>" + t.getTicketID() + "<br><b>Ticket Name:</b> " + t.getTicketName() + "<br><br></center>");
		writer.println("<hr style=\"width:100%;text-align:left;margin-left:0\">");
		writer.println("<h1>Create New SubTicket</h1>");
		writer.println("<form method=\"post\" action=\"/WebTicketApp/CreateSubTicket\">\n"
				+ "  <div>\n"
				+ "    <label for=\"ticketName\">SubTicket Name: &nbsp;&nbsp;<span class=\"required\"></span> </label>\n"
				+ "    <input type=\"text\" id=\"ticketName\" name=\"ticketName\" value=\"\" placeholder=\"Ticket Name\" required=\"required\" maxlength=\"120\" />\n"
				+ "  </div>\n"
				+ "  \n"
				+ "  <div class=\"spacer\"></div>\n"
				+ "    \n"
				+ "  <div>\n"
				+ "    <label for=\"ticketDesc\">Ticket Description: <span class=\"required\"></span> </label>\n"
				+ "    <input type=\"text\" id=\"ticketDesc\" name=\"ticketDesc\" value=\"\" placeholder=\"Ticket Description\" required=\"required\" maxlength=\"200\" />\n"
				+ "  </div>\n"
				+ "  \n"
				+ "  <div class=\"spacer\"></div>\n"
				+ "\n"
				+ "  <div>\n "
				+ "<input type=\"hidden\" id=\"parentID\" name=\"parentID\" value=\"" + t.getTicketID() +"\" />"
				+ "    <input type=\"submit\" value=\"Create SubTicket\" id=\"submit\" class=\"button\" />\n"
				+ "  </div>\n"
				+ "</form>");
		
		ArrayList<SubTicket> list = TicketDao.getAllSubTickets(t);
		
		writer.println("<hr style=\"width:100%;text-align:left;margin-left:0\">");		
		writer.println("<br><h1>Existing SubTickets: </h1>");
		writer.print("<table id=\"ticktable\">");
		writer.println("<tr><th>SubTicket ID</th><th>SubTicket Name</th><th>SubTicket Description</th><th>Date Created</th><th>Date Resolved</th><th>Completed?</th><th>Mark as Resolved?</th><th>Delete SubTicket?</th></tr>");
		
		
		for(SubTicket st:list){
			writer.println("<form method=\"post\" action=\"/WebTicketApp/ResolveSubTicket\""
					+"<tr><td>"
					// Hidden fields to grab ticket values
					+"<input type=\"hidden\" id=\"ticketName\" name=\"ticketName\" value=\"" + st.getTicketName() +"\" />"
					+"<input type=\"hidden\" id=\"ticketID\" name=\"ticketID\" value=\"" + st.getTicketID() +"\" />"
					+"<input type=\"hidden\" id=\"ticketDesc\" name=\"ticketDesc\" value=\"" + st.getTicketDesc() +"\" />"
					+"<input type=\"hidden\" id=\"parentID\" name=\"parentID\" value=\"" + st.getParentID() +"\" />"
					+"<input type=\"hidden\" id=\"ticketDateCreated\" name=\"ticketDateCreated\" value=\"" + st.getDateCreated() +"\" />"
					+st.getTicketID()+"</td><td>"
					+st.getTicketName()+"</td><td>"
					+st.getTicketDesc()+"</td><td>"
					+st.getDateCreated()+"</td><td>"
					+st.getDateResolved()+"</td><td>"
					+st.getIsComplete()+"</td>");					
					
					/**
					 * RESOLVE TICKET
					 */
					
					if (st.getIsComplete() != true) {
						writer.println("<form method=\"post\" action=\"/WebTicketApp/ResolveTicket\"" +"<tr><td>"
							+"<input type=\"hidden\" id=\"ticketName\" name=\"ticketName\" value=\"" + st.getTicketName() +"\" />"
							+"<input type=\"hidden\" id=\"ticketID\" name=\"ticketID\" value=\"" + st.getTicketID() +"\" />"
							+"<input type=\"hidden\" id=\"ticketDesc\" name=\"ticketDesc\" value=\"" + st.getTicketDesc() +"\" />"
							+"<input type=\"hidden\" id=\"ticketDateCreated\" name=\"ticketDateCreated\" value=\"" + st.getDateCreated() +"\" />"
							+" <input type=\"submit\" value=\"Resolve Ticket\" id=\"subRes\" class=\"button\" />" );		
					 }
					 else {
						 writer.println("<td> <p>Ticket Resolved</p> </td>");
					 }
					
					 writer.println("<form method=\"post\" action=\"/WebTicketApp/DeleteSubTicket\"" +"<tr><td>"
								+"<input type=\"hidden\" id=\"ticketName\" name=\"ticketName\" value=\"" + st.getTicketName() +"\" />"
								+"<input type=\"hidden\" id=\"ticketID\" name=\"ticketID\" value=\"" + st.getTicketID() +"\" />"
								+"<input type=\"hidden\" id=\"ticketDesc\" name=\"ticketDesc\" value=\"" + st.getTicketDesc() +"\" />"
								+"<input type=\"hidden\" id=\"ticketDateCreated\" name=\"ticketDateCreated\" value=\"" + st.getDateCreated() +"\" />"
								+" <input type=\"submit\" value=\"Delete SubTicket\" id=\"subRes\" class=\"button\" />");		

			writer.println("</tr>");
		}
		writer.println("</table>");
		writer.println("<div class=\"spacerLg\"></div>");
		// writer.println(" <input type=\"submit\" value=\"Resolve Selected Tickets\" id=\"submit\" class=\"button\" />");
		// placeholder for resolve function
		writer.println( "<button type=\"button\">Update Ticket Status</button>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
		
		
	}

}
