package oc.projet.biblio.webapp.controller.serviceWeb;


import io.biblio.api.biblio_web_service.*;
import oc.projet.biblio.business.service.UsagerService;
import oc.projet.biblio.consumer.entity.impl.UsagerImpl;
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
public class UsagerEndPoint {

    private static final String NAMESPACE_URI = "http://biblio.io/api/biblio-web-service";

    @Autowired
    private UsagerService usagerService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerRequest")
    @ResponsePayload
    public GetUsagerResponse getUsager(){
            GetUsagerResponse UsagerResponse = new GetUsagerResponse();
            List<Usager> usagers = this.usagerService.findAll();
            UsagerResponse.getUsagerWS().addAll(this.populateUsagerWSList(usagers));
            return UsagerResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerByEmailRequest")
    @ResponsePayload
    public GetUsagerByEmailResponse getUsagerByEmail(@RequestPayload GetUsagerByEmailRequest request){
        GetUsagerByEmailResponse usagerResponse = new GetUsagerByEmailResponse();
        Usager usager = this.usagerService.findUsagerByEmail(request.getEmail());
        usagerResponse.setUsagerWS(populateUsagerWS(usager));
        return usagerResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerCreateRequest")
    @ResponsePayload
    public GetUsagerCreateResponse getUsagerCreate(@RequestPayload GetUsagerCreateRequest request){
        Usager usager = this.usagerService.createUsager(request.getEmail());
        GetUsagerCreateResponse usagerResponse = new GetUsagerCreateResponse();
        usagerResponse.setUsagerWS(populateUsagerWS(usager));
        return usagerResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerByIdRequest")
    @ResponsePayload
    public GetUsagerByIdResponse getUsagerById(@RequestPayload GetUsagerByIdRequest request){
        Usager usager = this.usagerService.find(request.getId());
        UsagerWS usagerWs = null;
        if (usager != null){
            usagerWs = new UsagerWS();
            BeanUtils.copyProperties(usager, usagerWs);
        }
        GetUsagerByIdResponse usagerResponse = new GetUsagerByIdResponse();
        usagerResponse.setUsagerWS(usagerWs);
        return usagerResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerByPretDateRequest")
    @ResponsePayload
    public GetUsagerByPretDateResponse getUsagerByPretDate (){
        List<Usager> usagerList = this.usagerService.findAllByPretDate();
        GetUsagerByPretDateResponse usagerByPretDateResponse = new GetUsagerByPretDateResponse();

        usagerByPretDateResponse.getUsagerWS().addAll(this.populateUsagerWSList(usagerList));
        return usagerByPretDateResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerByRelanceDateRequest")
    @ResponsePayload
    public GetUsagerByRelanceDateResponse getUsagerByRelanceDate (){
        List<Usager> usagerList = this.usagerService.findAllByRelanceDate();
        GetUsagerByRelanceDateResponse usagerByRelanceDateResponse = new GetUsagerByRelanceDateResponse();


        usagerByRelanceDateResponse.getUsagerWS().addAll(populateUsagerWSList(usagerList));
        return usagerByRelanceDateResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerByPretAndRelanceDateRequest")
    @ResponsePayload
    public GetUsagerByPretAndRelanceDateResponse getReservationByUsagerAndOuvrageResponse(@RequestPayload GetUsagerByPretAndRelanceDateRequest usagerByPretAndRelanceDateRequest){
        GetUsagerByPretAndRelanceDateResponse usagerByPretAndRelanceDateResponse = new GetUsagerByPretAndRelanceDateResponse();
        List<Usager> usagerList = this.usagerService.findAllByPretAndRelanceDate(usagerByPretAndRelanceDateRequest.getDate());
        usagerByPretAndRelanceDateResponse.getUsagerWS().addAll( this.populateUsagerWSList(usagerList));
        return usagerByPretAndRelanceDateResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerUpdateRequest")
    @ResponsePayload
    public GetUsagerUpdateResponse usagerUpdateResponse(@RequestPayload GetUsagerUpdateRequest usagerUpdateRequest){
        GetUsagerUpdateResponse usagerUpdateResponse = new GetUsagerUpdateResponse();
        UsagerWS usagerWS = usagerUpdateRequest.getUsagerWS();
        Usager usager = new UsagerImpl();
        if(usagerWS != null){
            BeanUtils.copyProperties(usagerWS, usager);
            usager = this.usagerService.update(usager);
            usagerWS = this.populateUsagerWS(usager);
        }
        usagerUpdateResponse.setUsagerWS(usagerWS);
        return usagerUpdateResponse;
    }

    private List<UsagerWS> populateUsagerWSList(List<Usager> usagerList) {
        List<UsagerWS> usagerWSList = null;
        if( usagerList != null) {
            usagerWSList = new ArrayList<>();
            for(Usager usager : usagerList){
                UsagerWS usagerWS = new UsagerWS();
                BeanUtils.copyProperties(usager, usagerWS);
                usagerWSList.add(usagerWS);
            }
        }
        return usagerWSList;
    }

    private UsagerWS populateUsagerWS(Usager usager){
        UsagerWS usagerWs = null;
        if( usager != null ){
            usagerWs = new UsagerWS();
            BeanUtils.copyProperties(usager, usagerWs);
        }
        return  usagerWs;
    }

}
