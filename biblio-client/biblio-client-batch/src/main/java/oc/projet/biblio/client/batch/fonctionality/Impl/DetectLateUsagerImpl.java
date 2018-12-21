package oc.projet.biblio.client.batch.fonctionality.Impl;


import oc.projet.biblio.client.batch.fonctionality.DetectLateUsager;
import oc.projet.biblio.client.business.service.UsagerService;
import oc.projet.biblio.client.consumer.generated.UsagerWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectLateUsagerImpl implements DetectLateUsager {


    @Autowired
    private UsagerService usagerService;

    @Override
    public void sendEMail(){
        List<UsagerWS> usagerWSList = this.usagerService.findAllByPretDate();
        if (usagerWSList != null ){
            usagerWSList.addAll(this.usagerService.findAllByRelanceDate());
            for(UsagerWS usagerWS : usagerWSList){
                System.err.println("Email envoyé à l'usager : " + usagerWS.getEmail());
            }
        } else {
            System.err.println("Aucune usager en retard.");
        }

    }

}
