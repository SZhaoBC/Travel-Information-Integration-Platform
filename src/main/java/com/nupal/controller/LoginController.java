package com.nupal.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nupal.dao.PersonDAO;
import com.nupal.exception.PersonException;
import com.nupal.pojo.Article;
import com.nupal.pojo.Customer;
import com.nupal.pojo.Person;

@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("personDAO")
	PersonDAO personDao;
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView loginMethod(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        ModelAndView mv = new ModelAndView();
        
        //PersonDAO personDao = new PersonDAO();
        Customer customer = null;
        Article article = null;
        try {
			customer = personDao.findCustomer(email, password);
			if(customer ==null){
				return new ModelAndView("home");
			}
			article = new Article();
			List<Customer> friendList = new ArrayList<Customer>();
			
			Date date = new Date();
			String str = new SimpleDateFormat("dd-MM-yy:HH:mm:SS").format(date);
			
			friendList = personDao.listFriends(customer.getFriendIDList());
			customer.setFriendList(friendList);
			mv.addObject("customer", customer);
			mv.addObject("article",article);
			mv.addObject("flist",friendList);
			mv.addObject("date",str);
			mv.setViewName("customer-form");
			
			session.setAttribute("customer", customer);
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(customer != null){
        	return mv;
        }else{
        	return new ModelAndView("error");
        }
	}
}
