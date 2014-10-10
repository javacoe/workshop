package nl.kadaster.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.kadaster.business.BusinessService;
import nl.kadaster.web.domain.Adres;

@Controller
public class AdresDatabaseCommandController {

    private static final Logger LOG = LoggerFactory.getLogger(AdresDatabaseCommandController.class);

    @Autowired
    private BusinessService businessService;

    @RequestMapping(value = "/verwijderAdres" , method = RequestMethod.POST)
    public String remove(@ModelAttribute("adres") Adres adres) {
        LOG.debug("Verwijder {} van de database table Adres", adres.getId());
        try {
			businessService.verwijderAdres(adres);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/showAdressen";
    }

    @RequestMapping(value = "/bewerkAdres" , method = RequestMethod.POST)
    public String edit(@ModelAttribute("adres") Adres adres) {
        LOG.debug("Bewerk {} van de database table Adres", adres.getId());
        return "bewerkAdres";
    }
    
    @RequestMapping(value = "/controleerAdres" , method = RequestMethod.POST)
    public String check(@ModelAttribute("adres") Adres adres) {
        LOG.debug("Controleer {} van de database table Adres", adres.getId());
        try {
//			businessService.checkAdres(adres);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "bewerkAdres";
		}
        return update(adres);
    }
    
    @RequestMapping(value = "/updateAdres" , method = RequestMethod.POST)
    public String update(@ModelAttribute("adres") Adres adres) {
        LOG.debug("Update {} van de database table Adres", adres.getId());
        try {
			businessService.updateAdres(adres);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/showAdressen";
    }
}