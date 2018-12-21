package oc.projet.biblio.client.business.service.impl;

import oc.projet.biblio.client.business.service.PretService;
import oc.projet.biblio.client.consumer.generated.ExemplaireWS;
import oc.projet.biblio.client.consumer.generated.PretWS;
import oc.projet.biblio.client.consumer.generated.UsagerWS;
import oc.projet.biblio.client.consumer.ws.PretClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class PretServiceImpl implements PretService {

    @Autowired
    private PretClient pretClient;

    @Override
    public PretWS find(int id){
        return this.pretClient.getPretByIdClientRequest(id);
    }

    @Override
    public PretWS createPret(ExemplaireWS exemplaire, UsagerWS usager, LocalDate date_pret, LocalDate date_fin){
        return this.pretClient.getPretCreateClientRequest(usager, exemplaire, date_pret, date_fin);
    }

    @Override
    public List<PretWS> findAll(){

        return this.pretClient.getPretClientResponse();
    }

    @Override
    public PretWS findByExemplaire(ExemplaireWS exemplaireWS){

        return this.pretClient.getPretByExemplaireClientRequest(exemplaireWS);
    }

    @Override
    public List<PretWS> findAllByUsager(UsagerWS usagerWS){
        return this.pretClient.getPretByUsagerClientRequest(usagerWS);
    }

    /*@Bean
    public void setPretClient (PretClient pretClient){
        this.pretClient = pretClient;
    }*/
}
