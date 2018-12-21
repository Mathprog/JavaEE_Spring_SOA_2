package oc.projet.biblio.client.webapp.controller;


import oc.projet.biblio.client.business.service.OuvrageService;
import oc.projet.biblio.client.consumer.generated.OuvrageWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/ouvrages")
public class OuvrageController {

    @Autowired
    private OuvrageService ouvrageService;

    @RequestMapping(value = { "/liste" }, method = RequestMethod.GET)
    public String ouvragesPage(ModelMap model) {
        model.addAttribute("ouvragesDispos", this.ouvrageService.findAllWithDispo());
        model.addAttribute("ouvragesNonDispos", this.ouvrageService.findAllWithNoDispo());
        return "ouvrages";
    }

    @RequestMapping(value = "/recherche", method = RequestMethod.GET)
    public String ouvragesSearch(ModelMap model){
        return "recherche";
    }

    @PostMapping(value = "/recherche")
    public String ouvragesSearchResult(@RequestParam("titre") String titre, ModelMap modelMap){

        List<OuvrageWS> ouvragesWSList = this.ouvrageService.findAllByTitreResearch(titre);

        modelMap.addAttribute("ouvrages", ouvragesWSList);
        return "recherche_res";
    }
}
