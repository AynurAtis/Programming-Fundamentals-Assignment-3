
public class Complaint {
	
	private int complaintID;
	private int customerID;
	private int producerID;
	private int productID;
	private String complaintTitle;
	private String complaint;
	private String status;
	
	
	// constructor
	public Complaint(int complaintID, int customerID, int producerID, int productID, String complaintTitle, String complaint, String status) {
		this.complaintID = complaintID;
		this.customerID = customerID;
		this.producerID = producerID;
		this.productID = productID;
		this.complaintTitle = complaintTitle;
		this.complaint = complaint;
		this.status = status;
		
	}


	
	// getter and setter methods
	public int getComplaintID() {
		return complaintID;
	}


	public void setComplaintID(int complaintID) {
		this.complaintID = complaintID;
	}


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public int getProducerID() {
		return producerID;
	}


	public void setProducerID(int producerID) {
		this.producerID = producerID;
	}


	public int getProductID() {
		return productID;
	}


	public void setProductID(int productID) {
		this.productID = productID;
	}


	public String getComplaintTitle() {
		return complaintTitle;
	}


	public void setComplaintTitle(String complaintTitle) {
		this.complaintTitle = complaintTitle;
	}


	public String getComplaint() {
		return complaint;
	}


	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	

	

	
	
}
