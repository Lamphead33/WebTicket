package defpac;

public class LogWorker implements Runnable {
	private Listener listener;
	private final int id;
	private final String name;

	public void setListener(Listener listener) {
        this.listener = listener;
    }
	
	public LogWorker(int id, String name) {
		this.id = id;
		this.name = name;
	}
    
	
	
	@Override
	public void run() {
		try {
			System.out.println("\n--- \nProcessing Ticket - ID: " + id + ", Name: " + name + "\n--- \n");
			listener.workComplete();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
