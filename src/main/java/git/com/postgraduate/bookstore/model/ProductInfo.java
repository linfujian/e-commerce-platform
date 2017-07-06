package git.com.postgraduate.bookstore.model;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import git.com.postgraduate.bookstore.entity.Paper;

public class ProductInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long code;
	private String name;
	private double price;
	
	private CommonsMultipartFile fieData;
	
	public ProductInfo() {
		
	}
	
	public ProductInfo(Paper paper) {
		this.code = paper.getId();
		this.name = paper.getName();
		this.price = paper.getPrice();
	}
	
	public ProductInfo(Long code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
		
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public CommonsMultipartFile getFileData() {
		return fieData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fieData = fileData;
	}
	
}
