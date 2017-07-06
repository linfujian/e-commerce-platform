package git.com.postgraduate.bookstore.model;

import java.io.Serializable;

public class CartLineInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductInfo productInfo;
	private int quantity;
	private double amount;
	
	public CartLineInfo() {
		this.quantity = 0;
	}
	
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() { //���ﳵ��ÿ�������¼���ܼ۸�
		amount = this.productInfo.getPrice() * quantity;
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
