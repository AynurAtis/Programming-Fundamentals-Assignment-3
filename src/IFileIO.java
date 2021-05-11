import java.util.ArrayList;

public interface IFileIO {

	public static ArrayList<String> readFileUser(){ // read user.csv file
		return null;
		
	}
	
	public static void writeFileUser(String userName, String Password, String displayName, String userType) { // write to user.csv file
		
	}
	
	public static ArrayList<String> readFileProduct(){ // read product.csv file 
		return null;
		
	}
	
	public static void writeFileProduct(int producerID, String productName, String productType) { // write to product.csv file
		
	}
	
	public static ArrayList<String> readFileComplaint(){ // read complaint.csv file
		return null;
		
	}
	
	// write to complaint.csv file
	public static void writeFileComplaint(int customerID, int producerID, int productID, String complaintTitle, String complaint, String status) {
		
	}
	
	public static boolean writeUpdatedStatustoFile(Complaint complaintID, Complaint status) { // write to complaint file to update status
		return false;
		
	}
}
