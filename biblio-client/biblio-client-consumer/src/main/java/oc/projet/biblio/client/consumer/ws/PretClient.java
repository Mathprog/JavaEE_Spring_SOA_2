package oc.projet.biblio.client.consumer.ws;

import oc.projet.biblio.client.consumer.generated.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.time.LocalDate;
import java.util.List;

public class PretClient extends WebServiceGatewaySupport{

    public List<PretWS> getPretClientResponse(){
        GetPretsRequest exemplaireRequest = new GetPretsRequest();

        GetPretsResponse pretResponse = (GetPretsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", exemplaireRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetPretsRequest"));

        return pretResponse.getPret();
    }

    public PretWS getPretByIdClientRequest(int id){
        GetPretByIdRequest pretByIdRequest = new GetPretByIdRequest();
        pretByIdRequest.setId(id);

        GetPretByIdResponse exemplaireByIdResponse = (GetPretByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", pretByIdRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetPretByIdRequest"));
        return exemplaireByIdResponse.getPret();
    }

    public PretWS getPretCreateClientRequest (UsagerWS usagerWS, ExemplaireWS exemplaireWS, LocalDate datePret, LocalDate dateFin){
        GetPretCreateRequest pretCreateRequest = new GetPretCreateRequest();
        pretCreateRequest.setUsager(usagerWS);
        pretCreateRequest.setExemplaire(exemplaireWS);
        pretCreateRequest.setDatePret(datePret);
        pretCreateRequest.setDateFin(dateFin);

        GetPretCreateResponse pretCreateResponse = (GetPretCreateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", pretCreateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetPretCreateRequest"));
        return pretCreateResponse.getPret();
    }

    public PretWS getPretByExemplaireClientRequest (ExemplaireWS exemplaireWS){
        GetPretByExemplaireRequest exemplaireRequest = new GetPretByExemplaireRequest();
        exemplaireRequest.setExemplaire(exemplaireWS);

        GetPretByExemplaireResponse exemplaireResponse = (GetPretByExemplaireResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", exemplaireRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetPretByExemplaireRequest"));
        return exemplaireResponse.getPret();
    }

    public List<PretWS> getPretByUsagerClientRequest(UsagerWS usagerWS){
        GetPretByUsagerRequest pretByUsagerRequest = new GetPretByUsagerRequest();
        pretByUsagerRequest.setUsager(usagerWS);

        GetPretByUsagerResponse pretByUsagerResponse = (GetPretByUsagerResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", pretByUsagerRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetPretByUsagerRequest"));
        return pretByUsagerResponse.getPret();
    }

    public List<PretWS> getAllPretBysUsagerAndDate(UsagerWS usagerWS, LocalDate date){
        GetPretByUsagerAndDateRequest pretByUsagerAndDateRequest = new GetPretByUsagerAndDateRequest();
        pretByUsagerAndDateRequest.setUsager(usagerWS);
        pretByUsagerAndDateRequest.setDate(date);

        GetPretByUsagerAndDateResponse pretByUsagerAndDateResponse = (GetPretByUsagerAndDateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", pretByUsagerAndDateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetPretByUsagerRequest"));

        return pretByUsagerAndDateResponse.getPret();
    }

}
