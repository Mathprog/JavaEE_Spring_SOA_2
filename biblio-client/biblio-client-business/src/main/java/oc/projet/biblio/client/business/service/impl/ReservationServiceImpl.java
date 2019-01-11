package oc.projet.biblio.client.business.service.impl;

import oc.projet.biblio.client.business.service.ReservationService;
import oc.projet.biblio.client.consumer.generated.OuvrageWS;
import oc.projet.biblio.client.consumer.generated.ReservationWS;
import oc.projet.biblio.client.consumer.generated.UsagerWS;
import oc.projet.biblio.client.consumer.ws.ReservationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationClient reservationClient;


    @Override
    public List<ReservationWS> findAll(){
        return this.reservationClient.getReservationRequestClient();
    }

    @Override
    public List<ReservationWS> findAllByOuvrage(OuvrageWS ouvrageWS){
        return this.reservationClient.getReservationByOuvrageRequestClient(ouvrageWS);
    }

    @Override
    public List<ReservationWS> findAllByUsager(UsagerWS usagerWS){
        return this.reservationClient.getReservationByUsagerRequestClient(usagerWS);
    }

    @Override
    public ReservationWS findByUsagerAndOuvrage(UsagerWS usagerWS, OuvrageWS ouvrageWS){
        return this.reservationClient.getReservationByUsagerAdnOuvrageRequestClient(usagerWS, ouvrageWS);
    }

    @Override
    public ReservationWS create(UsagerWS usagerWS, OuvrageWS ouvrageWS){
        return this.reservationClient.getCreateReservationRequestClient(usagerWS, ouvrageWS);
    }

    @Override
    public ReservationWS delete(ReservationWS reservationWS){
        return this.reservationClient.getDeleteReservationRequestClient(reservationWS);
    }

    @Override
    public List<ReservationWS> findAllLate(){
        return this.reservationClient.getReservationLateRequestClient();
    }

    @Override
    public int deleteLateRequest(List<ReservationWS> reservationWSList){
        return this.reservationClient.getReservationDeleteLateRequestClient(reservationWSList);
    }

}
