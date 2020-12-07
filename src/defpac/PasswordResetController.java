package defpac;

import dao.*;
import java.sql.SQLException;

public class PasswordResetController {
	String username;
	String answer;
	
	public PasswordResetController(String username, String answer) {
		this.username = username;
		this.answer = answer;
	}
	
	public boolean reset() throws SQLException {
		// Compare a string
		ApplicationDao ad = new ApplicationDao();
		if (!ad.resetCheck(username, answer)) {
			// if username & answer don't match
			this.resetDenied();
			return false;
		}
		else {
			// if match, return true
			return true;
		}
	}

	
	public String resetDenied() {
		// consider making an array of errors
		String errMsg = "Authenticaion failed. Please check username and answer.";
		return errMsg;
	}
}
