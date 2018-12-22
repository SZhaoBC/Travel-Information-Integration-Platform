package com.nupal.pdfView;

import java.awt.Color;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nupal.pojo.Customer;

public class PdfView extends AbstractPdfView{
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		List<Customer> personList =(List<Customer>) session.getAttribute("userList");
		
		Font  helvetica_18_blue = new Font(Font.HELVETICA, 18, Font.BOLDITALIC, Color.BLACK);
		Paragraph title = new Paragraph("Customer Information Report 2017", helvetica_18_blue);
		pdfdoc.add(title);
		/*
		Phrase firstPhrase = new Phrase("");
		*/
		PdfPTable table = new PdfPTable(5);
		Format formatter = new SimpleDateFormat("MM-dd-yyyy");
		
		PdfPCell c1 = new PdfPCell(new Phrase("User ID"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("User Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Email"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        
        c1 = new PdfPCell(new Phrase("Gender"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        
        c1 = new PdfPCell(new Phrase("Birthday"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for(Customer cus:personList){
        	table.addCell(Long.toString(cus.getPersonID()));
            table.addCell(cus.getFname()+" "+cus.getLname());
            table.addCell(cus.getEmail());
            if(cus.isGender()){
            	table.addCell("Female");
            }else{
            	table.addCell("Male");
            }
            
            String s = formatter.format(cus.getBirth());
            table.addCell(s);
        }
        
		pdfdoc.add(table);
	}

}
