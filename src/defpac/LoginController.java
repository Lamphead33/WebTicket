package defpac;

import dao.*;
import java.sql.SQLException;

public class LoginController {
	String username;
	String password;
	
	public LoginController(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public boolean login() throws SQLException {
		// Compare pass string
		ApplicationDao ad = new ApplicationDao();
		if (!ad.loginCheck(username, password)) {
			// if username & pass don't match
			this.loginDenied();
			return false;
		}
		else {
			// if match, return true
			return true;
		}
	}
	
	public void logout() {
		// display message
		// navigate to login page
		
		
	}
	
	
	public String loginDenied() {
		// consider making an array of errors
		String errMsg = "Login failed. Please check username and password.";
		return errMsg;
	}

	public String logoutSuccessful() {
		String logMsg = "Logged out successfully";
		return logMsg;
	}
	
	public String resetDenied() {
		// consider making an array of errors
		String errMsg = "Authenticaion failed. Please check username and answer.";
		return errMsg;
	}
}
