import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User implements ICustomer {

	public Customer(int userID, String userName, String userPassword, String displayName, String userType) {
		super(userID, userName, userPassword, displayName, userType);
	}

	public static void welcome() {
		String displayName = Login.getDisplayName();
		System.out.println("Welcome " + displayName);
	}

	
	public static void getCustomerPage() throws IOException
	{
		
		System.out.println("To add a complaint, please select 1 or to see previous complaints, please select 2 or to finish program, please select 3: ");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		switch(option) {
		case 1:
			addComplaint();
			break;
		case 2:
			seeComplaints();
			break;
		case 3:
			System.exit(0);
		default:
			System.out.println("Invalid selection...");
		}
	}
	
	public static void addComplaint() throws IOException{
		int i =0;
		int customerID = Login.getUserID();
		Scanner scan = new Scanner(System.in);
		ArrayList<User> producerObjectList = creatingObjects.producerObjects();
		ArrayList<Product> productObjectList = creatingObjects.productObjects();
		for(User prd: producerObjectList) // List the producers
		{
			i+=1;
			System.out.println(i + "=> " + prd.getUserName());
			
		}
		System.out.println("Please to select producer, select one of the options: ");
		int option = scan.nextInt();
		if(option > 0 && option <=i)
		{
			String productName = "";
			String productType = "";
			int productID = 0;
			int producerID = producerObjectList.get(option-1).getUserID();
			int k = 0;
			for(int j=0; j<productObjectList.size(); j++) // list the product type and product name of chosen producer
			{
				if(producerID == productObjectList.get(j).getProducer_ID())
				{
					k+=1;
					System.out.println(k + "=> " + productObjectList.get(j).getProductType() + " " + productObjectList.get(j).getProductName());
				}
			}
			System.out.println("To select product type and product name please enter an option: ");
			int pOption = scan.nextInt();
			if(pOption > 0 && pOption <=k)
			{
				System.out.println("Please enter the Complaint Title with no blank: ");
				String complaintTitle = scan.next();
				System.out.println("Please enter the complaint with no blank: ");
				String complaint = scan.next();
				productID = productObjectList.get(pOption-1).getProduct_ID(); 
				productName = productObjectList.get(pOption-1).getProductName();
				productType = productObjectList.get(pOption-1).getProductType();
				
				// adding the the complaint to complaint.csv file
				FileIO.writeFileComplaint(customerID, producerID, productID, complaintTitle, complaint, "New"); 
				System.out.println("You are directing to main menu...");
				getCustomerPage();
				
			}
		}
	}
	
	public static void seeComplaints() throws IOException{
		Scanner scan = new Scanner(System.in);
		int customerID = Login.getUserID();
		ArrayList<Complaint> complaintList = creatingObjects.complaintObjects();
		String[] splitItems = null;
		int i = 0; // i is the option number
		ArrayList<User> producerObjects = creatingObjects.producerObjects();
		ArrayList<Product> productObjects = creatingObjects.productObjects();
		String producerName = "";
		String productName = "";
		int notDeletedStatus = 0;
		// list the all complaints that the status is not deleted
		for(Complaint cmp : complaintList)
		{
			for (int j = 0; j<producerObjects.size(); j++)
			{
				if(cmp.getProducerID() == producerObjects.get(j).getUserID())
				{
					producerName = producerObjects.get(j).getUserName();
				}
			}
			for (int j = 0; j<productObjects.size(); j++)
			{
				if(cmp.getProductID() == productObjects.get(j).getProduct_ID())
				{
					productName = productObjects.get(j).getProductName();
				}
				
			}
			i+=1;
			// if the status is not deleted and the complaints are belongs to the that customer
			if(!cmp.getStatus().equalsIgnoreCase("Deleted") && (customerID==cmp.getCustomerID())) 
			{
				System.out.println(i + "=> " + cmp.getComplaintID() + " " + producerName + " " + productName 
						+ " " + cmp.getComplaintTitle() + " " + cmp.getStatus());
				notDeletedStatus +=1;
			}
			else {
				i-=1;
				
			}
		}
		if(notDeletedStatus > 0) {
		System.out.println("Select the complaint number to see it: ");
		int option = scan.nextInt();
		if(option > 0 && option <= i)
		{
			System.out.println(complaintList.get(option-1).getComplaintTitle() + " " + complaintList.get(option-1).getComplaint());

		}
		System.out.println("To delete the complaint, please select -1 or, to back to main menu, please select -2: ");
		System.out.println("Note: You can not delete the complaint if the status of it is Fixed!!!");
		int doption = scan.nextInt();
		switch(doption) {
		case -1:
			String status = complaintList.get(option-1).getStatus();
			if(status.equalsIgnoreCase("Fixed"))
			{
				System.out.println("It can not be deleted");
			}
			else
			{

				complaintList.get(option-1).setStatus("Deleted");
				FileIO.writeUpdatedStatustoFile(complaintList.get(option-1), complaintList.get(option-1));
				System.out.println("Your complaint is deleted...");
			}
			break;
		case -2:
			getCustomerPage();
			break;
		default:
			System.out.println("Wrong option...");
		}
		}
		else {
			System.out.println("There is no any complaint that belongs to you...");
			System.out.println("To back to main menu, please select 1, to exit the system, please select any number: ");
			int sOption = scan.nextInt();
			if(sOption==1)
			{
				getCustomerPage();
				
			}
			else
			{
				System.out.println("Good bye...");
				System.exit(0);
			}
		}
		
	}
			
}
