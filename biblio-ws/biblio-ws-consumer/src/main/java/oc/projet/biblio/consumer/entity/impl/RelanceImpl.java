package oc.projet.biblio.consumer.entity.impl;

import oc.projet.biblio.model.entity.Relance;
import oc.projet.biblio.model.entity.Pret;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@NamedQueries({
        @NamedQuery(
                name = RelanceImpl.QN.FIND_ALL,
                query = "SELECT r FROM RelanceImpl r"
        ),
        @NamedQuery(
                name = RelanceImpl.QN.FIND_ALL_BY_PRET,
                query = "SELECT r FROM RelanceImpl r " +
                        "JOIN r.pret p " +
                        "WHERE p = :p"
        ),
        @NamedQuery(
                name = RelanceImpl.QN.FIND_ALL_BY_USAGER,
                query = "SELECT r FROM RelanceImpl r " +
                        "JOIN r.pret p " +
                        "JOIN p.usager u " +
                        "WHERE u = :usager"
        ),
        @NamedQuery(
                name = RelanceImpl.QN.FIND_FIRST_DISPO_DATE,
                query = "SELECT MIN(r.dateFin) FROM RelanceImpl r " +
                        "JOIN r.pret p " +
                        "JOIN p.exemplaire e " +
                        "JOIN e.ouvrage o " +
                        "WHERE o = :ouvrage"
        ),
        @NamedQuery(
                name = RelanceImpl.QN.FIND_ALL_BY_USAGER_AND_DATE,
                query = "SELECT r FROM RelanceImpl r " +
                        "JOIN r.pret p " +
                        "JOIN p.usager u " +
                        "WHERE u = :usager " +
                        "AND (r.dateFin BETWEEN current_date AND :date)"
        )
})
@Entity
@Table(name= "relance")
public class RelanceImpl implements Relance, Serializable {

    public static class QN {
        public static final String FIND_ALL = "RelanceImpl.findAll";
        public static final String FIND_ALL_BY_PRET = "RelanceImpl.findAllByPret";
        public static final String FIND_ALL_BY_USAGER = "RelanceImpl.findAllByUsager";
        public static final String FIND_FIRST_DISPO_DATE = "RelanceImpl.findFirstDispoDate";
        public static final String FIND_ALL_BY_USAGER_AND_DATE = "RelanceImpl.findAllByUsagerAndDate";
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = PretImpl.class)
    @JoinColumn(name = "pret_id")
    private Pret pret;

    public RelanceImpl() {
    }

    public RelanceImpl(LocalDate dateFin, Pret pret) {
        this.dateFin = dateFin;
        this.pret = pret;
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
    public LocalDate getDateFin() {
        return dateFin;
    }

    @Override
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public Pret getPret() {
        return pret;
    }

    @Override
    public void setPret(Pret pret) {
        this.pret = pret;
    }
}
