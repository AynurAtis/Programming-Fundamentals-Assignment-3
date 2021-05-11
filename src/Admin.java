import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User implements IAdmin {

	public Admin(int userID, String userName, String userPassword, String displayName, String userType) {
		super(userID, userName, userPassword, displayName, userType);
	}


	public static void welcome() {
		String displayName = Login.getDisplayName();
		System.out.println("Welcome " + displayName);
	}

	
	public static void getAdminPage() throws IOException
	{
		
		Scanner scan = new Scanner(System.in);
		ArrayList<Complaint> complaintList = creatingObjects.complaintObjects();
		String[] splitItems = null;
		int i = 0;
		ArrayList<User> customerObjects = creatingObjects.customerObjects();
		ArrayList<User> producerObjects = creatingObjects.producerObjects();
		ArrayList<Product> productObjects = creatingObjects.productObjects();
		String producerName = "";
		String productName = "";
		String customerName = "";
		
		// block_1 is the list the all complaints automatically
		for(Complaint cmp : complaintList)
		{
			for(int j=0; j<customerObjects.size(); j++)
			{
				if(cmp.getCustomerID() == customerObjects.get(j).getUserID())
				{
					customerName = customerObjects.get(j).getUserName();
				}
			}
			for(int j=0; j<producerObjects.size(); j++)
			{
				if(cmp.getProducerID() == producerObjects.get(j).getUserID())
				{
					producerName = producerObjects.get(j).getUserName();
				}
			}
			for(int j=0; j<productObjects.size(); j++)
			{
				if(cmp.getProductID() == productObjects.get(j).getProduct_ID())
				{
					productName = productObjects.get(j).getProductName();
				}
			}
			
			i+=1;
			System.out.println(i + "=> " + cmp.getComplaintID() + " " + customerName + " " + producerName + " " + productName 
					+ " " + cmp.getComplaintTitle() + " " + cmp.getStatus());
		}
		// end of the  block_1 --------------
		
		System.out.println("If want to see any complaints detail select one: ");
		int option = scan.nextInt();
		System.out.println(complaintList.get(option-1).getComplaintTitle() + " " + complaintList.get(option-1).getComplaint());
		System.out.println("If want to change the complaint status, please enter 1 or, to back main menu select 0: ");
		int soption = scan.nextInt();
		switch(soption)
		{
		case 1:
			System.out.println("status = ");
			String status = scan.next();
			complaintList.get(option-1).setStatus(status); // change the status
			FileIO.writeUpdatedStatustoFile(complaintList.get(option-1), complaintList.get(option-1)); // write to the complaint.csv for updated status
			break;
		case 0:
			getAdminPage();
			break;
		default:
			System.out.println("Invalid option...");
			break;
		}
		System.out.println("To Exit the system please enter -1:");
		int exit = scan.nextInt();
		if(exit==-1)
		{
			System.out.println("Good bye....");
			System.exit(0);
		}
		else
			getAdminPage();
	}
}
