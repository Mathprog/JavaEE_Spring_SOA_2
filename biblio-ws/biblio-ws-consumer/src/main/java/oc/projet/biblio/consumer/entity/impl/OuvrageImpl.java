package oc.projet.biblio.consumer.entity.impl;

import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Reservation;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "ouvrage")
@NamedQueries({
        @NamedQuery(
                name = OuvrageImpl.QN.FIND_ALL,
                query = "SELECT o FROM OuvrageImpl o"
        ),
        @NamedQuery(
                name=OuvrageImpl.QN.FIND_ALL_DISPO,
                query="SELECT o, COUNT(e)" +
                        "FROM OuvrageImpl o " +
                        "JOIN o.exemplaires e " +
                        "WHERE e.pret IS NULL " +
                        "GROUP BY o "+
                        "HAVING COUNT(e) > 0"
        ),
        @NamedQuery(
                name=OuvrageImpl.QN.FIND_ALL_NOT_DISPO,
                query="SELECT o " +
                        "FROM OuvrageImpl o " +
                        "JOIN o.exemplaires e "+
                        "WHERE e.pret IS NOT NULL "+
                        "GROUP BY o " +
                        "HAVING COUNT(e) = ( " +
                        "SELECT COUNT(e2) " +
                        "FROM ExemplaireImpl e2 " +
                        "JOIN e2.ouvrage o2 " +
                        "WHERE o = o2)"
        ),
        @NamedQuery(
                name = OuvrageImpl.QN.FIND_ALL_BY_RESEARCH,
                query = "SELECT o, COUNT(e) " +
                        "FROM OuvrageImpl o " +
                        "LEFT JOIN o.exemplaires e " +
                        "WHERE o.titre LIKE :liketitre " +
                        "GROUP BY o"
        ),
        @NamedQuery(
                name=OuvrageImpl.QN.FIND_ALL_DISPO_BY_RESEARCH,
                query="SELECT o, COUNT(e)" +
                        "FROM OuvrageImpl o " +
                        "JOIN o.exemplaires e " +
                        "WHERE e.pret IS NULL AND o.titre LIKE :liketitre " +
                        "GROUP BY o "+
                        "HAVING COUNT(e) > 0"
        ),
        @NamedQuery(
                name=OuvrageImpl.QN.FIND_ALL_NOT_DISPO_BY_RESEARCH,
                query="SELECT o " +
                        "FROM OuvrageImpl o " +
                        "JOIN o.exemplaires e "+
                        "WHERE e.pret IS NOT NULL AND o.titre LIKE :liketitre "+
                        "GROUP BY o " +
                        "HAVING COUNT(e) = ( " +
                        "SELECT COUNT(e2) " +
                        "FROM ExemplaireImpl e2 " +
                        "JOIN e2.ouvrage o2 " +
                        "WHERE o = o2)"
        ),
        @NamedQuery(
                name = OuvrageImpl.QN.FIND_OUVRAGE_EXEMPLAIRES_RESERVATIONS,
                query = "SELECT o FROM OuvrageImpl o " +
                        "JOIN FETCH o.exemplaires es " +
                        "JOIN FETCH o.reservations rs " +
                        "WHERE o.id = :ouvrage_id"
        ),
        @NamedQuery(
                name = OuvrageImpl.QN.COUNT_RESA,
                query = "SELECT COUNT(rs) FROM OuvrageImpl o " +
                        "JOIN o.reservations rs " +
                        "WHERE o = :ouvrage"
        )
})
public class OuvrageImpl implements Ouvrage, Serializable {

    public static class QN {
        public static final String FIND_ALL = "OuvrageImpl.findAll";
        public static final String FIND_ALL_DISPO = "OuvrageImpl.findAllWithDispo";
        public static final String FIND_ALL_NOT_DISPO = "OuvrageImpl.findAllWithNoDispo";
        public static final String FIND_ALL_BY_RESEARCH = "OuvrageImpl.findAllByResearch";
        public static final String FIND_ALL_DISPO_BY_RESEARCH = "OuvrageImpl.findAllWithDispoBySearch";
        public static final String FIND_ALL_NOT_DISPO_BY_RESEARCH = "OuvrageImpl.findAllWithNoDispoByResearch";
        public static final String FIND_OUVRAGE_EXEMPLAIRES_RESERVATIONS = "OuvrageImpl.findOuvrageWithExemplairesAndReservations";
        public static final String COUNT_RESA = "OuvrageImpl.CountResa";
    }


    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="titre", length = 255)
    @Size(min=2, max = 255)
    private String titre;

    @Column(name="auteur", length = 80)
    @Size(min=2, max = 80)
    private String auteur;

    @Column(name = "resume", length = 500)
    @Size(min = 2, max = 500)
    private String resume;

    @Column(name = "date")
    @Past
    private LocalDate date;

    @Transient
    private Long nbDispo = 0L;

    @Transient
    private boolean isReservable;

    @Transient
    private LocalDate dateDispo;

    @Transient
    private Long nbReservation;

    @Lob
    @Column(name = "imageb", columnDefinition="BLOB")
    private byte[] imageb;

    @OneToMany(mappedBy = "ouvrage", fetch = FetchType.LAZY, targetEntity = ExemplaireImpl.class)
    private Set<Exemplaire> exemplaires;

    @OneToMany(mappedBy = "ouvrage", fetch = FetchType.LAZY, targetEntity = ReservationImpl.class)
    private Set<Reservation> reservations;

    public OuvrageImpl() {
    }

    public OuvrageImpl(String titre, String auteur, String resume, LocalDate date, Long nbDispo) {
        this.titre = titre;
        this.auteur = auteur;
        this.resume = resume;
        this.date = date;
        this.nbDispo = nbDispo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public Long getNbDispo() {
        return nbDispo;
    }

    @Override
    public void setNbDispo(Long nbDispo) {
        this.nbDispo = nbDispo;
    }

    @Override
    public Set<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    @Override
    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    @Override
    public String getAuteur() {
        return auteur;
    }

    @Override
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public String getResume() {
        return resume;
    }

    @Override
    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public byte[] getImageb() {
        return imageb;
    }

    @Override
    public void setImageb(byte[] imageb) {
        this.imageb = imageb;
    }

    @Override
    public Set<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean isReservable() {
        return isReservable;
    }

    @Override
    public void setReservable(boolean reservable) {
        isReservable = reservable;
    }

    @Override
    public void calculReservable(){
        if(this.reservations == null && this.exemplaires != null){ // S'il n'y a pas de réservation et des exemplaires.
            this.isReservable = true;
        } else if (this.reservations != null && this.exemplaires != null){ // S'il y a des réservations et des exemplaires.
            if( this.reservations.size() >= this.exemplaires.size() * 2){ // Si la condition du nombre maximale de résa est atteinte.
                this.isReservable = false;
            } else {
                this.isReservable = true;
            }
        } else { // Il n'y a pas d'exemplaires disponibles.
            this.isReservable = false;
        }
    }

    @Override
    public LocalDate getDateDispo() {
        return dateDispo;
    }

    @Override
    public void setDateDispo(LocalDate dateDispo) {
        this.dateDispo = dateDispo;
    }

    @Override
    public Long getNbReservation() {
        return nbReservation;
    }

    @Override
    public void setNbReservation(Long nbReservation) {
        this.nbReservation = nbReservation;
    }
}
