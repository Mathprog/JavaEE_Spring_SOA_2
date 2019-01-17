package oc.projet.biblio.client.consumer.ws;

import oc.projet.biblio.client.consumer.generated.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.time.LocalDate;
import java.util.List;

public class UsagerClient extends WebServiceGatewaySupport {

    public List<UsagerWS> getUsagerClientRequest(){
        GetUsagerRequest usagerRequest = new GetUsagerRequest();
        GetUsagerResponse responseUsager = (GetUsagerResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", usagerRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetUsagerRequest"));

        return responseUsager.getUsagerWS();
    }

    public UsagerWS getUsagerByEmailClientRequest(String email){
        GetUsagerByEmailRequest usagerRequest = new GetUsagerByEmailRequest();
        usagerRequest.setEmail(email);
        GetUsagerByEmailResponse usagerByEmailResponse = (GetUsagerByEmailResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", usagerRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetUsagerByEmailRequest"));

        return usagerByEmailResponse.getUsagerWS();
    }

    public UsagerWS getUsagerCreateClientRequest(String email){
        GetUsagerCreateRequest usagerRequest = new GetUsagerCreateRequest();
        usagerRequest.setEmail(email);

        GetUsagerCreateResponse getUsagerCreateResponse = (GetUsagerCreateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", usagerRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetUsagerCreateRequest"));

        return getUsagerCreateResponse.getUsagerWS();

    }

    public UsagerWS getUsagerByIdClientRequest(int id){
        GetUsagerByIdRequest usagerByIdRequest = new GetUsagerByIdRequest();
        usagerByIdRequest.setId(id);

        GetUsagerByIdResponse usagerByIdResponse = (GetUsagerByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", usagerByIdRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetUsagerByIdRequest"));

        return usagerByIdResponse.getUsagerWS();
    }

    public List<UsagerWS> getUsagerByPretDateClientRequest (){
        GetUsagerByPretDateRequest usagerByPretDateRequest = new GetUsagerByPretDateRequest();
        GetUsagerByPretDateResponse usagerByPretDateResponse = (GetUsagerByPretDateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", usagerByPretDateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetUsagerByPretDateRequest"));

        return usagerByPretDateResponse.getUsagerWS();
    }

    public List<UsagerWS> usagerByRelanceDateClientRequest(){
        GetUsagerByRelanceDateRequest usagerByRelanceDateRequest = new GetUsagerByRelanceDateRequest();
        GetUsagerByRelanceDateResponse usagerByRelanceDateResponse = ( GetUsagerByRelanceDateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", usagerByRelanceDateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetUsagerByRelanceDateRequest"));
        return usagerByRelanceDateResponse.getUsagerWS();
    }

    public List<UsagerWS> getAllUsagerByPretAndRelanceDate(LocalDate date){
        GetUsagerByPretAndRelanceDateRequest usagerByPretAndRelanceDateRequest = new GetUsagerByPretAndRelanceDateRequest();
        usagerByPretAndRelanceDateRequest.setDate(date);
        GetUsagerByPretAndRelanceDateResponse usagerByPretAndRelanceDateResponse = (GetUsagerByPretAndRelanceDateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", usagerByPretAndRelanceDateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetUsagerByRelanceDateRequest"));

        return usagerByPretAndRelanceDateResponse.getUsagerWS();
    }

    public UsagerWS getUsagerUpdateClientRequest(UsagerWS usagerWS){
        GetUsagerUpdateRequest usagerUpdateRequest = new GetUsagerUpdateRequest();
        usagerUpdateRequest.setUsagerWS(usagerWS);

        GetUsagerUpdateResponse usagerUpdateResponse =(GetUsagerUpdateResponse)getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", usagerUpdateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetUsagerByRelanceDateRequest"));

        return usagerUpdateResponse.getUsagerWS();
    }

}
