package org.springhibernate.base;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springhibernate.base.model.Adres;
import org.springhibernate.base.service.DBServiceInterface;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	static Logger Logger = LoggerFactory.getLogger(HomeController.class);
	
	
	//An implementation of DBServiceInterface is injected automatically by Spring
	@Autowired
	private DBServiceInterface<Adres> dbService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	// Laat een lijst zien van alle adressen
	@RequestMapping(value = "/")
	public ModelAndView home() {			
		List<Adres> listAdres = dbService.listItems();
		Logger.info("List of adresses is obtained from database");
		ModelAndView model = new ModelAndView("home"); // home.jsp is de view
		model.addObject("adresList", listAdres ); // stuur listAdres object mee
		
		return model;
	}
	
	// voeg een nieuw adres toe mbv een formulier
    @RequestMapping(value = "/nieuwAdres", method = RequestMethod.GET)
    public ModelAndView newAdres() {
        ModelAndView model = new ModelAndView("adresInput"); // adresInput.jsp is de view
        model.addObject("adres", new Adres()); // stuur leeg adres object mee die wordt opgevuld        
        return model;      
    }
	
    // zodra je op opslaan klikt, wordt de data opgeslagen naar een database en keert terug naar beginscherm
	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public ModelAndView addAddress(@ModelAttribute Adres adres) { // binds the data from request to adres
	    // @ModelAttribute("adres")
		//System.out.println("just before saveOrUpdateAdres(): ID is " + adres.getId());
		//System.out.println("just before saveOrUpdateAdres(): huisNummer is " + adres.getHuisNummer());
		if (adres != null){
			dbService.saveOrUpdateItem(adres); 			
			Logger.info("Adres: {} {} added to database",adres.getStraatNaam(),adres.getHuisNummer());
		}
		return new ModelAndView("redirect:/");
	}	
	

    @RequestMapping(value = "/aanpassen", method = RequestMethod.GET)
    public ModelAndView editAdres(HttpServletRequest request) {
        int adresId = Integer.parseInt(request.getParameter("id"));
        Adres adres = dbService.get(adresId);
        ModelAndView model = new ModelAndView("adresEdit");
        model.addObject("adres", adres);
        
        System.out.println("after fetching adres in edit: ID is " + adres.getId());
        return model;      
    }
 
    @RequestMapping(value = "/verwijderen", method = RequestMethod.GET)
    public ModelAndView deleteAdres(HttpServletRequest request) {
        int adresId = Integer.parseInt(request.getParameter("id"));
        dbService.delete(adresId);
        return new ModelAndView("redirect:/");     
    }
	
	
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model) {
	    String greetings = "Greetings, Spring MVC!";
	    model.addAttribute("message", greetings);
	 
	    return "test";
	}
	
	
	
	private String checkAdres(Adres adres, String errorMessage){
		
		if (!adres.getPostcode().matches("[0-9]{4}[a-zA-Z]{2}")){
			
			errorMessage = "Onjuiste postcode! vb. 1000AA";
			System.out.println("Onjuiste postcode!");
			return errorMessage;		
		}
		
		return null;		
		
	}
	
	
	
	
}
