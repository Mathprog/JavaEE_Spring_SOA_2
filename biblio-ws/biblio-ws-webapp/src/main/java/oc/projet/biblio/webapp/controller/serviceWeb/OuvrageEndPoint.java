package oc.projet.biblio.webapp.controller.serviceWeb;

import io.biblio.api.biblio_web_service.*;
import oc.projet.biblio.business.service.OuvrageService;
import oc.projet.biblio.model.entity.Ouvrage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;
import java.util.*;
import java.util.List;


@Endpoint
@Transactional(propagation = Propagation.REQUIRED)
public class OuvrageEndPoint {

    private static final String NAMESPACE_URI = "http://biblio.io/api/biblio-web-service";

    @Autowired
    private OuvrageService ouvrageService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOuvrageRequest")
    @ResponsePayload
    public GetOuvrageResponse getOuvrage(){
        GetOuvrageResponse OuvrageResponse = new GetOuvrageResponse();
        List<Ouvrage> ouvrages = this.ouvrageService.findAllOuvrage();
        OuvrageResponse.getOuvrageWS().addAll(populateReturnList(ouvrages));

        return OuvrageResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOuvrageByIdRequest")
    @ResponsePayload
    public GetOuvrageByIdResponse getOuvrageById(@RequestPayload GetOuvrageByIdRequest request) {
        GetOuvrageByIdResponse ouvrageResponseWS = new GetOuvrageByIdResponse();
        Ouvrage ouvrage = this.ouvrageService.find(request.getId());
        OuvrageWS ouvrageWS  = null;
        if( ouvrage != null){
            ouvrageWS  = new OuvrageWS();
            BeanUtils.copyProperties(ouvrage, ouvrageWS);
        }
        ouvrageResponseWS.setOuvrageWS(ouvrageWS);
        return ouvrageResponseWS;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOuvrageCreateRequest")
    @ResponsePayload
    public GetOuvrageCreateResponse getOuvrageCreate(@RequestPayload GetOuvrageCreateRequest request){
        GetOuvrageCreateResponse ouvrageResponse = new GetOuvrageCreateResponse();
        Ouvrage ouvrage = this.ouvrageService.createOuvrage(request.getTitre(), request.getResume(), request.getAuteur() , LocalDate.now().minusYears(8));
        OuvrageWS ouvrageWS = null;
        if( ouvrage != null ){
            ouvrageWS = new OuvrageWS();
            BeanUtils.copyProperties(ouvrage, ouvrageWS);
        }
        ouvrageResponse.setOuvrageWS(ouvrageWS);
        return ouvrageResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOuvrageByTitreRequest")
    @ResponsePayload
    public GetOuvrageByTitreResponse getOuvrageByTitre (@RequestPayload GetOuvrageByTitreRequest request){
        GetOuvrageByTitreResponse ouvrageResponse = new GetOuvrageByTitreResponse();
        List<Ouvrage> ouvrages = this.ouvrageService.findAllByTitreResearch(request.getTitre());
        ouvrageResponse.getOuvrageWS().addAll(populateReturnList(ouvrages));
        return ouvrageResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOuvrageByDispoRequest")
    @ResponsePayload
    public GetOuvrageByDispoResponse getOuvrageByDispoClientRequest (){
        GetOuvrageByDispoResponse ouvrageByDispoResponse = new GetOuvrageByDispoResponse();
        List<Ouvrage> ouvrages = this.ouvrageService.findAllWithDispo();
        ouvrageByDispoResponse.getOuvrageWS().addAll(populateReturnList(ouvrages));
        return ouvrageByDispoResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOuvrageByNoDispoRequest")
    @ResponsePayload
    public GetOuvrageByNoDispoResponse getOuvrageByNoDispoClientRequest (){
        GetOuvrageByNoDispoResponse ouvrageByNoDispoResponse = new GetOuvrageByNoDispoResponse();
        List<Ouvrage> ouvrages = this.ouvrageService.findAllWithNoDispo();
        List<OuvrageWS> ouvrageWSList = new ArrayList<>();
        ouvrageByNoDispoResponse.getOuvrageWS().addAll(populateReturnList(ouvrages));
        return ouvrageByNoDispoResponse;
    }



    private List<OuvrageWS> populateReturnList(List<Ouvrage> ouvrageList){
        List<OuvrageWS> ouvrageWSList = new ArrayList<>();
        for (Ouvrage ouvrage : ouvrageList){
            OuvrageWS ouvrageWS = new OuvrageWS();
            BeanUtils.copyProperties(ouvrage, ouvrageWS);
            ouvrageWSList.add(ouvrageWS);
        }
        return ouvrageWSList;
    }

}
