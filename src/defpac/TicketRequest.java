package defpac;

public class TicketRequest {

	private int userID;
	private String requestInfo;
	
	 // Getters
	  public int getUserID() {
	    return userID;
	  }

	  public String getRequestInfo() {
	    return requestInfo;
	  }

	  // Setters
	  public void setUserID(int userID) {
	    this.userID = userID;
	  }

	  public void setUserName(String requestInfo) {
	    this.requestInfo = requestInfo;
	  }
	  
}
