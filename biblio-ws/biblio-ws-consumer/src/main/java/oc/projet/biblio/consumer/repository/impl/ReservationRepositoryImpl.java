package oc.projet.biblio.consumer.repository.impl;

import oc.projet.biblio.consumer.entity.impl.ReservationImpl;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.entity.Usager;
import oc.projet.biblio.model.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Reservation> findAll(){
        return this.entityManager.createNamedQuery(ReservationImpl.QN.FIND_ALL, Reservation.class).getResultList();
    }

    @Override
    public List<Reservation> findAllByOuvrage(Ouvrage ouvrage){
        return this.entityManager.createNamedQuery(ReservationImpl.QN.FIND_ALL_BY_OUVRAGE, Reservation.class).setParameter("ouvrage", ouvrage).getResultList();
    }

    @Override
    public List<Reservation> findAllByUsager(Usager usager){
        return this.entityManager.createNamedQuery(ReservationImpl.QN.FIND_ALL_BY_USAGER, Reservation.class).setParameter("usager", usager).getResultList();
    }

    @Override
    public Reservation findLastByOuvrage(Ouvrage ouvrage){
        Reservation reservation;
        try {
            reservation = this.entityManager.createNamedQuery(ReservationImpl.QN.FIND_LAST_BY_OUVRAGE, Reservation.class).setParameter("ouvrage", ouvrage).setMaxResults(1).getSingleResult();
        } catch (NoResultException nre){
            reservation = null;
        }
        return  reservation;
    }

    @Override
    public List<Reservation> findAllDispos(){
        return this.entityManager.createNamedQuery(ReservationImpl.QN.FIND_ALL_DISPOS, Reservation.class).setParameter("date", LocalDate.now().plusDays(2L)).getResultList();
    }

    @Override
    public Reservation create(Usager usager, Ouvrage ouvrage){
        Reservation reservation = null;
        List<Reservation> reservationList = this.findAllByOuvrage(ouvrage);
        reservation = new ReservationImpl();
        reservation.setDateReservation(LocalDateTime.now());
        reservation.setOuvrage(ouvrage);
        reservation.setUsager(usager);
        if (reservationList == null){
            reservation.setDateLimite(LocalDate.now().plusDays(2));
        } else {
            reservation.setDateLimite(null);
        }
        return reservation;
    }

    @Override
    public Reservation delete(Reservation reservation){
        this.entityManager.remove(reservation);
        return reservation;
    }

    @Override
    public Reservation updateDateLimite(Reservation reservation){
        reservation.setDateLimite(LocalDate.now().plusDays(2));
        this.entityManager.merge(reservation);
        return reservation;
    }

    @Override
    public Reservation findNextResa(Ouvrage ouvrage){
        Reservation reservation;
        try {
            reservation = this.entityManager.createNamedQuery(ReservationImpl.QN.FIND_NEXT_RESA, Reservation.class).setParameter("ouvrage", ouvrage).setMaxResults(1).getSingleResult();
        } catch (NoResultException nre){
            reservation = null;
        }
        return reservation;
    }

}
