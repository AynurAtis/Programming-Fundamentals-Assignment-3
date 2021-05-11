import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Iterator;


public class FileIO implements IFileIO{
	
	static Random rnd = new Random();
	static int Low = 1;
	static int High = 10000;

	
	
	// read the user.csv file as a string and store these lines an arrayList named userList and return userList
	public static ArrayList<String> readFileUser() throws IOException{ 
		String fileName = "user.csv";
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		ArrayList<String> userList = new ArrayList<String>(); 
		String line = null;
		line = reader.readLine(); // read the first line
		while(line!=null)
		{

			userList.add(line); // if first line is not null add to userList 
			line = reader.readLine(); // read second line , third line ... until the line is null
		}
		reader.close();
		return userList; // userList arraylist includes all users for all lines as a string
	}
	
	// write to the user.csv file after sign up process 
	public static void writeFileUser(String userName, String Password, String displayName, String userType) throws IOException{
		String[] lines; 
		boolean flag = true;
		int userID = rnd.nextInt(High - Low) + Low; // create a random userID
		for(String user: readFileUser()) // To check to exist userID in user.csv file, it looks in the array that includes user informations 
		{
			lines = user.split(",");
			while(true)
			{
				if(lines[0].equals(String.valueOf(userID))) // if the userID already exists, it creates new one
				{
					flag = false;
				}
				else {
					flag = true;
					break;
				}
			}
		}
		if(flag)
		{
			// writes to the user.csv file
			String fileName = "user.csv";
			BufferedWriter writer =new BufferedWriter(new FileWriter(fileName, true));
			writer.write(String.valueOf(userID) + "," + userName + "," + Password + "," + displayName + "," + userType + "\n");
			writer.flush();
			System.out.println("Registeration is successfull!");
	 		writer.close();
		}
	
	}
	
	// read the product.csv file as a string and store these lines an arrayList named productList and return productList
	public static ArrayList<String> readFileProduct() throws IOException{
		
		String fileName = "product.csv";
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		ArrayList<String> productList = new ArrayList<String>();
		String line = null;
		line = reader.readLine();	// read the first line
		while(line!=null)
		{	
			productList.add(line); // if first line is not null add to userList 
			line = reader.readLine();  // read second line , third line ... until the line is null
		}	

		reader.close();
		return productList; // productList arraylist includes all products for all lines as a string
	}
	
	// write to the product.csv file after producer's adding product process
	public static void writeFileProduct(int producerID, String productName, String productType) throws IOException{
		String[] lines; 
		boolean flag = true;
		int productID = rnd.nextInt(High - Low) + Low; // create a random productID
		for(String user: readFileProduct()) // To check to exist productID in product.csv file, it looks in the array that includes product informations 
		{
			lines = user.split(",");
			while(true)
			{
				if(lines[0].equals(String.valueOf(productID))) // if the productID already exists, it creates new one
				{
					flag = false;
				}
				else {
					flag = true;
					break;
				}
			}
			if(flag)
			{
				break;
			}
		}
		if(flag)
		{
			// writes to the product.csv file
			String fileName = "product.csv";
			BufferedWriter writer =new BufferedWriter(new FileWriter(fileName, true));
			writer.write(String.valueOf(productID) + "," + String.valueOf(producerID) + "," + productName + "," + productType + "\n");
			writer.flush();
			System.out.println("Product registration is successful.");
	 		writer.close();
		}
	
	}
	
	// read the compliant.csv file as a string and store these lines an arrayList named complaintList and return complaintList
	public static ArrayList<String> readFileComplaint() throws IOException{
		
		String fileName = "complaint.csv";
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		ArrayList<String> complaintList = new ArrayList<String>();
		String line = null;
		line = reader.readLine();
		while(line!=null)
		{
			complaintList.add(line);
			line = reader.readLine();
		}
		reader.close();
		
		
		return complaintList;
	}
	// write to the product.csv file after producer's adding product process
	public static void writeFileComplaint(int customerID, int producerID, int productID, String complaintTitle, String complaint, String status) throws IOException{
		String[] lines; 
		boolean flag = true;
		int complaintID = rnd.nextInt(High - Low) + Low;  // create a random complaintID
		for(String user: readFileComplaint())
		{
			lines = user.split(",");
			while(true)
			{
				if(lines[0].equals(String.valueOf(complaintID)))
				{
					flag = false;
				}
				else {
					flag = true;
					break;
				}
			}
			if(flag)
			{
				break;
			}
		}
		if(flag)
		{
			String fileName = "complaint.csv";
			BufferedWriter writer =new BufferedWriter(new FileWriter(fileName, true));
			writer.write(String.valueOf(complaintID) + "," + String.valueOf(customerID) + "," + String.valueOf(producerID) + "," + String.valueOf(productID) 
			+ ", " + complaintTitle + "," + complaint + "," + status + "\n");
			writer.flush();
			System.out.println("Complaint sending is successful.");
	 		writer.close();
		}
	
	}
	
	// after the complaint status this method is using to write to complaint.csv file 
	public static boolean writeUpdatedStatustoFile(Complaint complaintID, Complaint status) throws IOException
	{
		String fileName = "complaint.csv";
		String updatedLine ; // updatedLine is the line that after for the status is updated
		boolean result = false;
		Scanner allFile = new Scanner(new File(fileName));
		String line;
		ArrayList<String> allFileArrayList = new ArrayList<String>(); // allFileArrayList includes all lines in the complaint.csv file
		while(allFile.hasNextLine())
		{
			line = allFile.nextLine();
			allFileArrayList.add(line);
			
		}
		Iterator<String> iter = allFileArrayList.iterator();
		iter.next();
		int lineNumber = 0;
		
		// block_1 do that if first line will update, that line is stored in the lineInformation, then after the line it is stored updatedLine
		// and changes that line in the arrayList
		String[] lineInformations = allFileArrayList.get(0).split(",");
		if(lineInformations[0].equals(String.valueOf(complaintID.getComplaintID())))
		{
			updatedLine = lineInformations[0] + "," + lineInformations[1] + "," + lineInformations[2] + "," + 
					lineInformations[3] + "," + lineInformations[4] + "," + lineInformations[5] + "," +
					status.getStatus();
			allFileArrayList.set(lineNumber, updatedLine);
		}
		else
		{
			lineNumber+=1;
		}
		// end o the block_1--------------------
		// block_2 do same operations for the next lines
		while(iter.hasNext())
		{
			lineInformations = iter.next().split(",");
			if(lineInformations[0].equals(String.valueOf(complaintID.getComplaintID())))
			{
				updatedLine = lineInformations[0] + "," + lineInformations[1] + "," + lineInformations[2] + "," + 
						lineInformations[3] + "," + lineInformations[4] + "," + lineInformations[5] + "," +
						status.getStatus();
				allFileArrayList.set(lineNumber, updatedLine);
			}
			else
			{
				lineNumber+=1;
			}

		}
		// end of the block_2----------------
		allFile.close();
		
		PrintWriter updatedFile = new PrintWriter(new FileWriter(fileName));
		for(String updatedFileLine: allFileArrayList)
		{
			updatedFile.write(updatedFileLine+ "\n"); // this line writes the complaint.csv for the updatedLine
		}
		result = true;
		updatedFile.close();
		return result;
	}
}
