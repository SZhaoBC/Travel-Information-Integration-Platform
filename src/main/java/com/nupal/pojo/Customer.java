package com.nupal.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="customer")
@PrimaryKeyJoinColumn(name="customerID", referencedColumnName="personID")
public final class Customer extends Person{
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name = "customerID", unique=true, nullable = false)
	//private long customerID;
	
	@ElementCollection
	@JoinTable(name="customer_friends", joinColumns=@JoinColumn(name="customerID"))
	@Column(name="friendID",nullable=false)
	private Set<Long> friendIDList;
	
	@Transient
	private List<Customer> friendList;
	//private ArrayList<Enterprise> enterpriseList;
	//private Set<MessageBox> messageBoxList;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
	private Set<Article> articleList;
	
	public Customer(Role role){
		super(Role.CUSTOMER);
		//friendList = new HashSet<Customer>();
		//messageBoxList = new HashSet<MessageBox>();
		//activityList = new HashSet<Activity>();
		friendIDList = new HashSet<Long>();
		articleList = new HashSet<Article>();
	}
	
	public Customer(){
		
	}

	public Set<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(Set<Article> articleList) {
		this.articleList = articleList;
	}


	
	public Set<Long> getFriendIDList() {
		return friendIDList;
	}

	public void setFriendIDList(Set<Long> friendIDList) {
		this.friendIDList = friendIDList;
	}

	/*
	public long getCustomerID() {
		return customerID;
	}


	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}*/


	
	public List<Customer> getFriendList() {
		return friendList;
	}


	public void setFriendList(List<Customer> friendList) {
		this.friendList = friendList;
	}

/*
	public Set<MessageBox> getMessageBoxList() {
		return messageBoxList;
	}


	public void setMessageBoxList(Set<MessageBox> messageBoxList) {
		this.messageBoxList = messageBoxList;
	}


	public Set<Activity> getActivityList() {
		return activityList;
	}


	public void setActivityList(Set<Activity> activityList) {
		this.activityList = activityList;
	}
*/

	
	
	
	
}
