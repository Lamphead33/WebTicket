package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Ticket;
import dao.TicketDao;

/**
 * Servlet implementation class DeleteTicket
 */
@WebServlet("/DeleteTicket")
public class DeleteTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		TicketDao ticketDao = new TicketDao();
		ticketDao.deleteTicket(t);
		String page = getHtmlString(req.getServletContext().getRealPath("/hub.html"));
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
