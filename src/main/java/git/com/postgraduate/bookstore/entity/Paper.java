package git.com.postgraduate.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name= "paper")
public class Paper {

	private Long id;
	private String name;
	private double price;
	private String description;
	private String author;
	private String publishedOn;
	private String province;
	private String university;
	private String school;
	private String major;
	
	private byte[] image;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column
	public String getPublishedOn() {
		return publishedOn;
	}
	
	public void setPublishedOn(String publishedOn) {
		this.publishedOn = publishedOn;
	}
	
	@Column
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column
	public String getUniversity() {
		return university;
	}
	
	public void setUniversity(String university) {
		this.university = university;
	}
	
	@Column
	public String getSchool() {
		return school;
	}
	
	public void setSchool(String school) {
		this.school = school;
	}	
	
	@Column
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	@Lob
	@Column(name="Image", length=Integer.MAX_VALUE, nullable = true)
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
}
