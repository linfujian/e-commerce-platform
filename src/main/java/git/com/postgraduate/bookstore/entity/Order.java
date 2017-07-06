package git.com.postgraduate.bookstore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="orders",uniqueConstraints={@UniqueConstraint(columnNames = "order_num")})
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date orderDate;
	private int orderNum;
	private String orderNote;
	private double amount;
	private boolean isPayFlag;
	private int orderStatus;
	private String loginName;
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
	
	@Id
	@Column(name="id",length=50)
	public String getId(){
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="order_date",nullable=false)
	public Date getorderDate(){
		return orderDate;
	}
	public void setOrderDate(Date date) {
		this.orderDate = date;
	}
	
	@Column(name="order_num")
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	@Column(name="order_note")
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	
	@Column(name="order_status")
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Column(name="amount")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name="pay_flag")
	public boolean isPayFlag() {
		return isPayFlag;
	}
	public void setPayFlag(boolean isPayFlag) {
		this.isPayFlag = isPayFlag;
	}
	
	@Column(name="user_name",length=20,nullable=false)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Column(name="transaction_num")
	public int getTransactionNum() {
	    return transactionNum;
	}
    public void setTransactionNum(int transactionNum) {
        this.transactionNum = transactionNum;
    }
	
    @Column(name="pay_time")
    public Date getPayTime() {
        return payTime;
    }
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
    @Column(name="express_num")
    public int getExpressNum() {
	    return expressNum;
	}
    public void setExpressNum(int expressNum) {
        this.expressNum = expressNum;
    }
    
    @Column(name="express_cost")
    public double getExpressCost() {
        return expressCost;
    }
    public void setExpressCost(double expressCost) {
        this.expressCost = expressCost;
    }
    
    @Column(name="express_name")
    public String getExpressName() {
        return expressName;
    }
    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }
	
	@Column(name="customer_name",length=255,nullable=false)
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Column(name="customer_address",length=255,nullable=false)
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	@Column(name="customer_addressdetail",length=255,nullable=false)
	public String getCustomerAddressDetail() {
		return customerAddressDetail;
	}
	public void setCustomerAddressDetail(String customerAddressDetail) {
		this.customerAddressDetail = customerAddressDetail;
	}
	
	@Column(name="customer_email",length=128,nullable=false)
	public String getCustomerEmail(){
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	@Column(name="customer_phone",length=128,nullable=false)
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String phone) {
		this.customerPhone = phone;
	}
	
	
}
