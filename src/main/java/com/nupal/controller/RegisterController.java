package com.nupal.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nupal.dao.PersonDAO;
import com.nupal.pojo.Article;
import com.nupal.pojo.Customer;
import com.nupal.pojo.Person.Role;
import com.nupal.validator.PersonValidator;

@Controller
public class RegisterController {

	@Autowired
	@Qualifier("personDAO")
	PersonDAO personDAO;

	@RequestMapping(value = "/add.htm", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		
		Customer customer=null;
		try {
		// Person Property 
		String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        
        String birthday_year = request.getParameter("birthday_year");
        String birthday_month = request.getParameter("birthday_month");
        String birthday_day = request.getParameter("birthday_day");
        
        String birth = birthday_month + "-" + birthday_day +"-" + birthday_year;
        DateFormat format = new SimpleDateFormat("MM-dd-yyyy"); 
        
        if(email.isEmpty()||password.isEmpty()||birth.isEmpty()){
			return new ModelAndView("home");
		}
        
        Date birthday = format.parse(birth);
       
        //save
		//PersonDAO personDao = new PersonDAO();
		Article article = new Article();
		customer = new Customer(Role.CUSTOMER);
		customer.setFname(fname);
		customer.setLname(lname);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setBirth(birthday);
		
		session.setAttribute("customer", customer);
		
		mv.addObject("customer", customer);
		mv.addObject("article",article);
		mv.setViewName("customer-form");
		
		if(gender.equals("female"))
			customer.setGender(true);
		else
			customer.setGender(false);
		
		Customer c = personDAO.findCustomer(email, password); 
		if(c != null){
			//personDAO.register(customer);
			return new ModelAndView("home");	
		}
		
		personDAO.register(customer);
		}catch (ParseException e) {    
	           e.printStackTrace();    
		}catch(Exception e){
			System.out.println("*** IllegalStateException: ");
		}
		//return new ModelAndView("home");
		return mv;
	}
}
