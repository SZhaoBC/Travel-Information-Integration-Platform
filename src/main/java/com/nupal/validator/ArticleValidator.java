package com.nupal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nupal.pojo.Article;

public class ArticleValidator implements Validator{
	public boolean supports(Class aClass) {
		return aClass.equals(Article.class);
	}

	public void validate(Object obj, Errors errors) {
		Article user = (Article) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "error.invalid.article", "Content Required");
/*
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email","Email Required");
*/		
		// check if user exists
		
	}
}
