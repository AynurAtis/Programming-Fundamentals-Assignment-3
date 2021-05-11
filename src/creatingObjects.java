import java.io.IOException;
import java.util.ArrayList;

public class creatingObjects {
	
	
	public static ArrayList<User> customerObjects() throws IOException { // User object is created as type of Customer
		ArrayList<User> customerList = new ArrayList<User>();
		String[] splitItems = null;
		
		for(String customer: FileIO.readFileUser())
		{
			splitItems = customer.split(",");
			if(splitItems[4].equals("Customer"))
			{
				User customers =new Customer (Integer.parseInt(splitItems[0]), splitItems[1], splitItems[2],
						splitItems[3], splitItems[4]);
				customerList.add(customers);
			}
		}
		
		return customerList;
	}

	public static ArrayList<User> producerObjects() throws IOException { // User object is created as type of Producer
		ArrayList<User> producerList = new ArrayList<User>();
		String[] splitItems = null;
		
		for(String producer: FileIO.readFileUser())
		{
			splitItems = producer.split(",");
			if(splitItems[4].equals("Producer"))
			{
				User producers = new Producer(Integer.parseInt(splitItems[0]), splitItems[1], splitItems[2],
						splitItems[3], splitItems[4]);
				producerList.add(producers);
			}
		}

		return producerList;
	}
	
	public static ArrayList<Complaint> complaintObjects() throws IOException{ // Complaint objects are created
		ArrayList<Complaint> complaintObjectList = new ArrayList<Complaint>();
		String[] splitItems = null;
		
		for(String cmp: FileIO.readFileComplaint())
		{
			splitItems = cmp.split(",");
			Complaint complaints = new Complaint(Integer.parseInt(splitItems[0]), Integer.parseInt(splitItems[1]), Integer.parseInt(splitItems[2]),
					Integer.parseInt(splitItems[3]), splitItems[4], splitItems[5], splitItems[6]);
			complaintObjectList.add(complaints);

		}
		
		return complaintObjectList;
	}
	
	public static ArrayList<Product> productObjects() throws IOException{ // Product objects are created
		ArrayList<Product> productObjectList = new ArrayList<Product>();
		String[] splitItems = null;
		ArrayList<String> productList = FileIO.readFileProduct();
		for(String pr: productList)
		{
			splitItems = pr.split(",");
			Product products = new Product(Integer.parseInt(splitItems[0]),Integer.parseInt(splitItems[1]), splitItems[2], splitItems[3] );
			productObjectList.add(products);
		}

		
		return productObjectList;
	}
}
