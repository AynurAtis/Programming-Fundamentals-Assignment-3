import java.io.IOException;
import java.util.ArrayList;

public class User implements IUser{
	
	private int userID;
	private String userName;
	private String userPassword;
	private String displayName;
	private String userType;
	
	// constructor
	public User(int userID, String userName, String userPassword, String displayName, String userType) {
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.displayName = displayName;
		this.userType = userType;
	}
	
	// getter and setter methods
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
	
	
}
