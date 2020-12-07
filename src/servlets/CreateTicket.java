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


@WebServlet("/CreateTicket")
public class CreateTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// grab params from Ticket Creation page
		String ticketName = req.getParameter("ticketName");
		String ticketDesc = req.getParameter("ticketDesc");
		String ticketDateCreated = req.getParameter("dateCreated");
						
		Ticket t = new Ticket();
		t.setTicketName(ticketName);
		t.setTicketDesc(ticketDesc);
		t.setDateCreated(ticketDateCreated);
		t.setIsComplete(false);
	
				
		TicketDao ticektDao = new TicketDao();
		ticektDao.createTicket(t);
		
		// User brought to Hub after creation
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
