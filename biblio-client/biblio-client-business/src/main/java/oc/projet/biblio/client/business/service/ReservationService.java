package oc.projet.biblio.client.business.service;

import oc.projet.biblio.client.consumer.generated.OuvrageWS;
import oc.projet.biblio.client.consumer.generated.ReservationWS;
import oc.projet.biblio.client.consumer.generated.UsagerWS;

import java.util.List;

public interface ReservationService {
    List<ReservationWS> findAll();

    List<ReservationWS> findAllByOuvrage (OuvrageWS ouvrageWS);

    List<ReservationWS> findAllByUsager(UsagerWS usagerWS);

    ReservationWS findByUsagerAndOuvrage(UsagerWS usagerWS, OuvrageWS ouvrageWS);

    ReservationWS create(UsagerWS usagerWS, OuvrageWS ouvrageWS);

    ReservationWS delete(ReservationWS reservationWS);

    List<ReservationWS> findAllLate();

    int deleteLateRequest(List<ReservationWS> reservationWSList);

    List<ReservationWS> findAllDispo();
}
