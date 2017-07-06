package git.com.postgraduate.bookstore.model;

import java.util.Date;
import java.util.List;

public class OrderInfo {

	private String id;
	private Date orderDate;
	private int orderNum;
	private double amount;
	private String orderNote;
	private boolean payFlag;
	private int orderStatus; // 0--未发货；1--已发货；2--已签收
	private int transactionNum;
	private Date payTime;
	private int expressNum;
	private String expressName;
	private double expressCost;
	
	
	private String customerName;
	private String customerAddress;
	private String customerAddressDetail;
	private String customerEmail;
	private String customerPhone;
	
	private List<OrderDetailInfo> details;
	
	public OrderInfo() {
		
	}
	
    public OrderInfo(String id, Date orderDate, int orderNum,
            double amount, String orderNote, boolean payFlag, int orderStatus, 
            String customerName, String customerAddress, String customerAddressDetail,
            String customerEmail, String customerPhone, int transactionNum,
            Date payTime, int expressNum, String expressName, double expressCost) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderNum = orderNum;
        this.amount = amount;
        this.orderNote = orderNote;
        this.payFlag = payFlag;
        this.orderStatus = orderStatus;
        this.transactionNum = transactionNum;
        this.payTime = payTime;
        this.expressNum = expressNum;
        this.expressName = expressName;
        this.expressCost = expressCost;
        
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerAddressDetail = customerAddressDetail;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }
    
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public Date getOrderDate() {
        return orderDate;
    }
 
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
 
    public int getOrderNum() {
        return orderNum;
    }
 
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getOrderNote() {
        return orderNote;
    }
 
    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }
    
    public boolean isPayFlag() {
        return payFlag;
    }
 
    public void setPayFlag(boolean payFlag) {
        this.payFlag = payFlag;
    }
    
    public int getOrderStatus() {
        return orderStatus;
    }
 
    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
    
	public int getTransactionNum() {
	    return transactionNum;
	}
	 
    public void setTransactionNum(int transactionNum) {
        this.transactionNum = transactionNum;
    }
	
    public Date getPayTime() {
        return payTime;
    }
 
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
    public int getExpressNum() {
	    return expressNum;
	}
	 
    public void setExpressNum(int expressNum) {
        this.expressNum = expressNum;
    }
    
    public double getExpressCost() {
        return expressCost;
    }
 
    public void setExpressCost(double expressCost) {
        this.expressCost = expressCost;
    }
    
    public String getExpressName() {
        return expressName;
    }
 
    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }
    
    public String getCustomerAddressDetail() {
        return customerAddressDetail;
    }
 
    public void setCustomerAddressDetail(String customerAddressDetail) {
        this.customerAddressDetail = customerAddressDetail;
    }
    
    public String getCustomerName() {
        return customerName;
    }
 
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
 
    public String getCustomerAddress() {
        return customerAddress;
    }
 
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
 
    public String getCustomerEmail() {
        return customerEmail;
    }
 
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
 
    public String getCustomerPhone() {
        return customerPhone;
    }
 
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public List<OrderDetailInfo> getDetails() {
        return details;
    }
 
    public void setDetails(List<OrderDetailInfo> details) {
        this.details = details;
    }
}
