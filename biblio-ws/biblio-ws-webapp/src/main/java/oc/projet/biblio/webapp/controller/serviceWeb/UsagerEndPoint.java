package oc.projet.biblio.webapp.controller.serviceWeb;


import io.biblio.api.biblio_web_service.*;
import oc.projet.biblio.business.service.UsagerService;
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
            List<UsagerWS> usagersWS = null;
            if( usagers != null){
                usagersWS = new ArrayList<>();
                for (Usager usager : usagers){
                    UsagerWS usagerws = new UsagerWS();
                    BeanUtils.copyProperties(usager, usagerws);
                    usagersWS.add(usagerws);
                }
                UsagerResponse.getUsagerWS().addAll(usagersWS);
            }
            return UsagerResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerByEmailRequest")
    @ResponsePayload
    public GetUsagerByEmailResponse getUsagerByEmail(@RequestPayload GetUsagerByEmailRequest request){
        GetUsagerByEmailResponse usagerResponse = new GetUsagerByEmailResponse();
        Usager usager = this.usagerService.findUsagerByEmail(request.getEmail());
        UsagerWS usagerWs = null;
        if (usager != null){
            usagerWs = new UsagerWS();
            BeanUtils.copyProperties(usager, usagerWs);
        }
        usagerResponse.setUsagerWS(usagerWs);
        return usagerResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerCreateRequest")
    @ResponsePayload
    public GetUsagerCreateResponse getUsagerCreate(@RequestPayload GetUsagerCreateRequest request){
        Usager usager = this.usagerService.createUsager(request.getEmail());
        GetUsagerCreateResponse usagerResponse = new GetUsagerCreateResponse();
        UsagerWS usagerWs = null;
        if( usager != null ){
            usagerWs = new UsagerWS();
            BeanUtils.copyProperties(usager, usagerWs);
        }
        usagerResponse.setUsagerWS(usagerWs);
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
        List<UsagerWS> usagerWSList = null;
        if( usagerList != null ){
            usagerWSList = new ArrayList<>();
            for(Usager usager : usagerList){
                UsagerWS usagerWS = new UsagerWS();
                BeanUtils.copyProperties(usager, usagerWS);
                usagerWSList.add(usagerWS);
            }
            usagerByPretDateResponse.getUsagerWS().addAll(usagerWSList);
        }

        return usagerByPretDateResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsagerByRelanceDateRequest")
    @ResponsePayload
    public GetUsagerByRelanceDateResponse getUsagerByRelanceDate (){
        List<Usager> usagerList = this.usagerService.findAllByRelanceDate();
        GetUsagerByRelanceDateResponse usagerByRelanceDateResponse = new GetUsagerByRelanceDateResponse();
        List<UsagerWS> usagerWSList = null;
        if( usagerList != null) {
            usagerWSList = new ArrayList<>();
            for(Usager usager : usagerList){
                UsagerWS usagerWS = new UsagerWS();
                BeanUtils.copyProperties(usager, usagerWS);
                usagerWSList.add(usagerWS);
            }
            usagerByRelanceDateResponse.getUsagerWS().addAll(usagerWSList);
        }
        return usagerByRelanceDateResponse;
    }

}
