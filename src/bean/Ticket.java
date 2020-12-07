package bean;
import java.util.ArrayList;

public class Ticket implements MasterTicket {

  private int ticketID;
  private String ticketName;
  private String ticketDesc;
  private String dateCreated;
  private String dateResolved;
  private boolean isComplete;
  ArrayList<String> subTickets = new ArrayList<String>();
  SubTicket subt = new SubTicket();
  
  // Getters
  public int getTicketID() {
    return ticketID;
  }

  public String getTicketName() {
	  return ticketName;
  }
  
  public String getTicketDesc() {
	  return ticketDesc;
  }
  
  public String getDateCreated() {
    return dateCreated;
  }

  public String getDateResolved() {
    return dateResolved;
  }
  
  public boolean getIsComplete() {
	  return isComplete;
  }
  
  // Setters
  public void setTicketID(int ticketID) {
    this.ticketID = ticketID;
  }
  
  public void setTicketName(String ticketName) {
	  this.ticketName = ticketName;
  }
  
  public void setTicketDesc(String ticketDesc) {
	  this.ticketDesc = ticketDesc;
  }

  public void setDateCreated(String dateCreated) {
    this.dateCreated = dateCreated;
  }

  public void setDateResolved(String dateResolved) {
    this.dateResolved = dateResolved;
  }
  
  public void setIsComplete(boolean isComplete) {
	  this.isComplete = isComplete;
  }
   
}