package com.nupal.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import javax.persistence.Column;

@Entity
@Table(name="admin")
@PrimaryKeyJoinColumn(name="employeeID", referencedColumnName="personID")
public final class Admin extends Person{
	
	@Column(name="userName")
	private String userName;
	
	public Admin(Role role){
		super(Role.ADMIN);
	}
	
	public Admin(){
		
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
