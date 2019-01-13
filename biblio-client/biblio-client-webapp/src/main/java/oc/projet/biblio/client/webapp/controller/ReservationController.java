package oc.projet.biblio.client.webapp.controller;


import oc.projet.biblio.client.business.service.OuvrageService;
import oc.projet.biblio.client.business.service.ReservationService;
import oc.projet.biblio.client.business.service.UsagerService;
import oc.projet.biblio.client.consumer.generated.OuvrageWS;
import oc.projet.biblio.client.consumer.generated.ReservationWS;
import oc.projet.biblio.client.consumer.generated.UsagerWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Controller
@RequestMapping("/reservation")
public class ReservationController {


    @Autowired
    private ReservationService reservationService;

    @Autowired
    private OuvrageService ouvrageService;

    @Autowired
    private UsagerService usagerService;

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String createReservationView(@PathVariable("id") int id, ModelMap modelMap){
        OuvrageWS ouvrageWS = this.ouvrageService.find(id);
        modelMap.addAttribute("ouvrage", ouvrageWS);

        return "reservationForm";
    }


    @PostMapping(value = "/create")
    public String createReservation(@RequestParam("ouvrageId") String id, @RequestParam("email") String email, ModelMap modelMap){
        OuvrageWS ouvrageWS = this.ouvrageService.find(Integer.parseInt(id));
        UsagerWS usagerWS = this.usagerService.findUsagerByEmail(email);
        ReservationWS reservationWS = this.reservationService.create(usagerWS, ouvrageWS);
        String message;
        if( reservationWS == null){
            message = "Vous ne pouvez pas réserver cet Ouvrage.";
        } else {
            message = "L'ouvrage à bien étéréservé. Un email vous sera envoyé lorsque vous devrez aller le récupérer";
        }
        modelMap.addAttribute("message", message);
        return "reservationDone";
    }

}
