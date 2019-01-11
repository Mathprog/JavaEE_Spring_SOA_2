package oc.projet.biblio.webapp.controller.serviceWeb;


import io.biblio.api.biblio_web_service.*;
import oc.projet.biblio.business.service.ReservationService;
import oc.projet.biblio.consumer.entity.impl.OuvrageImpl;
import oc.projet.biblio.consumer.entity.impl.ReservationImpl;
import oc.projet.biblio.consumer.entity.impl.UsagerImpl;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.entity.Usager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Endpoint
@Transactional(propagation = Propagation.REQUIRED)
public class ReservationEndPoint {

    private static final String NAMESPACE_URI = "http://biblio.io/api/biblio-web-service";

    @Autowired
    private ReservationService reservationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationRequest")
    @ResponsePayload
    public GetReservationResponse getReservationResponseClient(){
        GetReservationResponse reservationResponse = new GetReservationResponse();
        List<Reservation> reservationList = this.reservationService.findAll();
        reservationResponse.getReservation().addAll(this.populateReservationWSList(reservationList));

        return  reservationResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationByOuvrageRequest")
    @ResponsePayload
    public GetReservationByOuvrageResponse reservationByOuvrageResponseClient(@RequestPayload GetReservationByOuvrageRequest getReservationByOuvrageRequest ){
        GetReservationByOuvrageResponse reservationByOuvrageResponse = new GetReservationByOuvrageResponse();
        Ouvrage ouvrage = new OuvrageImpl();
        BeanUtils.copyProperties(getReservationByOuvrageRequest.getOuvrage(), ouvrage);
        List<Reservation> reservationList = this.reservationService.findAllByOuvrage(ouvrage);
        reservationByOuvrageResponse.getReservation().addAll(this.populateReservationWSList(reservationList));

        return reservationByOuvrageResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationByUsagerRequest")
    @ResponsePayload
    public GetReservationByUsagerResponse reservationByUsagerResponseClient(@RequestPayload GetReservationByUsagerRequest reservationByUsagerRequest){
        GetReservationByUsagerResponse reservationByUsagerResponse = new GetReservationByUsagerResponse();
        Usager usager = new UsagerImpl();
        BeanUtils.copyProperties(reservationByUsagerRequest.getUsager(), usager);
        List<Reservation> reservationList = this.reservationService.findAllByUsager(usager);
        reservationByUsagerResponse.getReservation().addAll(this.populateReservationWSList(reservationList));

        return reservationByUsagerResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationByUsagerAndOuvrageRequest")
    @ResponsePayload
    public GetReservationByUsagerAndOuvrageResponse reservationByUsagerAndOuvrageResponseClient(@RequestPayload GetReservationByUsagerAndOuvrageRequest reservationByUsagerAndOuvrageRequest){
        GetReservationByUsagerAndOuvrageResponse reservationByUsagerAndOuvrageResponse = new GetReservationByUsagerAndOuvrageResponse();
        Ouvrage ouvrage = new OuvrageImpl();
        Usager usager = new UsagerImpl();
        BeanUtils.copyProperties(reservationByUsagerAndOuvrageRequest.getOuvrage(), ouvrage);
        BeanUtils.copyProperties(reservationByUsagerAndOuvrageRequest.getUsager(), usager);
        Reservation reservation = this.reservationService.findByUsagerAndOuvrage(usager, ouvrage);
        ReservationWS reservationWS = this.uniqueReservation(reservation);
        reservationByUsagerAndOuvrageResponse.setReservation(reservationWS);

        return reservationByUsagerAndOuvrageResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationCreateRequest")
    @ResponsePayload
    public GetReservationCreateResponse reservationCreateResponseClient(@RequestPayload GetReservationCreateRequest reservationCreateRequest){
        GetReservationCreateResponse reservationCreateResponse = new GetReservationCreateResponse();
        Ouvrage ouvrage = new OuvrageImpl();
        Usager usager = new UsagerImpl();
        BeanUtils.copyProperties(reservationCreateRequest.getOuvrage(), ouvrage);
        BeanUtils.copyProperties(reservationCreateRequest.getUsager(), usager);
        Reservation reservation = this.reservationService.create(usager, ouvrage, LocalDateTime.now());
        reservationCreateResponse.setReservation(this.uniqueReservation(reservation));
        return reservationCreateResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationDeleteRequest")
    @ResponsePayload
    public GetReservationDeletesResponse reservationDeleteResponseClient (@RequestPayload GetReservationDeleteRequest reservationDeleteRequest){
        GetReservationDeletesResponse reservationDeleteResponse = new GetReservationDeletesResponse();
        Reservation reservation = new ReservationImpl();
        BeanUtils.copyProperties(reservationDeleteRequest.getReservation(), reservation);
        reservation = this.reservationService.delete(reservation);
        reservationDeleteResponse.setReservation(this.uniqueReservation(reservation));
        return reservationDeleteResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationLateRequest")
    @ResponsePayload
    public GetReservationLateResponse reservationLateResponse(){
        GetReservationLateResponse reservationLateResponse = new GetReservationLateResponse();
        List<Reservation> reservationList = this.reservationService.findAllLateResa();
        reservationLateResponse.getReservation().addAll(this.populateReservationWSList(reservationList));
        return  reservationLateResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationDeleteLateRequest")
    @ResponsePayload
    public GetReservationDeleteLateResponse reservationDeleteLateResponseClient(@RequestPayload GetReservationDeleteLateRequest reservationDeleteLateRequest){
        GetReservationDeleteLateResponse reservationDeleteLateResponse = new GetReservationDeleteLateResponse();
        List<ReservationWS> reservationWSList = reservationDeleteLateRequest.getReservation();
        int nbRowDeleted = this.reservationService.deleteLateResa(this.populateReservationList(reservationWSList));
        reservationDeleteLateResponse.setNbReservationDeleted(nbRowDeleted);
        return reservationDeleteLateResponse;
    }

    private List<ReservationWS> populateReservationWSList(List<Reservation> reservationList) {
        List<ReservationWS> reservationWSList = new ArrayList<>();
        for(Reservation reservation: reservationList){
            ReservationWS reservationWS = uniqueReservation(reservation);
            reservationWSList.add(reservationWS);
        }
        return reservationWSList;
    }

    private ReservationWS uniqueReservation(Reservation reservation){
        ReservationWS reservationWS = new ReservationWS();
        OuvrageWS ouvrageWS = new OuvrageWS();
        UsagerWS usagerWS = new UsagerWS();
        BeanUtils.copyProperties(reservation, reservationWS);
        BeanUtils.copyProperties(reservation.getOuvrage(), ouvrageWS);
        BeanUtils.copyProperties(reservation.getUsager(), usagerWS);
        reservationWS.setOuvrage(ouvrageWS);
        reservationWS.setUsager(usagerWS);
        return reservationWS;
    }

    private List<Reservation> populateReservationList(List<ReservationWS> reservationWSList){
        List<Reservation> reservationList = new ArrayList<>();
        for (ReservationWS reservationWS : reservationWSList){
            Reservation reservation = new ReservationImpl();
            BeanUtils.copyProperties(reservationWS, reservation);
            reservationList.add(reservation);
        }
        return reservationList;
    }
}
