package oc.projet.biblio.consumer.entity.impl;


import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.entity.Usager;
import oc.projet.biblio.model.entity.Pret;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name ="usager")
@NamedQueries({
        @NamedQuery(
                name = UsagerImpl.QN.FIND_ALL,
                query = "SELECT u FROM UsagerImpl u"
        ),
        @NamedQuery(
                name= UsagerImpl.QN.FIND_BY_EMAIL,
                query="SELECT u FROM UsagerImpl u " +
                        "WHERE u.email = :email"
        ),
        @NamedQuery(
                name = UsagerImpl.QN.FIND_ALL_PRETS,
                query ="SELECT u FROM UsagerImpl u " +
                        "JOIN FETCH u.prets p " +
                        "WHERE u.email = :email"
        ),
        @NamedQuery(
                name = UsagerImpl.QN.FIND_PRETS_DETAILS ,
                query ="SELECT u FROM UsagerImpl u " +
                        "JOIN FETCH u.prets p " +
                        "JOIN FETCH u.reservations rs " +
                        "JOIN FETCH p.relance r " +
                        "JOIN FETCH p.exemplaire e " +
                        "JOIN FETCH e.ouvrage o " +
                        "WHERE u.email = :email"
        ),
        @NamedQuery(
                name= UsagerImpl.QN.FIND_ALL_BY_RELANCE_DATE,
                query = "SELECT distinct u FROM UsagerImpl u " +
                        "JOIN u.prets p " +
                        "JOIN p.relance r " +
                        "WHERE r.dateFin < current_date"
        ),
        @NamedQuery(
                name = UsagerImpl.QN.FIND_ALL_BY_PRET_DATE,
                query = "SELECT distinct u FROM UsagerImpl u " +
                        "JOIN u.prets p " +
                        "WHERE p.dateFin < current_date " +
                        "AND NOT EXISTS (SELECT r2 FROM RelanceImpl r2 " +
                        "JOIN r2.pret p2 " +
                        "WHERE p2.id = p.id)"
        ),
        @NamedQuery(
                name = UsagerImpl.QN.FIND_ALL_BY_PRET_5_EXPIRATION,
                query = "SELECT distinct u FROM UsagerImpl u " +
                        "JOIN u.prets p " +
                        "WHERE (p.dateFin BETWEEN current_date AND :date) " +
                        "AND u.pretExpiration = TRUE " +
                        "AND NOT EXISTS (SELECT r2 FROM RelanceImpl r2 " +
                        "JOIN r2.pret p2 " +
                        "WHERE p2.id = p.id)"
        ),
        @NamedQuery(
                name= UsagerImpl.QN.FIND_ALL_BY_RELANCE_5_EXPIRATION,
                query = "SELECT distinct u FROM UsagerImpl u " +
                        "JOIN u.prets p " +
                        "JOIN p.relance r " +
                        "WHERE (r.dateFin BETWEEN current_date AND :date) " +
                        "AND u.pretExpiration = TRUE"
        )

})
public class UsagerImpl implements Usager, Serializable {

    public static class QN {
        public static final String FIND_BY_EMAIL = "UsagerImpl.findByEmail";
        public static final String FIND_ALL_PRETS = "UsagerImpl.findAllPrets";
        public static final String FIND_PRETS_DETAILS = "UsagerImpl.findPretsDetails";
        public static final String FIND_ALL = "UsagerImpl.findAll";
        public static final String FIND_ALL_BY_RELANCE_DATE = "UsagerImpl.findAllByRelanceDate";
        public static final String FIND_ALL_BY_PRET_DATE = "UsagerImpl.findAllByPretDate";
        public static final String FIND_ALL_BY_PRET_5_EXPIRATION = "UsagerImpl.findAllByPret5Expiration";
        public static final String FIND_ALL_BY_RELANCE_5_EXPIRATION = "UsagerImpl.findAllByRelance5Expiration";
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="email", length = 255, unique=true)
    private String email;

    @Column(name = "pret_expiration")
    private boolean pretExpiration;

    @OneToMany(mappedBy = "usager", fetch = FetchType.LAZY, targetEntity = PretImpl.class)
    private Set<Pret> prets;

    @OneToMany(mappedBy = "usager", fetch = FetchType.LAZY, targetEntity = ReservationImpl.class)
    private Set<Reservation> reservations;

    public UsagerImpl() {
    }

    public UsagerImpl(String email, Set<Pret> prets) {
        this.email = email;
        this.prets = prets;
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
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Set<Pret> getPrets() {
        return prets;
    }

    @Override
    public void setPrets(Set<Pret> prets) {
        this.prets = prets;
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
    public boolean isPretExpiration() {
        return pretExpiration;
    }

    @Override
    public void setPretExpiration(boolean pretExpiration) {
        this.pretExpiration = pretExpiration;
    }
}
