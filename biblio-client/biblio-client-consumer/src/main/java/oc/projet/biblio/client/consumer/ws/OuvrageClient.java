package oc.projet.biblio.client.consumer.ws;

import oc.projet.biblio.client.consumer.generated.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.activation.DataHandler;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class OuvrageClient extends WebServiceGatewaySupport {

    public List<OuvrageWS> geOuvrageClientRequest(){
        GetOuvrageRequest ouvrageRequest = new GetOuvrageRequest();

        GetOuvrageResponse ouvrageResponse = (GetOuvrageResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", ouvrageRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetOuvrageRequest"));



        return ouvrageResponse.getOuvrageWS();
    }


    public OuvrageWS getOuvrageByIdClientRequest(int id){
        GetOuvrageByIdRequest ouvrageByIdRequest = new GetOuvrageByIdRequest();
        ouvrageByIdRequest.setId(id);

        GetOuvrageByIdResponse ouvrageByIdResponse = (GetOuvrageByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", ouvrageByIdRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetOuvrageByIdRequest"));

        return ouvrageByIdResponse.getOuvrageWS();
    }


    public OuvrageWS getOuvrageCreateClientRequest(String titre){
        GetOuvrageCreateRequest ouvrageCreateRequest = new GetOuvrageCreateRequest();
        ouvrageCreateRequest.setTitre(titre);

        GetOuvrageCreateResponse ouvrageCreateResponse = (GetOuvrageCreateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", ouvrageCreateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetOuvrageCreateRequest"));

        return ouvrageCreateResponse.getOuvrageWS();
    }

    public List<OuvrageWS> getOuvrageByTitreClientRequest(String titre){
        GetOuvrageByTitreRequest ouvrageByTitreRequest = new GetOuvrageByTitreRequest();
        ouvrageByTitreRequest.setTitre(titre);

        GetOuvrageByTitreResponse ouvrageByTitreResponse = (GetOuvrageByTitreResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", ouvrageByTitreRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetOuvrageByTitreRequest"));

        return ouvrageByTitreResponse.getOuvrageWS();
    }

    public List<OuvrageWS> getOuvrageByDispo(){
        GetOuvrageByDispoRequest ouvrageByDispoRequest = new GetOuvrageByDispoRequest();

        GetOuvrageByDispoResponse ouvrageByNoDispoResponse = (GetOuvrageByDispoResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", ouvrageByDispoRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetOuvrageByDispoRequest"));

        return ouvrageByNoDispoResponse.getOuvrageWS();
    }

    public List<OuvrageWS> getOuvrageByNoDispo(){
        GetOuvrageByNoDispoRequest ouvrageByNoDispoRequest = new GetOuvrageByNoDispoRequest();

        GetOuvrageByNoDispoResponse ouvrageByNoDispoResponse = (GetOuvrageByNoDispoResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", ouvrageByNoDispoRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetOuvrageByNoDispoRequest"));
        return ouvrageByNoDispoResponse.getOuvrageWS();
    }

}
