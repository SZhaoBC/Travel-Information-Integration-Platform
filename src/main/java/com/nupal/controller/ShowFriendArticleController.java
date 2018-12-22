package com.nupal.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nupal.dao.ArticleDAO;
import com.nupal.dao.PersonDAO;
import com.nupal.exception.PersonException;
import com.nupal.pojo.Article;
import com.nupal.pojo.Customer;

@Controller
public class ShowFriendArticleController {
	

	@Autowired
	@Qualifier("articleDAO")
	ArticleDAO articleDAO;
	
	@Autowired
	@Qualifier("personDAO")
	PersonDAO personDAO;
	
	@RequestMapping(value = "/friendsArticle*", method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		long cusId = Long.parseLong(request.getParameter("fid"));
		Set<Long> fids = new HashSet<Long>();
		fids.add( cusId);
		try {
			List<Customer> friend = personDAO.listFriends(fids);
			mv.addObject("friend",friend.get(0));
			
			
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("friendsArticle");
		return mv;

	}

}
