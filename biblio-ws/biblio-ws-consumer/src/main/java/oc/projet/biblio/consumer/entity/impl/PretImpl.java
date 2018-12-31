package oc.projet.biblio.consumer.entity.impl;

import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Relance;
import oc.projet.biblio.model.entity.Usager;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(
                name = PretImpl.QN.FIND_ALL,
                query = "SELECT p FROM PretImpl p"
        ),
        @NamedQuery(
                name = PretImpl.QN.FIND_ALL_BY_USAGER,
                query = "SELECT p FROM PretImpl p " +
                        "WHERE p.usager = :usager"
        ),
        @NamedQuery(
                name = PretImpl.QN.FIND_BY_EXEMPLAIRE,
                query = "SELECT p FROM PretImpl p " +
                        "WHERE p.exemplaire = :exemplaire"
        ),
        @NamedQuery(
                name = PretImpl.QN.FIND_BY_USAGER_AND_OUVRAGE,
                query = "SELECT p FROM PretImpl p " +
                        "JOIN p.exemplary e " +
                        "WHERE p.usager = :usager " +
                        "AND e.ouvrage = :ouvrage"
        )
})

@Entity
@Table(name ="pret")
public class PretImpl implements Pret, Serializable {

    public static class QN {
        public static final String FIND_ALL = "PretImpl.findAll";
        public static final String FIND_ALL_BY_USAGER = "PretImpl.findByUsager";
        public static final String FIND_BY_EXEMPLAIRE = "PretImpl.findByExemplaire";
        public static final String FIND_BY_USAGER_AND_OUVRAGE = "PretImpl.findByUsagerAndOuvrage";
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UsagerImpl.class)
    @JoinColumn(name="usager_id", nullable = false)
    private Usager usager;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = ExemplaireImpl.class)
    @JoinColumn(name = "exemplaire_id", nullable = false)
    private Exemplaire exemplaire;

    @OneToOne(mappedBy = "pret",
            fetch = FetchType.LAZY, targetEntity = RelanceImpl.class)
    private Relance relance;

    @Column(name = "date_pret")
    private LocalDate datePret;

    @Column(name ="date_fin")
    private LocalDate dateFin;

    public PretImpl() {
    }

    public PretImpl(Usager usager, Exemplaire exemplaire, Relance relance, LocalDate datePret, LocalDate dateFin) {
        this.usager = usager;
        this.exemplaire = exemplaire;
        this.relance = relance;
        this.datePret = datePret;
        this.dateFin = dateFin;
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
    public Usager getUsager() {
        return usager;
    }

    @Override
    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    @Override
    public LocalDate getDatePret() {
        return datePret;
    }

    @Override
    public void setDatePret(LocalDate datePret) {
        this.datePret = datePret;
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
    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    @Override
    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    @Override
    public Relance getRelance() {
        return relance;
    }

    @Override
    public void setRelance(Relance relance) {
        this.relance = relance;
    }
}
