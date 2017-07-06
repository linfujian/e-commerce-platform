package git.com.postgraduate.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mysql.fabric.FabricCommunicationException;

@Entity
@Table(name="accounts")
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String ROLE_MANAGER = "MANAGER";
	public static final String ROLE_EMPLOYEE= "EMPOLYEE";
	
	private String userName;
	private String password;
	private String passwordConfirm;
	private boolean active;
	private String userRole;
	
	@Id
	@Column(name="user_name",length=20,nullable=false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="passwordconfirm",nullable=false)
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordconfirm) {
		this.passwordConfirm = passwordconfirm;
	}
	
	@Column(name="active",length=1,nullable=false)
	public boolean isActive(){
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Column(name="user_role",length=20,nullable=false)
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "[" + this.userName + "," + this.password + "," + this.userRole + "]";
	}
}
