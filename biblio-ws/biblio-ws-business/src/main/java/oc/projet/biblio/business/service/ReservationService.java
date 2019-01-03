package oc.projet.biblio.business.service;

import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.entity.Usager;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();

    List<Reservation> findAllByOuvrage(Ouvrage ouvrage);

    List<Reservation> findAllByUsager(Usager usager);

    Reservation create(Usager usager, Ouvrage ouvrage);

    Reservation delete(Reservation reservation, Ouvrage ouvrage);

    Reservation updateDateLimiteNextResa(Ouvrage ouvrage);
}
