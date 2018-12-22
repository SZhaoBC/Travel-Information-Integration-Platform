package com.nupal.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.nupal.exception.PersonException;
import com.nupal.pojo.Admin;
import com.nupal.pojo.Customer;
import com.nupal.pojo.Person;
import com.nupal.pojo.Person.Role;


public class PersonDAO extends DAO{
	public PersonDAO() {
	}

	 
	public List<Person> get(String key,String flag) throws PersonException {
		try {
			begin();
			String query="";
			if (flag.equalsIgnoreCase("first")) {
				query = "from User where fname= :fname";
            } else if (flag.equalsIgnoreCase("last")) {
            	query = "from User where lname= :fname";
            } else if (flag.equalsIgnoreCase("email")) {
            	query = "from User where email= :fname";
            }
			Query q1 = getSession().createQuery(query);
			q1.setString("fname", key);		
			List<Person> person =q1.list();
			commit();
			return person;
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get person with " + key, e);
		}
	}
	
	//GET aLL USERS
	public List<Customer> listUser() throws PersonException{
		try {
			begin();
			String query="from Customer";
			Query q1 = getSession().createQuery(query);	
			List<Customer> person =q1.list();
			commit();
			return person;
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get person with ", e);
		}
	}
	
	//get Friend list through name 
	public List<Person> getFriends(String[] key,long cusID) throws PersonException {
		try {
			/*
			begin();
			String query="";
			if (flag.equalsIgnoreCase("first")) {
				query = "from User where fname= :fname";
            } else if (flag.equalsIgnoreCase("last")) {
            	query = "from User where lname= :fname";
            } else if (flag.equalsIgnoreCase("email")) {
            	query = "from User where email= :fname";
            }
			Query q1 = getSession().createQuery(query);
			q1.setString("fname", key+"%");		
			List<Person> person =q1.list();
			commit();
			return person;*/
			begin();
			Criteria crit = getSession().createCriteria(Customer.class);
			Disjunction disjunction = Restrictions.disjunction();
			System.out.println("FRIEND SIZE"+key.length);
			for(int i=0; i<key.length;i++){
				Criterion fname = Restrictions.ilike("fname", key[i]+"%");
				Criterion lname = Restrictions.ilike("lname", key[i]+"%");
				//Criterion fname = Restrictions.eq("fname", key[i]);
				//Criterion lname = Restrictions.eq("lname", key[i]);
				disjunction.add(fname);
				disjunction.add(lname);
			}
			crit.add(disjunction);
			//crit.add(Restrictions.ne("personID",cusID));
			//crit.add(Restrictions.ne("lname",cusLname));
			List<Person> results=crit.list();
			System.out.println("FRIEND SIZE"+results.size());
			commit();
			return results;
		} 
		catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get person with " + key, e);
		}
	}
	
	//List All Friends
	public List<Customer> listFriends(Set<Long> fids) throws PersonException{
		
		try{
			begin();
			
			Iterator<Long> it = fids.iterator();
			List<Customer> results = new ArrayList<Customer>();
			
			//Disjunction disjunction = Restrictions.disjunction();
			for(;it.hasNext();){
				Criteria crit = getSession().createCriteria(Customer.class);
				crit.add(Restrictions.eq("personID", it.next()));
				Customer p=(Customer)crit.uniqueResult();
				results.add(p);
			}
			//crit.add(disjunction);
			//crit.add(Restrictions.ne("personID",cusID));
			//crit.add(Restrictions.ne("lname",cusLname));
			//List<Person> results=crit.list();
			commit();
			return results;
		} 
		catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get person", e);
		}	
	}
	
	//Search a customer
	public void addFriends(long cusid,long fid){
		begin();
		String query = "from Customer where customerID= :fname";
		Query q1 = getSession().createQuery(query);
		q1.setLong("fname", cusid);
		List<Customer> person =q1.list();
		Customer c = person.get(0);
		c.getFriendIDList().add(fid);
		commit();
	}
	
	//Customer Login 
	public Customer findCustomer(String email, String password) throws PersonException{
		try {
			begin();
			
            String query = "from Customer where email= :email and password = :password";
            
			Query q1 = getSession().createQuery(query);
			q1.setString("email", email);	
			q1.setString("password", password);
			List<Customer> cusList =q1.list();
			commit();			
			if(cusList != null && !cusList.isEmpty()){
				return cusList.get(0);
			}
			return null;
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get person with " + email, e);
		}
	}
	
	//Find Admin through usrname and password
	public Admin findAdmin(String adminName, String password) throws PersonException{
		try {
			begin();
			
            String query = "from Admin where userName= :userName and password = :password";
            
			Query q1 = getSession().createQuery(query);
			q1.setString("userName", adminName);	
			q1.setString("password", password);
			List<Admin> adminList =q1.list();
			commit();			
			if(adminList != null && !adminList.isEmpty()){
				
				return adminList.get(0);
			}
			return null;
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get person with " + adminName, e);
		}
	}
	
	//Register Customer for customer
	public Person register(Person u)
			throws PersonException {
		try {
			begin();
			System.out.println("inside DAO");			
			getSession().save(u);
			commit();
			return u;

		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Exception while creating person: " + e.getMessage());
		}
	}

	//Register Admin
	public Person registerAdmin() throws PersonException{
		try {
			begin();
			Admin a = new Admin(Role.ADMIN);
			a.setPersonID(1);
			a.setPassword("admin");
			a.setUserName("admin");
			getSession().save(a);
			commit();
			return a;

		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Exception while creating person: " + e.getMessage());
		}
	}
	
	//Delete Customer for admin
	public void delete(Customer person) throws PersonException {
		try {
			begin();
			
			String query2 = "delete from Article where author= :author";
			Query q2 = getSession().createQuery(query2);
			q2.setEntity("author", person);	
			int c2 =q2.executeUpdate();
			//List<Customer> articles =q2.list();
			commit();
			
			begin();
			String query1 = "delete from Customer where customerID= :customerID";
			Query q1 = getSession().createQuery(query1);
			q1.setLong("customerID", person.getPersonID());	
			int c1 =q1.executeUpdate();
			commit();
			
	        //getSession().delete(cusList.get(0));
	       // getSession().delete(articles.get(0));
			getSession().delete(person);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not delete person " + person.getFname(), e);
		}
	}
	
	
}
