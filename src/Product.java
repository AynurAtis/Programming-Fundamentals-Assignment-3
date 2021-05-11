
public class Product{

	private int product_ID;
	private int producer_ID;
	private String productName;
	private String productType;
	
	
	// getter and setter methods
	
	public int getProduct_ID() {
		return product_ID;
	}
	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
	}
	public int getProducer_ID() {
		return producer_ID;
	}
	public void setProducer_ID(int producer_ID) {
		this.producer_ID = producer_ID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	// constructor
	
	public Product(int product_ID, int producer_ID, String productName, String productType) {
		
		this.product_ID = product_ID;
		this.producer_ID = producer_ID;
		this.productName = productName;
		this.productType = productType;
		
	}
	
	

	
}
