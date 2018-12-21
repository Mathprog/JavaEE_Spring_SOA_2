package oc.projet.biblio.client.business.service.impl;

import oc.projet.biblio.client.business.service.OuvrageService;
import oc.projet.biblio.client.consumer.generated.OuvrageWS;
import oc.projet.biblio.client.consumer.ws.OuvrageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;


@Service
public class OuvrageServiceImpl implements OuvrageService{

    @Autowired
    private OuvrageClient ouvrageClient;




    @Override
    public List<OuvrageWS> findAllOuvrage(){
        return ouvrageWSListTranscode(this.ouvrageClient.geOuvrageClientRequest());
    }

    @Override
    public OuvrageWS find(int id){
        return this.ouvrageClient.getOuvrageByIdClientRequest(id);
    }


    @Override
    public OuvrageWS createOuvrate(String titre){
        return this.ouvrageClient.getOuvrageCreateClientRequest(titre);
    }


    @Override
    public List<OuvrageWS> findAllWithDispo(){
        return ouvrageWSListTranscode(this.ouvrageClient.getOuvrageByDispo());
    }


    @Override
    public List<OuvrageWS> findAllWithNoDispo(){
        return ouvrageWSListTranscode(this.ouvrageClient.getOuvrageByNoDispo());
    }


    @Override
    public List<OuvrageWS> findAllByTitreResearch(String titre){
        return ouvrageWSListTranscode(this.ouvrageClient.getOuvrageByTitreClientRequest(titre));
    }

    private List<OuvrageWS> ouvrageWSListTranscode(List<OuvrageWS> ouvrageWSList){
        for(OuvrageWS ouvrageWS : ouvrageWSList){
            if(ouvrageWS.getImageb() != null){
                String base64DataString = null;
                base64DataString = Base64.getEncoder().withoutPadding().encodeToString(ouvrageWS.getImageb());
                ouvrageWS.setImageBase64DataString(base64DataString);
                ouvrageWS.setImageb(null);
            }
        }
        return ouvrageWSList;
    }

}
