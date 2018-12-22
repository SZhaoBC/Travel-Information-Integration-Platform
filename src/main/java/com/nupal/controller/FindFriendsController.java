package com.nupal.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nupal.dao.PersonDAO;
import com.nupal.exception.PersonException;
import com.nupal.pojo.Customer;
import com.nupal.pojo.Person;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FindFriendsController {
	
	@Autowired
	@Qualifier("personDAO")
	PersonDAO personDAO;
	
	@RequestMapping(value="/findfriends.htm",method = RequestMethod.GET)
	public ModelAndView findFriends(HttpServletRequest request){
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		long cusID = customer.getPersonID();
		
		String requirement = request.getParameter("friendName");
		if(requirement.isEmpty()){
			return new ModelAndView("find-friends-result","friendList",null);
		}
		String[] names = requirement.split("\\s+"); 
		
		List<Person> friendList = null;
		try {
			friendList = personDAO.getFriends(names,cusID);
			Set<Long> myFriends = customer.getFriendIDList();
			myFriends.add(cusID);
			//System.out.println("FRIEND SIZE"+friendList.size());
			 for (Iterator<Person> it = friendList.iterator(); it.hasNext();) {
			        if(myFriends.contains(it.next().getPersonID())){
			        	it.remove();
			        }
			    }
			 
			return new ModelAndView("find-friends-result","friendList",friendList);
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
