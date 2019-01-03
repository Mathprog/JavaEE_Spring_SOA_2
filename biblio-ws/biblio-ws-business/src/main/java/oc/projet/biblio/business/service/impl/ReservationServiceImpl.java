package oc.projet.biblio.business.service.impl;

import oc.projet.biblio.business.service.PretService;
import oc.projet.biblio.business.service.ReservationService;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.entity.Usager;
import oc.projet.biblio.model.repository.ReservationRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(propagation = Propagation.MANDATORY)
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PretService pretService;

    @Override
    public List<Reservation> findAll(){
        return this.reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findAllByOuvrage(Ouvrage ouvrage){
        return this.reservationRepository.findAllByOuvrage(ouvrage);
    }

    @Override
    public List<Reservation> findAllByUsager(Usager usager){
        return this.reservationRepository.findAllByUsager(usager);
    }



    @Override
    public Reservation create(Usager usager, Ouvrage ouvrage){
        Pret pret = this.pretService.findByUsagerAndOuvrage(usager, ouvrage); // On récupére un Pret potentiellement existant entre l'usager et l'ouvrage.
        Reservation reservationFound = this.reservationRepository.findByUsagerAndOuvrage(usager, ouvrage);
        Hibernate.initialize(ouvrage.getExemplaires());
        Hibernate.initialize(ouvrage.getReservations());
        ouvrage.calculReservable();
        Reservation reservation;
        if( pret == null && reservationFound == null && ouvrage.isReservable()){ // S'il n'y a pas de prêt et que les conditions de réservations de l'ouvrage sont bonnes.
            reservation = this.reservationRepository.create(usager, ouvrage);
        } else {
            reservation = null;
        }
        return reservation;
    }

    @Override
    public Reservation delete(Reservation reservation, Ouvrage ouvrage){
        return this.reservationRepository.delete(reservation);
    }

    @Override
    public Reservation updateDateLimiteNextResa(Ouvrage ouvrage){
        Reservation nextReservation = this.reservationRepository.findNextResa(ouvrage);
        this.reservationRepository.updateDateLimite(nextReservation);
        return  nextReservation;
    }
}
