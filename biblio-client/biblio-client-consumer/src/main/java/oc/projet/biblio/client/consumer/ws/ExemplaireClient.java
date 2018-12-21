package oc.projet.biblio.client.consumer.ws;

import oc.projet.biblio.client.consumer.generated.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

public class ExemplaireClient extends WebServiceGatewaySupport {

    public List<ExemplaireWS> getExemplaireClientRequest(){
        GetExemplaireRequest exemplaireRequest = new GetExemplaireRequest();

        GetExemplaireResponse exemplaireResponse = (GetExemplaireResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", exemplaireRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetExemplaireRequest"));
        return exemplaireResponse.getExemplaireWS();
    }

    public ExemplaireWS getExemplaireByIdCLientRequest(int id){
        GetExemplaireByIdRequest exemplaireByIdRequest = new GetExemplaireByIdRequest();
        exemplaireByIdRequest.setId(id);

        GetExemplaireByIdResponse  exemplaireByIdResponse = (GetExemplaireByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", exemplaireByIdRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetExemplaireByIdRequest"));

        return exemplaireByIdResponse.getExemplaireWS();
    }

    public List<ExemplaireWS> getExemplaireByBookCLientRequest(OuvrageWS ouvrageWS){
        GetExemplaireByBookRequest exemplaireByBookRequest = new GetExemplaireByBookRequest();
        exemplaireByBookRequest.setBook(ouvrageWS);

        GetExemplaireByBookResponse getExemplaireByBookResponse = (GetExemplaireByBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", exemplaireByBookRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetExemplaireByBookRequest"));

        return getExemplaireByBookResponse.getExemplaireWS();
    }

    public ExemplaireWS getExemplaireCreateClientRequest (OuvrageWS ouvrageWS){
        GetExemplaireCreateRequest getExemplaireCreateRequest = new GetExemplaireCreateRequest();
        getExemplaireCreateRequest.setOuvrage(ouvrageWS);

        GetExemplaireCreateResponse getExemplaireCreateResponse = (GetExemplaireCreateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", getExemplaireCreateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetExemplaireCreateRequest"));

        return getExemplaireCreateResponse.getExemplaireWS();
    }

    public List<ExemplaireWS> getExemplaireByUsager(UsagerWS usagerWS){
        GetExemplaireByUsagerRequest exemplaireByUsagerRequest = new GetExemplaireByUsagerRequest();
        exemplaireByUsagerRequest.setUsager(usagerWS);

        GetExemplaireByUsagerResponse getExemplaireByUsagerResponse = (GetExemplaireByUsagerResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", exemplaireByUsagerRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetExemplaireByUsagerRequest"));

        return getExemplaireByUsagerResponse.getExemplaireWS();

    }

    public ExemplaireWS getExemplaireByPret(PretWS pretWS){
        GetExemplaireByPretRequest exemplaireByPretRequest = new GetExemplaireByPretRequest();
        exemplaireByPretRequest.setPret(pretWS);

        GetExemplaireByPretResponse relanceByPretResponse = (GetExemplaireByPretResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", exemplaireByPretRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetExemplaireByPretRequest"));

        return relanceByPretResponse.getExemplaireWS();
    }
}
