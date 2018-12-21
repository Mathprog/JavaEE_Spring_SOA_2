package oc.projet.biblio.client.webapp.controller;


import oc.projet.biblio.client.business.service.PretService;
import oc.projet.biblio.client.business.service.RelanceService;
import oc.projet.biblio.client.consumer.generated.PretWS;
import oc.projet.biblio.client.consumer.generated.RelanceWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/relance")
public class RelanceController {


    @Autowired
    private RelanceService relanceService;

    @Autowired
    private PretService pretService;


    @PostMapping(value = "/add")
    public String addRelanceController(@RequestParam("pretId") int pretId, @RequestParam("weeks") int weeks, ModelMap modelMap){
        PretWS pretWS = pretService.find(pretId);
        RelanceWS relanceWS = null;
        String message;
        if(weeks != 0){
            if( pretWS != null) {
                relanceWS = this.relanceService.createRelance(pretWS, pretWS.getDateFin().plusWeeks(weeks));
                message = "Votre relance a bien été enregistrée. ";
            } else {
                message = "Erreur, le prêt n'existait pas";
            }
        } else {
            message = "Veuillez saisir un nombre de semaines";
        }


        modelMap.addAttribute("message", message);
        modelMap.addAttribute("relance", relanceWS);
        return "relance";
    }

}
