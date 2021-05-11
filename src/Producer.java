import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;


public class Producer extends User implements IProducer{
	
	
	public Producer(int producerID, String producerName, String userPassword, String displayName, String userType) {
		super(producerID, producerName, userPassword, displayName, userType);
	
	}
	
	public static void welcome() {
		String displayName = Login.getDisplayName();
		System.out.println("Welcome " + displayName);
	}

	public static void getProducerPage() throws IOException{

		System.out.println("To add a product, please select 1 or to see previous complaints, please select 2: ");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		switch(option) {
		case 1:
			addProduct();
			break;
		case 2:
			seeComplaints();
			break;
		default:
			System.out.println("Invalid selection...");
		}
	}
	
	public static void addProduct() throws IOException{
		int producerID = Login.getUserID();
		ArrayList<String> productList = FileIO.readFileProduct(); 
		Set<String> productTypeL = new LinkedHashSet<String>();
		ArrayList<String> productTypeList = new ArrayList<String>();
		String[] splitItems = null;
		Scanner scan = new Scanner(System.in);
		int i = 0;
		String productType = "";

		if(productList.size()!=0) {
			for(String prd: productList) // list the all product types
			{
				splitItems = prd.split(","); 
				productTypeL.add(splitItems[3]);
			}
			for(String pdl: productTypeL)
			{
				productTypeList.add(pdl);  // LinkedHashSet elements added to arraylist named productTypeList
			}
			for(String pdl: productTypeList)
			{
				i+=1;
				System.out.println(i + "=> " + productTypeList.get(i-1));
			}
			
			System.out.println("Please select and enter the product type or add a new type please select 0: ");
			
			int productTypeOption = scan.nextInt();
			
			if(productTypeOption >= 0 && productTypeOption<= i)
			{
				if(productTypeOption == 0) // if the product type does not exist in the product type list
				{
					System.out.println("Enter the product type name with no blank: ");
					productType = scan.next();
				}
				else  // if the product type exists in the product type list, select one of them
				{
					productType = productTypeList.get(productTypeOption - 1);
				}
				System.out.println("Enter the product name with no blank: ");
				String productName = scan.next();
				FileIO.writeFileProduct(producerID, productName, productType);
				System.out.println("Product is added. You are directing to main menu...");
			}
			else
			{
				System.out.println("Invalid option...");
			}
			getProducerPage();
		}
		else  // if any product does not exist in the product list
		{
			System.out.println("Please enter the product type with no blank: ");
			productType = scan.next();
			System.out.println("Enter the product name with no blank");
			String productName = scan.next();
			FileIO.writeFileProduct(producerID, productName, productType);
			System.out.println("Product is added. You are directing to main menu...");
			getProducerPage();
		}
	}
	
	
	public static void seeComplaints() throws IOException{
		Scanner scan = new Scanner(System.in);
		int producerID = Login.getUserID(); // producer ID gets after the login process
		int notDeletedStatus = 0;
		ArrayList<Complaint> complaintList = creatingObjects.complaintObjects(); // Complaint object list
		String[] splitItems = null;
		int i = 0;
		ArrayList<User> customerObjects = creatingObjects.customerObjects(); // customer object list
		ArrayList<Product> productObjects = creatingObjects.productObjects(); // product object list
		String custumerName = "";
		String productName = "";
		if(complaintList.size()==0)
		{
			System.out.println("There is no any complaint...");
			getProducerPage();
		}
		// block_1 list the all complaints that is status is not deleted
		for(Complaint cmp : complaintList)
		{
			if(cmp.getProducerID() == producerID) {
				for (int j = 0; j<customerObjects.size(); j++)
				{
					if(cmp.getCustomerID() == customerObjects.get(j).getUserID())
					{
						custumerName = customerObjects.get(j).getUserName();
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
				if(!cmp.getStatus().equalsIgnoreCase("Deleted"))
				{
					System.out.println(i + "=> " + cmp.getComplaintID() + " " + custumerName + " " + productName 
					+ " " + cmp.getComplaintTitle() + " " + cmp.getStatus());
					notDeletedStatus +=1;
				}
				else {
					i-=1;
				}
			}
		}
		// end of the block_1
		
		if(notDeletedStatus > 0) { // if there is at least one complaint that is status is not deleted, this block runs
		System.out.println("Select the complaint number to see it: ");
		int option = scan.nextInt();
		if(option > 0 && option <= i)
		{
			System.out.println(complaintList.get(option-1).getComplaintTitle() + " " + complaintList.get(option-1).getComplaint());
			complaintList.get(option-1).setStatus("Seen"); // Chosen complaint's status is changed as Seen automatically

			FileIO.writeUpdatedStatustoFile(complaintList.get(option-1), complaintList.get(option-1)); // status is updated from complaint.csv file
		}
		System.out.println("To change the status of the complaint as Working or Fixed, please select -1 or to back to main menu, please select -2: ");
		int changeoption = scan.nextInt(); // if producer want to change the status of chosen complaint
		switch(changeoption) {
		case -1:
			System.out.println("Enter the status as Working or Fixed: ");
			String status = scan.next();
			while(true)
			{
				if(status.equalsIgnoreCase("Working") || status.equalsIgnoreCase("Fixed"))
				{
					break;
				}
				else {
					System.out.println("There is not status such that Please enter the status correctly or to back main menu please select 0: ");
					int eOption =  scan.nextInt();
					switch(eOption)
					{
					case 0:
						getProducerPage();
						break;
					}
					status = scan.next();
				}
			}
			complaintList.get(option-1).setStatus(status); // status is updated
			FileIO.writeUpdatedStatustoFile(complaintList.get(option-1), complaintList.get(option-1)); // status is updated from compliant.csv file

			break;
		case -2:
			getProducerPage();
			break;
		default:
			System.out.println("Wrong option...");
		}
		}
		else {
			System.out.println("There is no any complaint...");
			System.out.println("To back to the main menu please enter the 1 or, To exit the system please enter the any number:");
			int sysOption = scan.nextInt();
			if(sysOption == 1)
			{
				getProducerPage();
				
			}
			else
				
				System.out.println("Good bye...");
				System.exit(0);
			}
	}
	

}
