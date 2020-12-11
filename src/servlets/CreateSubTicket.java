package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SubTicket;
import bean.Ticket;
import dao.TicketDao;

/**
 * Servlet implementation class CreateSubTicket
 */
@WebServlet("/CreateSubTicket")
public class CreateSubTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSubTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		// TODO Auto-generated method stub
		String ticketName = req.getParameter("ticketName");
		String ticketDesc = req.getParameter("ticketDesc");
		String ticketDateCreated = req.getParameter("dateCreated");
		int parentID = Integer.parseInt(req.getParameter("parentID"));
						
		SubTicket t = new SubTicket();
		t.setTicketName(ticketName);
		t.setTicketDesc(ticketDesc);
		t.setParentID(parentID);
		t.setDateCreated(ticketDateCreated);
		t.setIsComplete(false);
	
				
		TicketDao ticektDao = new TicketDao();
		ticektDao.createSubTicket(t);
		
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
