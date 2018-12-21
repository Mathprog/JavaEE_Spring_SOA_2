package oc.projet.biblio.webapp.controller.serviceWeb;


import io.biblio.api.biblio_web_service.*;
import oc.projet.biblio.business.service.ExemplaireService;
import oc.projet.biblio.consumer.entity.impl.OuvrageImpl;
import oc.projet.biblio.consumer.entity.impl.PretImpl;
import oc.projet.biblio.consumer.entity.impl.UsagerImpl;
import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Ouvrage;
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
public class ExemplaireEndPoint {

    private static final String NAMESPACE_URI = "http://biblio.io/api/biblio-web-service";

    @Autowired
    private ExemplaireService exemplaireService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExemplaireRequest")
    @ResponsePayload
    public GetExemplaireResponse getAllExemplaire (){
        GetExemplaireResponse exemplaireResponse = new GetExemplaireResponse();
        List<Exemplaire> exemplaires = this.exemplaireService.findAll();
        List<ExemplaireWS> exemplaireWSList = new ArrayList<>();
        for (Exemplaire exemplaire : exemplaires){
            ExemplaireWS exemplaireWS = new ExemplaireWS();
            OuvrageWS ouvrageWS = new OuvrageWS();
            Ouvrage ouvrage = exemplaire.getOuvrage();
            if( ouvrage != null){
                BeanUtils.copyProperties(ouvrage, ouvrageWS);
            }
            BeanUtils.copyProperties(exemplaire, exemplaireWS);
            exemplaireWS.setOuvrage(ouvrageWS);
            exemplaireWSList.add(exemplaireWS);
        }

        exemplaireResponse.getExemplaireWS().addAll(exemplaireWSList);
        return exemplaireResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExemplaireByIdRequest")
    @ResponsePayload
    public GetExemplaireByIdResponse getExemplaireById (@RequestPayload GetExemplaireByIdRequest request){
        GetExemplaireByIdResponse exemplaireResponse = new GetExemplaireByIdResponse();
        Exemplaire exemplaire = this.exemplaireService.find(request.getId());
        ExemplaireWS exemplaireWS = null;
        if (exemplaire != null){
            exemplaireWS = new ExemplaireWS();
            BeanUtils.copyProperties(exemplaire, exemplaireWS);
        }
        exemplaireResponse.setExemplaireWS(exemplaireWS);
        return exemplaireResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExemplaireByBookRequest")
    @ResponsePayload
    public GetExemplaireByBookResponse getAllExemplaireByBook(@RequestPayload GetExemplaireByBookRequest request){
        GetExemplaireByBookResponse exemplairesResponse = new GetExemplaireByBookResponse();
        OuvrageWS ouvrageWS = request.getBook();
        Ouvrage ouvrage = null;
        if (ouvrageWS != null){
            ouvrage = new OuvrageImpl();
            BeanUtils.copyProperties(ouvrageWS, ouvrage);
        }

        List<Exemplaire> exemplairesFound = this.exemplaireService.findAllByBook( ouvrage);
        List<ExemplaireWS> exemplaireWSList = new ArrayList<>();
        for (Exemplaire exemplaire : exemplairesFound){
            ExemplaireWS exemplaireWS = new ExemplaireWS();
            BeanUtils.copyProperties(exemplaire, exemplaireWS);
            exemplaireWS.setOuvrage(ouvrageWS);
            exemplaireWSList.add(exemplaireWS);
        }
        exemplairesResponse.getExemplaireWS().addAll(exemplaireWSList);
        return exemplairesResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExemplaireByUsagerRequest")
    @ResponsePayload
    public GetExemplaireByUsagerResponse getAllExemplaireByUsager(@RequestPayload GetExemplaireByUsagerRequest request){
        GetExemplaireByUsagerResponse exemplairesResponse = new GetExemplaireByUsagerResponse();
        UsagerWS usagerWS = request.getUsager();
        Usager usager = null;
        if (usagerWS != null ){
            usager = new UsagerImpl();
            BeanUtils.copyProperties(usagerWS, usager);
        }
        List<Exemplaire> exemplairesFound = this.exemplaireService.findAllByUsager( usager);
        List<ExemplaireWS> exemplaireWSList = new ArrayList<>();
        for (Exemplaire exemplaire : exemplairesFound){
            ExemplaireWS exemplaireWS = new ExemplaireWS();
            OuvrageWS ouvrageWS = new OuvrageWS();
            BeanUtils.copyProperties(exemplaire, exemplaireWS);
            BeanUtils.copyProperties(exemplaire.getOuvrage(), ouvrageWS);
            exemplaireWS.setOuvrage(ouvrageWS);
            exemplaireWSList.add(exemplaireWS);
        }
        exemplairesResponse.getExemplaireWS().addAll(exemplaireWSList);
        return exemplairesResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExemplaireCreateRequest")
    @ResponsePayload
    public GetExemplaireCreateResponse getCreateBook(@RequestPayload GetExemplaireCreateRequest request){
        GetExemplaireCreateResponse exemplaireResponse = new GetExemplaireCreateResponse();
        OuvrageWS ouvrageWS = request.getOuvrage();
        Ouvrage ouvrage = null;
        ExemplaireWS exemplaireWS = null;
        if( ouvrageWS != null){
            ouvrage = new OuvrageImpl();
            BeanUtils.copyProperties(ouvrageWS, ouvrage);
            Exemplaire exemplaire = this.exemplaireService.createExemplaire(ouvrage);
            if (exemplaire != null){
                exemplaireWS = new ExemplaireWS();
                BeanUtils.copyProperties(exemplaire, exemplaireWS);
                exemplaireWS.setOuvrage(ouvrageWS);
            }
        }
        exemplaireResponse.setExemplaireWS(exemplaireWS);
        return exemplaireResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExemplaireByPretRequest")
    @ResponsePayload
    public GetExemplaireByPretResponse getExemplaireById (@RequestPayload GetExemplaireByPretRequest request){
        GetExemplaireByPretResponse exemplaireResponse = new GetExemplaireByPretResponse();
        PretWS pretWS = request.getPret();
        OuvrageWS ouvrageWS = null;
        ExemplaireWS exemplaireWS = null;
        Pret pret = null;
        if( pretWS != null){
            pret = new PretImpl();
            BeanUtils.copyProperties(pretWS, pret);
            Exemplaire exemplaire = this.exemplaireService.findByPret(pret);
            Ouvrage ouvrage = exemplaire.getOuvrage();
            if( ouvrage != null){
                ouvrageWS = new OuvrageWS();
                BeanUtils.copyProperties(ouvrage, ouvrageWS);
            }
            exemplaireWS = new ExemplaireWS();
            BeanUtils.copyProperties(exemplaire, exemplaireWS);
            exemplaireWS.setOuvrage(ouvrageWS);

        }
        exemplaireResponse.setExemplaireWS(exemplaireWS);
        return exemplaireResponse;
    }

}
