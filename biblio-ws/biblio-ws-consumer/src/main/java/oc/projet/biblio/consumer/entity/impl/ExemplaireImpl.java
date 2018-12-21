package oc.projet.biblio.consumer.entity.impl;


import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Pret;


import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(
                name = ExemplaireImpl.QN.FIND_ALL,
                query = "SELECT e FROM ExemplaireImpl e " +
                        "JOIN FETCH e.ouvrage"

        ),
        @NamedQuery(
                name = ExemplaireImpl.QN.FIND_ALL_BY_BOOKS,
                query = "SELECT e FROM ExemplaireImpl e " +
                        "WHERE e.ouvrage = :ouvrage"
        ),
        @NamedQuery(
                name = ExemplaireImpl.QN.FIND_BY_PRET,
                query = "SELECT e FROM ExemplaireImpl e " +
                        "WHERE e.pret = :pret"
        ),
         @NamedQuery(
                 name = ExemplaireImpl.QN.FIND_ALL_BY_USAGER,
                 query = "SELECT e FROM ExemplaireImpl e " +
                         "JOIN e.pret p " +
                         "JOIN p.usager u " +
                         "JOIN FETCH e.ouvrage " +
                         "WHERE u = :usager"
         )
})

@Entity
@Table(name="exemplaire")
public class ExemplaireImpl implements Exemplaire, Serializable {

    public static class QN {
        public static final String FIND_ALL = "ExemplaireImpl.findAll";
        public static final String FIND_ALL_BY_BOOKS = "ExemplaireImpl.findAllByBooks";
        public static final String FIND_BY_PRET = "ExemplaireImpl.findByPret";
        public static final String FIND_ALL_BY_USAGER = "ExemplaireImpl.findAllByUsager";

    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = OuvrageImpl.class)
    @JoinColumn(name = "ouvrage_id")
    private Ouvrage ouvrage;

     /*@OneToOne(fetch = FetchType.LAZY, mappedBy = "exemplaire", cascade = CascadeType.ALL, targetEntity = PretImpl.class)
     private Pret pret;*/

    @OneToOne(fetch = FetchType.LAZY, targetEntity = PretImpl.class, optional = true)
    @JoinColumn(name = "pret_id", nullable = true)
    private Pret pret;


    public ExemplaireImpl() {
    }

    public ExemplaireImpl(Ouvrage ouvrage, Pret pret) {
        this.ouvrage = ouvrage;
        this.pret = pret;
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
    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    @Override
    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
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
