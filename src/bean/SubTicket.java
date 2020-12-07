package bean;

public class SubTicket implements MasterTicket {
	
	private int ticketID;
	private String ticketName;
	private String ticketDesc;
	private String dateCreated;
	private String dateResolved;
	private boolean isComplete;

	@Override
	public int getTicketID() {
		return ticketID;
	}

	@Override
	public String getTicketName() {
		return ticketName;
	}

	@Override
	public String getTicketDesc() {
		return ticketDesc;
	}

	@Override
	public String getDateCreated() {
		return dateCreated;
	}

	@Override
	public String getDateResolved() {
		return dateResolved;
	}

	@Override
	public boolean getIsComplete() {
		return isComplete;
	}

	@Override
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	@Override
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
		
	}

	@Override
	public void setTicketDesc(String ticketDesc) {
		this.ticketDesc = ticketDesc;
		
	}

	@Override
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
		
	}

	@Override
	public void setDateResolved(String dateResolved) {
		this.dateResolved = dateResolved;
		
	}

	@Override
	public void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
		
	}

}
