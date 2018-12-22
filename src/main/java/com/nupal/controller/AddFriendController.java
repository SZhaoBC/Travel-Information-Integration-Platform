package com.nupal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nupal.dao.PersonDAO;
@Controller
public class AddFriendController {
	
	@Autowired
	@Qualifier("personDAO")
	PersonDAO personDAO;

	@RequestMapping(value="/addfriends*", method = RequestMethod.GET)
	public ModelAndView findFriends(HttpServletRequest request){
		
		long cusid = Long.parseLong(request.getParameter("cusid"));
		long fid = Long.parseLong(request.getParameter("friendid"));
		personDAO.addFriends(cusid,fid);
		
		return null;
	}

}
