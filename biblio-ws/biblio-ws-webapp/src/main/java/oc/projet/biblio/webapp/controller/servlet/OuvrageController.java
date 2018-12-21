package oc.projet.biblio.webapp.controller.servlet;


import oc.projet.biblio.business.service.OuvrageService;
import oc.projet.biblio.model.entity.Ouvrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/ouvrage")
@Transactional(propagation = Propagation.REQUIRED)
public class OuvrageController {

    @Autowired
    private OuvrageService ouvrageService;

    @GetMapping(path="/all") // Map ONLY GET Requests
    public String showAllOuvrages(Map<String, Object> model) {

        // List<Ouvrage> ouvrages = this.ouvrageService.findAllOuvrage();
        List<Ouvrage> ouvragesDispos = this.ouvrageService.findAllWithDispo();
        List<Ouvrage> ouvragesNonDispos = this.ouvrageService.findAllWithNoDispo();
        model.put("ouvrages", ouvragesDispos);
        model.put("ouvragesNonDispos", ouvragesNonDispos);
        return "ouvrages";
    }
}
