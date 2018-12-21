package oc.projet.biblio.webapp.controller.serviceWeb;


import io.biblio.api.biblio_web_service.*;
import oc.projet.biblio.business.service.RelanceService;
import oc.projet.biblio.consumer.entity.impl.PretImpl;
import oc.projet.biblio.consumer.entity.impl.UsagerImpl;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Relance;
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
public class RelanceEndPoint {

    private static final String NAMESPACE_URI = "http://biblio.io/api/biblio-web-service";

    @Autowired
    private RelanceService relanceService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRelanceByIdRequest")
    @ResponsePayload
    public GetRelanceByIdResponse getRelanceByIdRequest(@RequestPayload GetRelanceByIdRequest request){
        GetRelanceByIdResponse relanceResponse = new GetRelanceByIdResponse();
        Relance relance = this.relanceService.find(request.getId());
        RelanceWS relanceWS = null;
        if( relance != null ){
            relanceWS = new RelanceWS();
            BeanUtils.copyProperties(relance, relanceWS);
        }
        relanceResponse.setRelance(relanceWS);
        return relanceResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRelanceRequest")
    @ResponsePayload
    public GetRelanceResponse getAllRelance(){
        GetRelanceResponse relanceResponse = new GetRelanceResponse();
        List<Relance> relances = this.relanceService.findAll();
        List<RelanceWS> relanceWSList = new ArrayList<>();
        if (relances != null ){
            for(Relance relance : relances){
                RelanceWS relanceWS = new RelanceWS();
                BeanUtils.copyProperties(relance, relanceWS);
                relanceWSList.add(relanceWS);
            }
            relanceResponse.getRelance().addAll(relanceWSList);
        }

        return relanceResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRelanceCreateRequest")
    @ResponsePayload
    public GetRelanceCreateResponse getCreateRelance(@RequestPayload GetRelanceCreateRequest request){
        GetRelanceCreateResponse relanceResponse = new GetRelanceCreateResponse();
        PretWS pretWS = request.getPret();
        RelanceWS relanceWS = null;
        Pret pret = new PretImpl();
        if (pretWS != null){
            BeanUtils.copyProperties(pretWS, pret);
            Relance relance = this.relanceService.createRelance(pret, request.getDateFin());
            if (relance != null){
                relanceWS = new RelanceWS();
                BeanUtils.copyProperties(relance, relanceWS);
            }
        }
        relanceResponse.setRelance(relanceWS);
        return relanceResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRelanceByPretRequest")
    @ResponsePayload
    public GetRelanceByPretResponse getRelanceByPret(@RequestPayload GetRelanceByPretRequest request){
        GetRelanceByPretResponse relanceResponse = new GetRelanceByPretResponse();
        PretWS pretWS = request.getPret();
        Pret pret = new PretImpl();
        RelanceWS relanceWS = null;
        if (pretWS != null){
            BeanUtils.copyProperties(pretWS, pret);
            Relance relance = this.relanceService.findByPret(pret);
            if( relance != null){
                relanceWS = new RelanceWS();
                BeanUtils.copyProperties(relance, relanceWS);
            }
        }
        relanceResponse.setRelance(relanceWS);
        return relanceResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRelanceByUsagerRequest")
    @ResponsePayload
    public GetRelanceByUsagerResponse getRelanceByUsager(@RequestPayload GetRelanceByUsagerRequest request){
        GetRelanceByUsagerResponse relanceResponse = new GetRelanceByUsagerResponse();
        UsagerWS usagerWS = request.getUsager();
        Usager usager = new UsagerImpl();
        List<RelanceWS> relanceWSList = new ArrayList<>();
        if( usagerWS != null ){
            BeanUtils.copyProperties(usagerWS, usager);
            List<Relance> relances = this.relanceService.findAllByUsager(usager);
            for(Relance relance : relances){
                RelanceWS relanceWS = new RelanceWS();
                BeanUtils.copyProperties(relance, relanceWS);
                relanceWSList.add(relanceWS);
            }
        }
        relanceResponse.getRelance().addAll(relanceWSList);
        return relanceResponse;

    }

}
