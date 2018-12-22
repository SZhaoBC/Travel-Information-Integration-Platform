package com.nupal.exception;

public class ArticleException extends Exception{
	
	public ArticleException(String message)
	{
		super("PersonException-"+message);
	}
	
	public ArticleException(String message, Throwable cause)
	{
		super("PersonException-"+message,cause);
	}

}
