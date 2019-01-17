package oc.projet.biblio.client.webapp.controller;


import oc.projet.biblio.client.business.service.*;
import oc.projet.biblio.client.consumer.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = { "/prets" }, method = RequestMethod.GET)
    public String pretsPage(ModelMap model) {
        return "prets";
    }

    @PostMapping(path="/prets/details")
    public String showAllPretDetails(@RequestParam("email") String email, ModelMap modelMap){
        UsagerWS usagerWS = this.usagerService.findUsagerByEmail(email);
        List<PretWS> pretWSList = null;
        List<ReservationWS> reservationWSList = null;
        String message = null;
        if(usagerWS != null){
            pretWSList = this.pretService.findAllByUsager(usagerWS );
            reservationWSList = this.reservationService.findAllByUsager(usagerWS);
            for(PretWS pretWS : pretWSList){
                ExemplaireWS exemplaireWS = this.exemplaireService.findByPret(pretWS);
                RelanceWS relanceWS = this.relanceService.findByPret(pretWS);
                pretWS.setRelance(relanceWS);
                pretWS.setExemplaire(exemplaireWS);
                if(pretWS.getDateFin().isBefore(LocalDate.now())){
                    pretWS.setReservable(false);
                } else {
                    pretWS.setReservable(true);
                }

            }
        } else {
            message = "Nous ne vous avons pas trouvé avec l'email : "+ email;
        }


        modelMap.addAttribute("usager", email);
        modelMap.addAttribute("usagerWS", usagerWS);
        modelMap.addAttribute("prets", pretWSList);
        modelMap.addAttribute("reservations", reservationWSList);
        modelMap.addAttribute("message", message);

        return "pretsDetails";
    }

    @PostMapping(path = "/update")
    public String usagerUpdate(@RequestParam("email") String email, @RequestParam("expiration") String expiration, ModelMap modelMap){
        UsagerWS usagerWS = this.usagerService.findUsagerByEmail(email);
        String message;
        if(usagerWS != null){
            if(expiration.equals("checked")){
                usagerWS.setPretExpiration(true);
            } else {
                usagerWS.setPretExpiration(false);
            }
            usagerService.update(usagerWS);
            message = "Votre profil a bien été mis à jour.";
        } else {
            message = "Aucune utilisateur pour le mail: " + email;

        }
        modelMap.addAttribute("message", message);
        return "updateConfirmation";
    }

}
