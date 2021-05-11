import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SignUp implements ISignUp{
	
	private static String userName;
	private static String userPassword;
	private static String confirmPassword;
	private static String displayName;
	private static String userType;
	
	
	public static boolean register() throws IOException {
		ArrayList<String> userList = FileIO.readFileUser();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the username: ");
		userName = scan.next();
		boolean successRegister = false;
		boolean flagUserName = true;
		boolean flagPassword = true;
		String[] splitItems = null;
		if(userList.size()!=0) {
			for (String userN : userList)
			{
				splitItems = userN.split(",");
				while (true)
				{
					if(userName.equals(splitItems[1])) // if the user name that is entered exists the user.csv file, enter the new one
					{
						flagUserName = false;
						System.out.println("This user name already exists!!!");
						System.out.println("Please enter the username: ");
						userName = scan.next();
					}
					else
					{
						flagUserName = true;
						break;
					}
						
				}
				
			}
		}
		if(flagUserName) {
			System.out.println("Please enter the userPassword: ");
			userPassword = scan.next();
			System.out.println("Please enter the confirm Password: ");
			confirmPassword = scan.next();
			while(true)
			{
				if(!(userPassword.equals(confirmPassword))) // confirm password
				{
					flagPassword = false;
					System.out.println("Passwords are not equal!!!");
					System.out.println("Please enter the userPassword");
					userPassword = scan.next();
					System.out.println("Please enter the confirm Password");
					confirmPassword = scan.next();
				}
				else
				{
					flagPassword = true;
					break;
				}
				
			}
			if(flagPassword) 
			{
				System.out.println("Please enter your name: ");
				String name = scan.next();
				System.out.println("Please enter your surname: ");
				String surname = scan.next();
				displayName = name + " " + surname;
				System.out.println("Please enter the user type: Producer or Customer ");
				userType = scan.next();
				boolean flag = false;
				while(true) // user type must be producer or customer
				{
					if((userType.equalsIgnoreCase("Producer")) || (userType.equalsIgnoreCase("Customer"))) {
						flag = true;
						break;
							
					}
					else
					{
						flag=false;
						System.out.println("Please enter the user type as Producer or Customer ");
						userType = scan.next();
					}
				}
				if(flag) {
					successRegister = true;
					FileIO.writeFileUser(userName, userPassword, displayName, userType); // write the user information to user.csv file
				}
			}
		}
		
		return successRegister;
	}

}
