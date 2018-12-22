package com.nupal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.nupal.pdfView.PdfView;

@Controller
public class PdfReportController {
	
	@RequestMapping(value = "/report.pdf", method = RequestMethod.GET)
	public ModelAndView showPdfReport()
	{
		View view = new PdfView();
		return new ModelAndView(view);
	}
	
}
