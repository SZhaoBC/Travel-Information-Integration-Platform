package com.nupal.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.InheritanceType;

@Entity
@Table(name="person")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person {
	
	public enum Role {
		ADMIN, CUSTOMER, ENTERPRISE
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "personID", unique=true, nullable = false)
	private long personID;
	@Column(name = "fname")
	private String fname;
	@Column(name = "lname")
	private String lname;
	@Column(name = "gender")
	private boolean gender;  // false male; 1 female
	@Column(name = "birth")
	private Date birth;
	
	@Column(name = "role")
	@Enumerated(EnumType.ORDINAL)
	private Role role;
	
	@Column(name = "email")
	private String email;
	@Column(name = "cellphone")
	private String cellPhone;	
	@Column(name = "password")
	private String password;
	
	public Person(){
		
	}
	
	public Person(Role role){
		this.role = role;
	}
	
	public long getPersonID() {
		return personID;
	}


	public void setPersonID(long personID) {
		this.personID = personID;
	}

	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
