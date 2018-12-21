package oc.projet.biblio.client.consumer.ws;

import oc.projet.biblio.client.consumer.generated.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.time.LocalDate;
import java.util.List;


public class RelanceClient extends WebServiceGatewaySupport {

    public List<RelanceWS> getRelanceClientResponse(){
        GetRelanceRequest relanceRequest = new GetRelanceRequest();

        GetRelanceResponse relanceResponse = (GetRelanceResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", relanceRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetRelanceRequest"));

        return relanceResponse.getRelance();
    }

    public RelanceWS getRelanceByIdClientRequest (int id){
        GetRelanceByIdRequest relanceByIdRequest = new GetRelanceByIdRequest();
        relanceByIdRequest.setId(id);

        GetRelanceByIdResponse relanceByIdResponse = (GetRelanceByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", relanceByIdRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetRelanceByIdRequest"));

        return relanceByIdResponse.getRelance();
    }

    public RelanceWS getRelanceCreateClientRequest (PretWS pretWS, LocalDate datFin){
        GetRelanceCreateRequest relanceCreateRequest = new GetRelanceCreateRequest();
        relanceCreateRequest.setPret(pretWS);
        relanceCreateRequest.setDateFin(datFin);

        GetRelanceCreateResponse relanceCreateResponse = (GetRelanceCreateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", relanceCreateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetRelanceCreateRequest"));

        return relanceCreateResponse.getRelance();
    }

    public List<RelanceWS> relanceByUsagerClientRequest (UsagerWS usagerWS){
        GetRelanceByUsagerRequest relanceByUsagerRequest = new GetRelanceByUsagerRequest();
        relanceByUsagerRequest.setUsager(usagerWS);

        GetRelanceByUsagerResponse relanceByUsagerResponse = (GetRelanceByUsagerResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", relanceByUsagerRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetRelanceByUsagerRequest"));

        return relanceByUsagerResponse.getRelance();
    }

    public RelanceWS getRelanceByPretClientRequest(PretWS pretWS){
        GetRelanceByPretRequest relanceByPretRequest = new GetRelanceByPretRequest();
        relanceByPretRequest.setPret(pretWS);

        GetRelanceByPretResponse relanceByPretResponse = (GetRelanceByPretResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", relanceByPretRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetRelanceByPretRequest"));

        return relanceByPretResponse.getRelance();
    }
}
