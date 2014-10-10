package nl.kadaster.web.controller;

import nl.kadaster.business.BusinessService;
import nl.kadaster.web.domain.Adres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
//@RequestMapping("/")
public class SiteController {

	private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);
	
	@Autowired(required=true)
	private BusinessService businessService;

	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}

	// {!begin inject}
	@Autowired
	private Adres adres;
  // {!end inject}

  // {!begin method}
//	@RequestMapping(method = RequestMethod.GET)
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getAdresLijst(Model model) {
		LOG.debug("Kadaster to home view");
		model.addAttribute("adressen",getAdressen());
		return "/home";
	}
  // {!end method}
			
	private List<Adres> getAdressen() {
		LOG.trace("calling businessService.getAdressen()");
		return businessService.getAdressen();
	}

  // {!begin model}
	@ModelAttribute("adres")
	private Adres getAdres() {
		return adres;
	}
  // {!end model}
}
