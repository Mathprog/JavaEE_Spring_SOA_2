package oc.projet.biblio.client.webapp.controller;


import oc.projet.biblio.client.business.service.*;
import oc.projet.biblio.client.consumer.generated.*;
import oc.projet.biblio.client.consumer.ws.UsagerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/usager")
public class UsagerController {

    @Autowired
    private UsagerService usagerService;

    @Autowired
    private PretService pretService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private RelanceService relanceService;

    @RequestMapping(value = { "/prets" }, method = RequestMethod.GET)
    public String pretsPage(ModelMap model) {
        return "prets";
    }

    @PostMapping(path="/prets/details")
    public String showAllPretDetails(@RequestParam("email") String email, ModelMap modelMap){
        UsagerWS usagerWS = this.usagerService.findUsagerByEmail(email);
        List<PretWS> pretWSList = null;
        String message = null;
        if(usagerWS != null){
            pretWSList = this.pretService.findAllByUsager(usagerWS );
            for(PretWS pretWS : pretWSList){
                ExemplaireWS exemplaireWS = this.exemplaireService.findByPret(pretWS);
                RelanceWS relanceWS = this.relanceService.findByPret(pretWS);
                pretWS.setRelance(relanceWS);
                pretWS.setExemplaire(exemplaireWS);

            }
        } else {
            message = "Nous ne vous avons pas trouvé...";
        }


        modelMap.addAttribute("usager", email);
        modelMap.addAttribute("prets", pretWSList);
        modelMap.addAttribute("message", message);

        return "pretsDetails";
    }

}
