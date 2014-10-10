package nl.kadaster.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.kadaster.business.BusinessService;
import nl.kadaster.web.domain.Adres;

@Controller
public class AdresDatabaseQueryController {

    private static final Logger LOG = LoggerFactory.getLogger(AdresDatabaseQueryController.class);

    @Autowired
    private BusinessService businessService;

    @RequestMapping(value = "/showAdressen" , method = RequestMethod.GET)
    public String show(Model model) {
        LOG.debug("Show the contents of database table Adres");
        return "/showAdressen";
    }

    @ModelAttribute("adressen")
    private List<Adres> getAdressen() {
        return businessService.getAdressen();
    }

}