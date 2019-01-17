package oc.projet.biblio.client.business.service.impl;

import oc.projet.biblio.client.business.service.RelanceService;
import oc.projet.biblio.client.consumer.generated.PretWS;
import oc.projet.biblio.client.consumer.generated.RelanceWS;
import oc.projet.biblio.client.consumer.generated.UsagerWS;
import oc.projet.biblio.client.consumer.ws.RelanceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class RelanceServiceImpl implements RelanceService {

    @Autowired
    private RelanceClient relanceClient;

    @Override
    public RelanceWS find(int id){
        return this.relanceClient.getRelanceByIdClientRequest(id);
    }

    @Override
    public RelanceWS createRelance(PretWS pret, LocalDate date_fin){
        return this.relanceClient.getRelanceCreateClientRequest(pret, date_fin);
    }

    @Override
    public List<RelanceWS> findAll(){
        return this.relanceClient.getRelanceClientResponse();
    }

    @Override
    public RelanceWS findByPret(PretWS pret){
        return this.relanceClient.getRelanceByPretClientRequest(pret);
    }

    @Override
    public List<RelanceWS> findAllByUsager(UsagerWS usager){
        return this.relanceClient.relanceByUsagerClientRequest(usager);
    }

    @Override
    public List<RelanceWS> findAllByUsagerAndDate(UsagerWS usagerWS, LocalDate date){
        return this.relanceClient.getAllRelanceByUsagerAndDate(usagerWS, date);
    }

    /*@Bean
    public void setRelanceClient(RelanceClient relanceClient){
        this.relanceClient = relanceClient;
    }*/

}
