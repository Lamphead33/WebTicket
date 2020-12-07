package bean;

public interface MasterTicket {
	public int getTicketID();
	public String getTicketName();
	public String getTicketDesc();
	public String getDateCreated();
	public String getDateResolved();
	public boolean getIsComplete();
	
	public void setTicketID(int ticketID);
	public void setTicketName(String ticketName);
	public void setTicketDesc(String ticketDesc);
	public void setDateCreated(String dateCreated);
	public void setDateResolved(String dateResolved);
	public void setIsComplete(boolean isComplete);
	
}
