package git.com.postgraduate.bookstore.model;

public class OrderDetailInfo {

	private String id;
	private Long productCode;
	private String productName;
	
	private int quantity;
	private double price;
	private double amount;
	
	public OrderDetailInfo() {
		
	}
	
	public OrderDetailInfo(String id, Long productCode, 
			String productName, int quantity, double price, double amount) {
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Long getProductCode() {
		return productCode;
	} 
	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
