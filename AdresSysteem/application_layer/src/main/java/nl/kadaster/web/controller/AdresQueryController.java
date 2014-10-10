package nl.kadaster.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.kadaster.web.domain.Adres;

@Controller
public class AdresQueryController {

    private static final Logger LOG = LoggerFactory.getLogger(AdresQueryController.class);

    @Autowired
    private Adres adres;

    @RequestMapping(value = "/showAdres" , method = RequestMethod.GET)

    public String show(Model model) {
        LOG.debug("Show the adres contents");
        return "/showAdres";
    }

    @ModelAttribute("adres")
    private Adres getAdres() {
        return adres;
    }

}