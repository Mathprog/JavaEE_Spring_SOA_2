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
                                "JOIN FETCH r.ouvrage o " +
                                "WHERE r.usager = :usager"),
                @NamedQuery( name = ReservationImpl.QN.FIND_ALL_BY_OUVRAGE,
                        query = "SELECT r FROM ReservationImpl r " +
                                "WHERE r.ouvrage = :ouvrage"),
                @NamedQuery( name = ReservationImpl.QN.FIND_LAST_BY_OUVRAGE,
                        query = "SELECT r FROM ReservationImpl r " +
                                "WHERE r.ouvrage = :ouvrage " +
                                "ORDER BY r.dateReservation DESC "),
                @NamedQuery( name = ReservationImpl.QN.FIND_ALL_ACUTAL_RESA,
                        query = "SELECT r FROM ReservationImpl r " +
                                "JOIN FETCH r.usager u " +
                                "WHERE r.dateLimite IS NOT NULL " +
                                "AND r.dateLimite <= :date"),
                @NamedQuery( name = ReservationImpl.QN.FIND_NEXT_RESA,
                        query = "SELECT r FROM ReservationImpl r " +
                                "JOIN FETCH r.usager u " +
                                "WHERE r.ouvrage = :ouvrage " +
                                "AND r.dateLimite IS NULL " +
                                "ORDER BY r.dateReservation DESC"),
                @NamedQuery( name = ReservationImpl.QN.FIND_BY_USAGER_AND_OUVRAGE,
                            query = "SELECT r FROM ReservationImpl r " +
                                    "WHERE r.usager = :usager " +
                                    "AND r.ouvrage = :ouvrage"),
                @NamedQuery( name = ReservationImpl.QN.FIND_LATE_RESA,
                            query = "SELECT r FROM ReservationImpl r " +
                                    "JOIN FETCH r.usager u " +
                                    "JOIN FETCH r.ouvrage o " +
                                    "WHERE r.dateLimite < :date"),
                @NamedQuery( name = ReservationImpl.QN.CALCUL_PLACE_USAGER,
                            query = "SELECT COUNT (rs) " +
                                    "FROM ReservationImpl rs " +
                                    "WHERE rs.ouvrage = :ouvrage " +
                                    "AND rs.dateReservation <= :date " +
                                    "ORDER BY rs.id ASC") //( SELECT rs2.dateReservation FROM ReservationImpl rs2 WHERE rs2.usager = :usager AND rs2.ouvrage = :ouvrage)
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
        public static final String FIND_ALL_ACUTAL_RESA = "ReservationImpl.findAllByDispo";
        public static final String FIND_NEXT_RESA = "ReservationImpl.findNextResa";
        public static final String FIND_BY_USAGER_AND_OUVRAGE = "ReservationImpl.findByUsagerAndOuvrage";
        public static final String FIND_LATE_RESA = "ReservationImpl.findLateResa";
        public static final String CALCUL_PLACE_USAGER = "ReservationImpl.calculPlaceUsager";
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

    @Transient
    private int usagerPlace;


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

    @Override
    public int getUsagerPlace() {
        return usagerPlace;
    }

    @Override
    public void setUsagerPlace(int usagerPlace) {
        this.usagerPlace = usagerPlace;
    }
}
