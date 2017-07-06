package git.com.postgraduate.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_details")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Order order;
	
	private Paper paper;
	private int quantity;
	private double price;
	private double amount;
	
	@Id
	@Column(name="id",length=50,nullable=false)
	public String getId(){
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id",nullable=false,foreignKey= @ForeignKey(name="ORDER_DETAIL_ORD_FK"))
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id",nullable=false,foreignKey=@ForeignKey(name="ORDER_DETAIL_PROD_FK"))
	public Paper getPaper(){
		return paper;
	}
	public void setPaper(Paper paper){
		this.paper =paper;
	}
	
	@Column(name="quantity",nullable=false)
	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity =quantity;
	}
	
	@Column(name="price",nullable=false)
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price =price;
	}
	
	@Column(name="amount",nullable=false)
	public double getAmount(){
		return amount;
	}
	public void setAmount(double amount){
		this.amount =amount;
	}
	
	
	
}
