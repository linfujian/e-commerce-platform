package git.com.postgraduate.bookstore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int orderNum;
	
	private CustomerInfo customerInfo;
	private List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();
	
	private int quantity;
	
	private double fareTotal;
	
	private double total;
	
	private String note;
	
	private int orderStatus;
	
	private boolean isEmpty;
	
	private boolean isValidCustomer;
	
	private boolean isPayFlag = false;
	
	private String loginName;
	
	private String expressName;
	private double expressCost;
	
	public CartInfo() {
		
	}
	
	public boolean isPayFlag() {
		return isPayFlag;
	}
	public void setPayFlag(boolean flag) {
		this.isPayFlag = flag;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String userName) {
		this.loginName = userName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
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
	
	
	public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
 
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
	
	public List<CartLineInfo> getCartLines() {
		return this.cartLines;
	}
	public CartLineInfo findLineByCode(long code) {
		for(CartLineInfo line : this.cartLines) {
			if(line.getProductInfo().getCode() == code)
				return line;
		}
		return null;
	}
	
	public void addProduct(ProductInfo productInfo, int quantity) {
		CartLineInfo line = this.findLineByCode(productInfo.getCode());
		
		if(line == null) {
			line = new CartLineInfo();
			line.setQuantity(0);
			line.setProductInfo(productInfo);
			this.cartLines.add(line);
		}
		int newQuantity = line.getQuantity() + quantity;
		if(newQuantity <=0) {
			this.cartLines.remove(line);
		} else {
			line.setQuantity(newQuantity);
		}
	}
	
	public void updateProducts(Long code, int quantity) {
		CartLineInfo line = this.findLineByCode(code);
		
		if(line != null) {
			if(quantity <=0) {
				this.cartLines.remove(line);
			} else {
				line.setQuantity(quantity);
			}
		}
	}
	
	public void removeProduct(ProductInfo productInfo) {
		CartLineInfo line = this.findLineByCode(productInfo.getCode());
		if(line != null)
			this.cartLines.remove(line);
	}
	
	public boolean isEmpty() {
		isEmpty = this.cartLines.isEmpty();
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
    public boolean isValidCustomer() {
    	isValidCustomer = this.customerInfo != null && this.customerInfo.isValid();
        return isValidCustomer;
    }
    public void setValidCustomer(boolean isValidation) {
    	this.isValidCustomer = isValidation;
    }
	
	public int getQuantityTotal(){
		int quantity = 0;
		for(CartLineInfo line : this.cartLines) {
			quantity += line.getQuantity();
		}
		return quantity;
	}
	public void setQuantityTotal(int quantity) {
		this.quantity = quantity;
	}
	
	public double getAmountTotal() {
		double total = 0;
		for(CartLineInfo line : this.cartLines) {
			total += line.getAmount();
		}
		return total;
	}
	public void setAmountTotal(double total) {
		this.total = total;
	}
	
	//get total with fare
	public double getFareTotal() {
		return fareTotal;
	}
	
	public void setFareTotal(double fareTotal) {
		this.fareTotal = fareTotal;
	}
	
	public void updateQuantity(CartInfo cartForm) {
		if(cartForm != null) {
			List<CartLineInfo> lines = cartForm.getCartLines();
			for(CartLineInfo line : lines) {
				this.updateProducts(line.getProductInfo().getCode(), line.getQuantity());
			}
		}
	}
	
	public void setCartInfo(CartInfo cartInfo) {
		
		this.orderNum = cartInfo.orderNum;
		this.cartLines = cartInfo.cartLines;
		this.customerInfo = cartInfo.customerInfo;
		this.quantity = cartInfo.quantity;
		this.total = cartInfo.total;
		this.isEmpty = cartInfo.isEmpty;
		this.isValidCustomer = cartInfo.isValidCustomer;
	}
}
