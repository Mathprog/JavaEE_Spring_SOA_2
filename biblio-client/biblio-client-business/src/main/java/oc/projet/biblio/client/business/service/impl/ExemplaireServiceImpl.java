package oc.projet.biblio.client.business.service.impl;

import oc.projet.biblio.client.business.service.ExemplaireService;
import oc.projet.biblio.client.consumer.generated.ExemplaireWS;
import oc.projet.biblio.client.consumer.generated.OuvrageWS;
import oc.projet.biblio.client.consumer.generated.PretWS;
import oc.projet.biblio.client.consumer.generated.UsagerWS;
import oc.projet.biblio.client.consumer.ws.ExemplaireClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemplaireServiceImpl implements ExemplaireService {

    @Autowired
    private ExemplaireClient exemplaireClient;

    public ExemplaireWS find(int id){
        return this.exemplaireClient.getExemplaireByIdCLientRequest(id);
    }

    public List<ExemplaireWS> findAllExemplaire(){
        return this.exemplaireClient.getExemplaireClientRequest();
    }

    public ExemplaireWS createExemplaire(OuvrageWS ouvrage){
        return this.exemplaireClient.getExemplaireCreateClientRequest(ouvrage);
    }

    public ExemplaireWS findByPret(PretWS pret){
        return this.exemplaireClient.getExemplaireByPret(pret);
    }

    public List<ExemplaireWS> findAllByBook(OuvrageWS ouvrage){
        return this.exemplaireClient.getExemplaireByBookCLientRequest(ouvrage);
    }

    public  List<ExemplaireWS> findAllByUsager(UsagerWS usager){
        return this.exemplaireClient.getExemplaireByUsager(usager);
    }

   /*@Bean
    public void setExemplaireClient(ExemplaireClient exemplaireClient ){
        this.exemplaireClient = exemplaireClient;
    }*/
}
