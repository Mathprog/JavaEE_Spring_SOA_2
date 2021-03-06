package oc.projet.biblio.consumer.repository.impl;

import oc.projet.biblio.consumer.entity.impl.ReservationImpl;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.entity.Usager;
import oc.projet.biblio.model.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Math.toIntExact;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
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
    public Reservation findByUsagerAndOuvrage(Usager usager, Ouvrage ouvrage){
        Reservation reservation;
        try {
            reservation = this.entityManager.createNamedQuery(ReservationImpl.QN.FIND_BY_USAGER_AND_OUVRAGE, Reservation.class).setParameter("usager", usager).setParameter("ouvrage", ouvrage).getSingleResult();
        } catch (NoResultException nre){
            reservation = null;
        }
        return  reservation;
    }

    @Override
    public Reservation create(Usager usager, Ouvrage ouvrage, LocalDateTime creationDate){
        Reservation reservation = null;
        List<Reservation> reservationList = this.findAllByOuvrage(ouvrage);
        reservation = new ReservationImpl();
        reservation.setDateReservation(creationDate);
        reservation.setOuvrage(ouvrage);
        reservation.setUsager(usager);
       /* if (reservationList != null && reservationList.size() == 0){
            reservation.setDateLimite(LocalDate.now().plusDays(2));
        } else {
            reservation.setDateLimite(null);
        }*/
        this.entityManager.persist(reservation);
        return reservation;
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
        return this.entityManager.createNamedQuery(ReservationImpl.QN.FIND_ALL_ACUTAL_RESA, Reservation.class).setParameter("date", LocalDate.now().plusDays(3)).getResultList();
    }



    @Override
    public Reservation delete(Reservation reservation){
        this.entityManager.remove(this.entityManager.contains(reservation) ? reservation :this.entityManager.merge(reservation));
        return reservation;
    }

    @Override
    public Reservation updateDateLimite(Reservation reservation){
        reservation.setDateLimite(LocalDate.now().plusDays(2));
        this.entityManager.merge(reservation);
        return reservation;
    }

    @Override
    public Reservation update(Reservation reservation){
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

    @Override
    public List<Reservation> findAllLateResa(LocalDate date){
        return this.entityManager.createNamedQuery(ReservationImpl.QN.FIND_LATE_RESA, Reservation.class).setParameter("date", date).getResultList();
    }

    @Override
    public int calculUsagerPosition(Usager usager, Ouvrage ouvrage){
        return toIntExact(this.entityManager.createNamedQuery(ReservationImpl.QN.CALCUL_PLACE_USAGER, Long.class).setParameter("ouvrage", ouvrage).setParameter("date", this.findByUsagerAndOuvrage(usager, ouvrage).getDateReservation()).getSingleResult());
    }

}
