import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerComplaintApp {
	
	public static void main(String[] args) throws IOException{

		System.out.println("Welcome to Customer Complaint App!!");
		System.out.println("Please select the operation that you want!");
		System.out.println("If you are a member of app, please select 1. Login else please select 2. Sign up...");
		System.out.println("If you want to exit the system, please select 3....");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		switch(option)
		{
		case 1:
			Login.getInformation();
			break;
		case 2:
			if(SignUp.register())
			{
				System.out.println("You are directing to Login Page...");
				Login.getInformation();
			}
			break;
		case 3:
			System.out.println("Good bye...");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Option....");
			break;
		}
		
		
	}

}
