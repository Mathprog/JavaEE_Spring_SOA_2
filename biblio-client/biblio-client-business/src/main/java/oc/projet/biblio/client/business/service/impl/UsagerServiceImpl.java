package oc.projet.biblio.client.business.service.impl;


import oc.projet.biblio.client.business.service.UsagerService;
import oc.projet.biblio.client.consumer.generated.UsagerWS;
import oc.projet.biblio.client.consumer.ws.UsagerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagerServiceImpl implements UsagerService {

    @Autowired
    private UsagerClient usagerClient;

    @Override
    public UsagerWS find(int id) {
        return usagerClient.getUsagerByIdClientRequest(id);
    }


    @Override
    public UsagerWS createUsager(String email) {
        return usagerClient.getUsagerCreateClientRequest(email);
    }

    @Override
    public UsagerWS findUsagerByEmail(String email) {
        return usagerClient.getUsagerByEmailClientRequest(email);
    }

    @Override
    public List<UsagerWS> findAll() {

        return usagerClient.getUsagerClientRequest();
    }

    @Override
    public List<UsagerWS> findAllByRelanceDate() {

        return usagerClient.usagerByRelanceDateClientRequest();
    }

    @Override
    public List<UsagerWS> findAllByPretDate() {

        return usagerClient.getUsagerByPretDateClientRequest();
    }

    /*@Bean
    public void setUsagerClient(UsagerClient usagerClient){
        this.usagerClient = usagerClient;
    }*/
}
