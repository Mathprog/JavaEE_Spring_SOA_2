package oc.projet.biblio.model.repository;

import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.entity.Usager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {


    List<Reservation> findAll();

    List<Reservation> findAllByOuvrage(Ouvrage ouvrage);

    List<Reservation> findAllByUsager(Usager usager);

    Reservation findLastByOuvrage(Ouvrage ouvrage);

    List<Reservation> findAllDispos();

    Reservation findByUsagerAndOuvrage(Usager usager, Ouvrage ouvrage);

    Reservation create(Usager usager, Ouvrage ouvrage, LocalDateTime creationDate);

    Reservation delete(Reservation reservation);

    Reservation updateDateLimite(Reservation reservation);

    Reservation update(Reservation reservation);

    Reservation findNextResa(Ouvrage ouvrage);

    List<Reservation> findAllLateResa(LocalDate date);

    int calculUsagerPosition(Usager usager, Ouvrage ouvrage);
}
