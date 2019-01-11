package oc.projet.biblio.business.service;

import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.entity.Usager;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();

    List<Reservation> findAllByOuvrage(Ouvrage ouvrage);

    List<Reservation> findAllByUsager(Usager usager);

    Reservation findByUsagerAndOuvrage(Usager usager, Ouvrage ouvrage);

    Reservation create(Usager usager, Ouvrage ouvrage, LocalDateTime creationDate);

    Reservation delete(Reservation reservation);

    Reservation updateDateLimiteNextResa(Ouvrage ouvrage);

    int deleteLateResa(List<Reservation> reservationLlateList);

    List<Reservation> findAllLateResa();

    Reservation update(Reservation reservation);

    Reservation deleteAndUpdateDateLimiteNextResa(Reservation reservation, Ouvrage ouvrage);

    int calculateUsagerPlace(Usager usager, Ouvrage ouvrage);
}
