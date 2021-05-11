import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login implements ILogin{

	private static int userID;
	private static String userName;
	private static String userPassword;
	private static String displayName;
	private static String userType;
	
	public static int getInformation() throws IOException{
		ArrayList<String> userList = FileIO.readFileUser(); // userList arrayList includes the all users 
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the username: ");
		userName = scan.next();
		System.out.println("Please enter the password: ");
		userPassword = scan.next();
		String[] splitItems = null;
		boolean flagUserInfo = false;
		String userType = "";
		String uID = "";
		if(userList.size()!=0)
		{
			while(true)
			{
				
				for (String userN :userList) 
				{
					splitItems = userN.split(",");
					if(userName.equals(splitItems[1]) && userPassword.equals(splitItems[2])) // if userName and password are exist the login process is successful
					{
						displayName = splitItems[3];
						userType = splitItems[4];
						uID = splitItems[0];
						flagUserInfo = true;
						break;
					}
					else
					{
						flagUserInfo = false;
					}
				}
				if(flagUserInfo)
				{
					break;
				}
				else
				{
					System.out.println("UserName or Password is wrong...");
					System.out.println("To enter the correct information, please select 1 or to Signup, please select 2: ");
					int option = scan.nextInt();
					switch(option) {
					case 1:
						System.out.println("Please enter the user name: ");
						userName = scan.next();
						System.out.println("Please enter the user password: ");
						userPassword = scan.next();
						break;
					case 2:
						SignUp.register();
						break;
					}
				}
				}
		
		}	
		userID = Integer.parseInt(uID);
		
		if(flagUserInfo) // if the login process is successful the user is directing the page that is according to userType
		{
			if(userType.equalsIgnoreCase("Producer"))
			{
				Producer.getProducerPage();
			}
			else if (userType.equalsIgnoreCase("Admin"))
			{
				Admin.getAdminPage();
			}
			else {
				Customer.getCustomerPage();
			}
		}
		
		
		return userID;
	}

	
	
	// getter and setter methods
	public static int getUserID() {
		return userID;
	}



	public static String getDisplayName() {
		return displayName;
	}



	public static void setDisplayName(String displayName) {
		Login.displayName = displayName;
	}



	public static void setUserID(int userID) {
		Login.userID = userID;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	
	
	
	
}
