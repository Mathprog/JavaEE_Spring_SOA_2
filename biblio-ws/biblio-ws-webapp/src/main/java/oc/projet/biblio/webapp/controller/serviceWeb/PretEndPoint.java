package oc.projet.biblio.webapp.controller.serviceWeb;


import io.biblio.api.biblio_web_service.*;
import oc.projet.biblio.business.service.PretService;
import oc.projet.biblio.consumer.entity.impl.ExemplaireImpl;
import oc.projet.biblio.consumer.entity.impl.PretImpl;
import oc.projet.biblio.consumer.entity.impl.UsagerImpl;
import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Usager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
@Transactional(propagation = Propagation.REQUIRED)
public class PretEndPoint {

    private static final String NAMESPACE_URI = "http://biblio.io/api/biblio-web-service";

    @Autowired
    private PretService pretService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPretByIdRequest")
    @ResponsePayload
    public GetPretByIdResponse getPretById(@RequestPayload GetPretByIdRequest request){
        GetPretByIdResponse pretResponse = new GetPretByIdResponse();
        Pret pret = this.pretService.find(request.getId());
        PretWS pretWS = null;
        if (pret != null){
            pretWS = new PretWS();
            BeanUtils.copyProperties(pret, pretWS);
        }
        pretResponse.setPret(pretWS);
        return pretResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPretsRequest")
    @ResponsePayload
    public GetPretsResponse getAllPrets(){
        GetPretsResponse pretsResponse = new GetPretsResponse();
        List<Pret> prets = this.pretService.findAll();
        List<PretWS> pretWSList = new ArrayList<>();
        for(Pret pret : prets){
            PretWS pretWS = new PretWS();
            BeanUtils.copyProperties(pret, pretWS);
            pretWSList.add(pretWS);
        }
        pretsResponse.getPret().addAll(pretWSList);
        return pretsResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPretCreateRequest")
    @ResponsePayload
    public GetPretCreateResponse getPretCreate(@RequestPayload GetPretCreateRequest request){
            GetPretCreateResponse pretCreateResponse = new GetPretCreateResponse();
            ExemplaireWS exemplaireWS = request.getExemplaire();
            UsagerWS usagerWS = request.getUsager();
            PretWS pretWS = null;

            Exemplaire exemplaire = new ExemplaireImpl();
            Usager usager = new UsagerImpl();
            if( exemplaireWS != null){
                BeanUtils.copyProperties(exemplaireWS, exemplaire);
            }
            if (usagerWS != null ){
                BeanUtils.copyProperties(usagerWS, usager);
            }

            Pret pret = this.pretService.createPret(exemplaire, usager, request.getDatePret(), request.getDateFin());
            if( pret != null){
                pretWS = new PretWS();
                BeanUtils.copyProperties(pret, pretWS);
            }
            pretCreateResponse.setPret(pretWS);
            return pretCreateResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPretByExemplaireRequest")
    @ResponsePayload
    public GetPretByExemplaireResponse getPretByExemplaire(@RequestPayload GetPretByExemplaireRequest request){
        GetPretByExemplaireResponse pretResponse = new GetPretByExemplaireResponse();
        ExemplaireWS exemplaireWS = request.getExemplaire();
        Exemplaire exemplaire = null;
        PretWS pretWS = null;
        if (exemplaireWS != null){
            exemplaire = new ExemplaireImpl();
            BeanUtils.copyProperties(exemplaireWS, exemplaire);
        }

        if( exemplaire != null){
            Pret pret = this.pretService.findByExemplaire(exemplaire);
            pretWS = new PretWS();
            BeanUtils.copyProperties(pret, pretWS);
        }
        pretResponse.setPret(pretWS);
        return pretResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPretByUsagerRequest")
    @ResponsePayload
    public GetPretByUsagerResponse getPretByUsager (@RequestPayload GetPretByUsagerRequest request){
        GetPretByUsagerResponse pretResponse = new GetPretByUsagerResponse();
        UsagerWS usagerWS = request.getUsager();
        Usager usager = new UsagerImpl();
        if( usagerWS != null){
            BeanUtils.copyProperties(usagerWS, usager);
            List<Pret> prets = this.pretService.findAllByUsager(usager);
            List<PretWS> pretWSList = new ArrayList<>();
            for(Pret pret : prets){
                PretWS pretWS = new PretWS();
                BeanUtils.copyProperties(pret, pretWS);
                pretWSList.add(pretWS);
            }
            pretResponse.getPret().addAll(pretWSList);
        }
        return pretResponse;
    }
}
