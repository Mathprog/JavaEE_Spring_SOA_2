package oc.projet.biblio.client.consumer.ws;

import oc.projet.biblio.client.consumer.generated.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

public class ReservationClient extends WebServiceGatewaySupport {


    public List<ReservationWS> getReservationRequestClient(){
        GetReservationRequest reservationRequest = new GetReservationRequest();

        GetReservationResponse relanceResponse = (GetReservationResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", reservationRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetReservationRequest"));

        return relanceResponse.getReservation();
    }

    public List<ReservationWS> getReservationByOuvrageRequestClient(OuvrageWS ouvrageWS){
        GetReservationByOuvrageRequest reservationByOuvrageRequest = new GetReservationByOuvrageRequest();
        reservationByOuvrageRequest.setOuvrage(ouvrageWS);

        GetReservationByOuvrageResponse reservationByOuvrageResponse = (GetReservationByOuvrageResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", reservationByOuvrageRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetReservationByOuvrageRequestt"));

        return reservationByOuvrageResponse.getReservation();
    }

    public List<ReservationWS> getReservationByUsagerRequestClient(UsagerWS usagerWS){
        GetReservationByUsagerRequest reservationByUsagerRequest = new GetReservationByUsagerRequest();
        reservationByUsagerRequest.setUsager(usagerWS);

        GetReservationByUsagerResponse reservationByUsagerResponse = (GetReservationByUsagerResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", reservationByUsagerRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetReservationByUsagerRequest"));

        return reservationByUsagerResponse.getReservation();
    }

    public ReservationWS getReservationByUsagerAdnOuvrageRequestClient(UsagerWS usagerWS, OuvrageWS ouvrageWS){
        GetReservationByUsagerAndOuvrageRequest reservationByUsagerAndOuvrageRequest = new GetReservationByUsagerAndOuvrageRequest();
        reservationByUsagerAndOuvrageRequest.setUsager(usagerWS);
        reservationByUsagerAndOuvrageRequest.setOuvrage(ouvrageWS);

        GetReservationByUsagerAndOuvrageResponse reservationByUsagerAndOuvrageResponse = (GetReservationByUsagerAndOuvrageResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", reservationByUsagerAndOuvrageRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetReservationByUsagerAndOuvrageRequest"));

        return reservationByUsagerAndOuvrageResponse.getReservation();
    }

    public ReservationWS getCreateReservationRequestClient(UsagerWS usagerWS, OuvrageWS ouvrageWS){
        GetReservationCreateRequest reservationCreateRequest = new GetReservationCreateRequest();

        reservationCreateRequest.setUsager(usagerWS);
        reservationCreateRequest.setOuvrage(ouvrageWS);

        GetReservationCreateResponse reservationCreateResponse = (GetReservationCreateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", reservationCreateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetReservationByUsagerRequest"));

        return reservationCreateResponse.getReservation();
    }

    public ReservationWS getDeleteReservationRequestClient(ReservationWS reservationWS){
        GetReservationDeleteRequest reservationDeleteRequest = new GetReservationDeleteRequest();
        reservationDeleteRequest.setReservation(reservationWS);

        GetReservationDeletesResponse reservationDeletesResponse = (GetReservationDeletesResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", reservationDeleteRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetReservationDeleteRequest"));
        return reservationDeletesResponse.getReservation();
    }

    public List<ReservationWS> getReservationLateRequestClient(){
        GetReservationLateRequest reservationLateRequest = new GetReservationLateRequest();

        GetReservationLateResponse reservationLateResponse = (GetReservationLateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", reservationLateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetReservationDeleteRequest"));

        return reservationLateResponse.getReservation();
    }

    public int getReservationDeleteLateRequestClient(List<ReservationWS> reservationWSList){
        GetReservationDeleteLateRequest reservationDeleteLateRequest = new GetReservationDeleteLateRequest();
        reservationDeleteLateRequest.getReservation().addAll(reservationWSList);

        GetReservationDeleteLateResponse reservationDeleteLateResponse = (GetReservationDeleteLateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", reservationDeleteLateRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetReservationDeleteRequest"));
        return reservationDeleteLateResponse.getNbReservationDeleted();
    }

    public List<ReservationWS> getReservationDispoRequestClient(){
        GetReservationDispoRequest reservationDispoRequest = new GetReservationDispoRequest();

        GetReservationDispoResponse reservationDispoResponse = (GetReservationDispoResponse)  getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/soapws/bibliosoap", reservationDispoRequest,
                        new SoapActionCallback(
                                "http://biblio.io/api/biblio-web-service/GetReservationDeleteRequest"));

        return reservationDispoResponse.getReservation();
    }
}
