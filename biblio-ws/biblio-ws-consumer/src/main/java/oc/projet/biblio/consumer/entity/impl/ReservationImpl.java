package oc.projet.biblio.consumer.entity.impl;

import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.entity.Usager;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedQueries(
        {
                @NamedQuery(name = ReservationImpl.QN.FIND_ALL,
                        query = "SELECT r FROM ReservationImpl r"),
                @NamedQuery( name = ReservationImpl.QN.FIND_ALL_BY_USAGER,
                        query = "SELECT r FROM ReservationImpl r " +
                                "WHERE r.usager = :usager"),
                @NamedQuery( name = ReservationImpl.QN.FIND_ALL_BY_OUVRAGE,
                        query = "SELECT r FROM ReservationImpl r " +
                                "WHERE r.ouvrage = :ouvrage"),
                @NamedQuery( name = ReservationImpl.QN.FIND_LAST_BY_OUVRAGE,
                        query = "SELECT r FROM ReservationImpl r " +
                                "WHERE r.ouvrage = :ouvrage " +
                                "ORDER BY r.dateReservation DESC "),
                @NamedQuery( name = ReservationImpl.QN.FIND_ALL_DISPOS,
                        query = "SELECt r FROM ReservationImpl r " +
                                "WHERE r.dateLimite IS NOT NULL " +
                                "AND r.dateLimite <= :date")
        }
)
@Entity
@Table(name="reservation")
public class ReservationImpl implements Reservation {


    public static class QN {
        public static final String FIND_ALL = "ReservationImpl.findALL";
        public static final String FIND_ALL_BY_USAGER = "ReservationImpl.findAllByUsager";
        public static final String FIND_ALL_BY_OUVRAGE = "ReservationImpl.findAllByOuvrage";
        public static final String FIND_LAST_BY_OUVRAGE = "ReservationImpl.findLastByOuvrage";
        public static final String FIND_ALL_DISPOS = "ReservationImpl.findAllByDispo";
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = OuvrageImpl.class)
    @JoinColumn(name = "ouvrage_id")
    private Ouvrage ouvrage;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UsagerImpl.class)
    @JoinColumn(name="usager_id", nullable = false)
    private Usager usager;

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    @Column(name = "date_limite")
    private LocalDate dateLimite;


    public ReservationImpl() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    @Override
    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    @Override
    public Usager getUsager() {
        return usager;
    }

    @Override
    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    @Override
    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    @Override
    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Override
    public LocalDate getDateLimite() {
        return dateLimite;
    }

    @Override
    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }
}