package bean;

public class User {

  private int userID;
  private String userName;
  private String password;
  private String answer;
  //Setting to int to handle 0 or 1 in SQL
  private int isEmployee;
  private int isTech;
  private int isAdmin;

  // Getters
  public int getUserID() {
    return userID;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }
  
  public int getIsEmployee() {
	  return isEmployee;
  }
  
  public int getIsTech() {
	  return isTech;
  }
  
  public int getIsAdmin() {
	  return isAdmin;
  }
  

  // Setters
  public void setUserID(int newUserID) {
    this.userID = newUserID;
  }

  public void setUserName(String newUserName) {
    this.userName = newUserName;
  }

  public void setPassword(String newPassword) {
    this.password = newPassword;
  }
  
  public void setIsEmployee(int isEmployee) {
	  this.isEmployee = isEmployee;
  }
  
  public void setIsTech(int isTech) {
	  this.isTech= isTech;
  }
  
  
  public void setIsAdmin(int isAdmin) {
	  this.isAdmin = isAdmin;
  }

public String getAnswer() {
	return answer;
}

public void setAnswer(String newAnswer) {
	this.answer = newAnswer;
}

}