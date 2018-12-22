package com.nupal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nupal.pojo.Person;

public class PersonValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Person.class);
	}

	public void validate(Object obj, Errors errors) {
		Person person = (Person) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname", "error.invalid.person", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname", "error.invalid.person", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.person", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email","Email Required");
		
		// check if user exists
		
	}
}
