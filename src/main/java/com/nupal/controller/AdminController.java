package com.nupal.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nupal.dao.PersonDAO;
import com.nupal.exception.PersonException;
import com.nupal.pojo.Admin;
import com.nupal.pojo.Customer;
import com.nupal.pojo.Person;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("personDAO")
	PersonDAO personDAO;
	
	@RequestMapping(value = "/login-admin.htm", method = RequestMethod.GET)
	public ModelAndView adminPage(HttpServletRequest request) {
		//HttpSession session = request.getSession();
		
		ModelAndView model = new ModelAndView();
		
		try{
			List<Customer> personList = personDAO.listUser();
			//Person p = personDAO.registerAdmin();
			model.setViewName("admin-login");
			model.addObject("userList",personList);
			
			//session.setAttribute("userList", personList);
			return model;
		}catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/login-admin.htm", method = RequestMethod.POST)
	public ModelAndView ShowForm(HttpServletRequest request){
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView();
		
		String userName = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = null;      
        try {
        	admin = personDAO.findAdmin(userName, password);
        	session.setAttribute("user", admin);
        	List<Customer> personList = personDAO.listUser();
			model.setViewName("admin-form");
			model.addObject("userList",personList);
			
			session.setAttribute("userList", personList);
			
			return model;
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(admin != null){
        	return new ModelAndView("admin-login");
        }else{
        	return new ModelAndView("error");
        }
	}
	
	@RequestMapping(value = "/admin-deleteUser*", method = RequestMethod.GET)
	public void deleteUser(HttpServletRequest request){
		long uid = Integer.parseInt(request.getParameter("cusid"));
		
		try {
			List<Customer> personList = personDAO.listUser();
			
			for(Customer per:personList){
				if(per.getPersonID() == uid){
					personDAO.delete(per);
				}
			}
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@RequestMapping(value = "/admin-search*", method = RequestMethod.GET)
	@ResponseBody
	public String searchUser(HttpServletRequest request){
		
		List<Customer> customers = new ArrayList<Customer>();
		String keywords = request.getParameter("keywords"); 
		String option = request.getParameter("option");
		try {
			List<Customer> personList = personDAO.listUser();
			//Iterator<Customer> itr = personList.iterator();
			boolean g=true;
			if(option.equals("gender")){
				
				if(keywords.equalsIgnoreCase("male")){
					for(Customer per:personList){
							if(!per.isGender())
								customers.add(per);
					}
					//g=false;
				}else if(keywords.equalsIgnoreCase("female")){
					for(Customer per:personList){
							if(per.isGender())
								customers.add(per);
					}
					//g=false;
				}else{
					for(Customer per:personList)
							customers.add(per);
				}
				
				/*
				for(Customer per:personList){
					if(g){
						if(per.isGender())
						customers.add(per);
					}else{
						if(!per.isGender())
							customers.add(per);
					}
				}*/
			}
			
			if(option.equals("email")){
				for(Customer per:personList){
					if(per.getEmail().equalsIgnoreCase(keywords)){
						customers.add(per);
					}
				}
				
				if(keywords.isEmpty()){
					for(Customer per:personList){
							customers.add(per);
					}
				}
			}
			
			String rsp = "<table>"+
	                     "<tr>"+
	      "<td>User Id</td>"+
	      "<td>User Name</td>"+
	      "<td>Email</td>"+
	      "<td>Gender</td>"+
	      "<td>Birthday</td>"+
	      "</tr>";
			
			for(Customer cus:customers){
				rsp = rsp + "<tr><td>"+ cus.getPersonID()+"</td>"+
							"<td>"+cus.getFname()+" "+cus.getLname()+"</td>"+
							"<td>"+cus.getEmail()+"</td>"+
							"<td>"+cus.isGender()+"</td>"+
							"<td>"+cus.getBirth()+"</td>"+
							"</tr>";	
			}
			rsp = rsp+"</table>";
			
			return rsp;
			
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/adlogout.htm", method = RequestMethod.GET)
	public String showForm(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();
		// return form view
		return "admin-login";

	}
}
