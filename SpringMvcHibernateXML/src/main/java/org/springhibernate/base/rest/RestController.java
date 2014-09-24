package org.springhibernate.base.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springhibernate.base.model.Adres;
import org.springhibernate.base.service.DBServiceInterface;


@Controller
@RequestMapping("/adresRaadplegen")
public class RestController {
	

	@Autowired
	private DBServiceInterface<Adres> service;
	
	static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	
	// Method to get the desired adres object from a given id. 	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Adres getAdres(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response) {		
		Adres adres = null;
		//try {
			// check if id is available in database otherwise give message
			logger.info("before get() call from service");			
			int int_id = 0; 
			boolean invalidInput = false;
			try{
				int_id = Integer.parseInt(id);
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null,"You have entered an invalid ID. ID should be numerical.","Invalid Input",JOptionPane.ERROR_MESSAGE);
				invalidInput = true;
			}
			
			adres = service.get(int_id);
			logger.info("Got adres object from id: {}",id);
			if (adres == null && !invalidInput){
				logger.info("Adres is null");
				try {
					JOptionPane.showMessageDialog(null,"This ID does not correspond to any address. Please enter another ID.","No Address",JOptionPane.WARNING_MESSAGE);
					//response.sendRedirect("home.jsp");
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		return adres;
	}	
}




