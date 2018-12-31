package oc.projet.biblio.business.service;

import oc.projet.biblio.model.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
}
