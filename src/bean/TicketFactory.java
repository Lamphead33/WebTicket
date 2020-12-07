package bean;

public class TicketFactory {
	public MasterTicket getTicket(String ticketType) {
		if (ticketType == null) {
			return null;
		}
		
		if (ticketType.equalsIgnoreCase("TICKET")) {
			return new Ticket();
		} else if (ticketType.equalsIgnoreCase("SUBTICKET")) {
			return new SubTicket();
		}
		
		return null;
	}
}
