package oc.projet.biblio.client.batch.fonctionality.Impl;


import oc.projet.biblio.client.batch.fonctionality.DetectLateUsager;
import oc.projet.biblio.client.business.service.*;
import oc.projet.biblio.client.consumer.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetectLateUsagerImpl implements DetectLateUsager {


    @Autowired
    private UsagerService usagerService;

    @Autowired
    private PretService pretService;

    @Autowired
    private RelanceService relanceService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private ReservationService reservationService;


    @Override
    public void sendEMail(){
        List<UsagerWS> usagerWSPretList = this.usagerService.findAllByPretDate();
        List<UsagerWS> usagerWSRelanceList = this.usagerService.findAllByRelanceDate();
        List<UsagerWS> usagerAllLate = new ArrayList<>();
        if (usagerWSPretList != null || usagerWSPretList != null){
            usagerAllLate.addAll(usagerWSPretList);
            usagerAllLate.addAll(usagerWSRelanceList);
            for(UsagerWS usagerWS : usagerAllLate){
                System.err.println("Email envoyé à l'usager : " + usagerWS.getEmail());
            }
        } else{
            System.err.println("Aucune usager en retard.");
        }
    }


    @Override
    public void sendMailAlert(){
        LocalDate date = LocalDate.now().plusDays(5);
        List<UsagerWS> usagerWSList = this.usagerService.findAllByPretAndRelanceDate(date);
        for(UsagerWS usagerWS : usagerWSList){
            System.err.println("\n\nEnvoie d'un mail de relance à l'usager: "+ usagerWS.getEmail());
            List<PretWS> pretWSList = this.pretService.findAllPretByUsagerAndDate(usagerWS,date );
            List<RelanceWS> relanceWSList = this.relanceService.findAllByUsagerAndDate(usagerWS, date);

            for(PretWS pretWS : pretWSList){
                ExemplaireWS exemplaireWS = this.exemplaireService.findByPret(pretWS);
                System.err.println("Rappel ! Votre prêt concernant l'ouvrage: " + exemplaireWS.getOuvrage().getTitre() + " arrive à expiration le: " + pretWS.getDateFin().toString());
            }

        }

    }

    @Override
    public void sendMailReservationAlert(){
        List<ReservationWS> reservationWSDisList = this.reservationService.findAllDispo();
        System.err.println("\n \n Envoie des MAILS des réservation à venir récupérer.");
        for (ReservationWS reservationWS : reservationWSDisList){
            System.err.println("\n \n Envoie d'un mail à l'utilisateur: "+ reservationWS.getUsager().getEmail() + " il vous reste "+ ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(2)) +" jours pour venir chercher votre livre.");
        }
    }

    @Override
    public void deleteLateReservation(){
        List<ReservationWS> reservationWSLateList = this.reservationService.findAllLate();
        for(ReservationWS reservationWS : reservationWSLateList){
            System.err.println("Envoie du mail de suppression de sa réservation à : " + reservationWS.getUsager().getEmail());
        }
        this.reservationService.deleteLateRequest(reservationWSLateList);
    }

}
