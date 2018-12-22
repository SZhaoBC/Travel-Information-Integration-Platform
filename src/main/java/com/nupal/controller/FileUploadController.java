package com.nupal.controller;

import java.io.File;
import java.io.IOException;
import java.net.ContentHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.AbstractDocument.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

//import com.me.dao.UserDAO;
//import com.me.upload.Qualifier;
//import com.me.upload.Qualifier;
//import com.me.validator.UserValidator;
import com.nupal.dao.ArticleDAO;
import com.nupal.dao.PersonDAO;
import com.nupal.exception.ArticleException;
import com.nupal.pojo.Article;
import com.nupal.pojo.Customer;
import com.nupal.validator.ArticleValidator;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileUploadController {
	
	@Autowired
	@Qualifier("articleDAO")
	ArticleDAO articleDAO;
	
	@Autowired
	@Qualifier("personDAO")
	PersonDAO personDAO;
	
	//ArticleDAO articleDAO = new ArticleDAO();
	//PersonDAO personDAS = new PersonDAO();
	
	//ArticleValidator articleValidator;
	
	@Autowired
	ServletContext servletContext;
	
	/*
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}*/
	
	@RequestMapping(value = "/upload.htm", method = RequestMethod.GET)
	public String showForm(ModelMap model) {
		Article article = new Article(); // should be AutoWired
		//article.setFavFramework(new String []{"Spring MVC"});

		// command object
		model.addAttribute("article", article);

		// return form view
		return "customer-form";

	}
	
	@RequestMapping(value = "/upload.htm", method = RequestMethod.POST)
	public String handleUpload(@ModelAttribute("article")  Article article, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		String content = request.getParameter("article");
		String authorID =request.getParameter("author_id");
		//articleValidator.validate(article, content);
		/*
		if(content.equals(null)){
			return "publishFail";
		}*/
		
		try {

				File directory;
				String check = File.separator; // Checking if system is linux
												// based or windows based by
												// checking seprator used.
				article.setAuthor((Customer)session.getAttribute("customer"));
				
				String path = null;
				if (check.equalsIgnoreCase("\\")) {
					path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
																				  // so we need to replace build in the path
																						}

				if (check.equalsIgnoreCase("/")) {
					path = servletContext.getRealPath("").replace("build/", "");
					path += "/"; // Adding trailing slash for Mac systems.
				}
				directory = new File("/Users/silu.zhao/Desktop/STS/images");
				boolean temp = directory.exists();
				
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					
					CommonsMultipartFile photoInMemory = article.getPhoto();
					if(photoInMemory.isEmpty())
					{
						System.out.println("photoInMemory is null!!!!");
					}
					String fileName = photoInMemory.getOriginalFilename();
					// could generate file names as well
//					if(fileName == null){
//						System.out.println("null");
//					}
//					System.out.println(fileName);
					File localFile = new File(directory.getPath(), fileName);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					article.setFilename(fileName);
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					
					//article.setAuthor((Customer)session.getAttribute("customer"));
					
					Article a = articleDAO.register(article,customer.getPersonID());

				} else {
					System.out.println("Failed to create directory!");
				}


		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		} //catch (ArticleException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
 catch (ArticleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "publish-success";
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String showForm(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();
		// return form view
		return "home";

	}

}
