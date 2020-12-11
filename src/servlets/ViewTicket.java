package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Ticket;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.TicketDao;
import defpac.Listener;
import defpac.LogWorker;

/**
 * Servlet implementation class ViewTicket
 */
@WebServlet("/ViewTicket")
public class ViewTicket extends HttpServlet {
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
		writer.println("<h1>View Existing Tickets:</h1>");		
		ArrayList<Ticket> list = TicketDao.getAllTickets();
		// log with thread
		logTickets(list);
		writer.print("<table id=\"ticktable\">");
		writer.println("<tr><th>Ticket ID</th><th>Ticket Name</th><th>Ticket Description</th><th>Date Created</th><th>Date Resolved</th><th>Completed?</th><th>Manage SubTickets</th><th>Mark as Resolved?</th><th>Delete Ticket?</th></tr>");
		for(Ticket t:list){
			writer.println("<form method=\"post\" action=\"/WebTicketApp/ManageSubTickets\""
					+"<tr><td>"
					// Hidden fields to grab ticket values
					+"<input type=\"hidden\" id=\"ticketName\" name=\"ticketName\" value=\"" + t.getTicketName() +"\" />"
					+"<input type=\"hidden\" id=\"ticketID\" name=\"ticketID\" value=\"" + t.getTicketID() +"\" />"
					+"<input type=\"hidden\" id=\"ticketDesc\" name=\"ticketDesc\" value=\"" + t.getTicketDesc() +"\" />"
					+"<input type=\"hidden\" id=\"ticketDateCreated\" name=\"ticketDateCreated\" value=\"" + t.getDateCreated() +"\" />"
					+t.getTicketID()+"</td><td>"
					+t.getTicketName()+"</td><td>"
					+t.getTicketDesc()+"</td><td>"
					+t.getDateCreated()+"</td><td>"
					+t.getDateResolved()+"</td><td>"
					+t.getIsComplete()+"</td><td>"
					+" <input type=\"submit\" value=\"Manage Subtickets\" id=\"subSub\" class=\"button\" />"
					+"</form>" );
					
					/**
					 * RESOLVE TICKET
					 */
					 if (t.getIsComplete() != true) {
						 writer.println("<form method=\"post\" action=\"/WebTicketApp/ResolveTicket\"" +"<tr><td>"
							+"<input type=\"hidden\" id=\"ticketName\" name=\"ticketName\" value=\"" + t.getTicketName() +"\" />"
							+"<input type=\"hidden\" id=\"ticketID\" name=\"ticketID\" value=\"" + t.getTicketID() +"\" />"
							+"<input type=\"hidden\" id=\"ticketDesc\" name=\"ticketDesc\" value=\"" + t.getTicketDesc() +"\" />"
							+"<input type=\"hidden\" id=\"ticketDateCreated\" name=\"ticketDateCreated\" value=\"" + t.getDateCreated() +"\" />"
							+" <input type=\"submit\" value=\"Resolve Ticket\" id=\"subRes\" class=\"button\" />" );		
					 }
					 else {
						 writer.println("<td> <p>Ticket Resolved</p> </td>");
					 }
					
					/**
					 * DELETE TICKET
					 */
					writer.println("</form>" 
					+"<form method=\"post\" action=\"/WebTicketApp/DeleteTicket\"" +"<tr><td>"
					+"<input type=\"hidden\" id=\"ticketName\" name=\"ticketName\" value=\"" + t.getTicketName() +"\" />"
					+"<input type=\"hidden\" id=\"ticketID\" name=\"ticketID\" value=\"" + t.getTicketID() +"\" />"
					+"<input type=\"hidden\" id=\"ticketDesc\" name=\"ticketDesc\" value=\"" + t.getTicketDesc() +"\" />"
					+"<input type=\"hidden\" id=\"ticketDateCreated\" name=\"ticketDateCreated\" value=\"" + t.getDateCreated() +"\" />"
					+" <input type=\"submit\" value=\"Delete Ticket\" id=\"subDel\" class=\"button\" />"
					+"</form>" );

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

	// Log Tickets function for LogWorker
	public void logTickets(ArrayList<Ticket> list) {
		Executor executor = Executors.newFixedThreadPool(5);
		        for(Ticket t:list) {
		            LogWorker worker = new LogWorker(t.getTicketID(), t.getTicketName());
		            
		            // Attach listener to worker
		            worker.setListener(new Listener() {
		                @Override
		                public void workComplete() {
		                    System.out.println("Successfully processed ticket " + t.getTicketID() + ": " + t.getTicketName());
		                }
		            });
		            
		            executor.execute(worker);
		        }
		        ((ExecutorService) executor).shutdown();
		        while(!((ExecutorService) executor).isTerminated()) {}
		        
		        System.out.println("\n --- Processing complete! --- ");  
		    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

