package oc.projet.biblio.consumer.repository.impl;

import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Usager;
import oc.projet.biblio.model.repository.RelanceRepository;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Relance;
import oc.projet.biblio.consumer.entity.impl.RelanceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.time.LocalDate;
import java.util.List;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class RelanceRepositoryImpl implements RelanceRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Relance find(int id){
        return this.entityManager.find(RelanceImpl.class, id);
    }

    @Override
    public Relance create(Pret pret, LocalDate date_fin) {
        Relance relance = this.findByPret(pret);
        if( relance == null){
            relance = new RelanceImpl();
            relance.setDateFin(date_fin);
            relance.setPret(pret);
            entityManager.persist(relance);
        } else {
            return null; // Ici on doit envoyer une exception
        }
        return relance;
    }

    @Override
    public List<Relance> findALl(){
        return this.entityManager.createNamedQuery(RelanceImpl.QN.FIND_ALL, Relance.class).getResultList();
    }

    @Override
    public Relance findByPret(Pret pret){
        Relance relance = null;
        try {
            relance = this.entityManager.createNamedQuery(RelanceImpl.QN.FIND_ALL_BY_PRET, Relance.class).setParameter("p", pret).getSingleResult();

        } catch (NoResultException nre){
            return null;
        }
        return relance;
    }

    @Override
    public List<Relance> findAllByUsager(Usager usager){
        return this.entityManager.createNamedQuery(RelanceImpl.QN.FIND_ALL_BY_USAGER, Relance.class).setParameter("usager", usager).getResultList();
    }

    @Override
    public LocalDate findFirstDispoDate(Ouvrage ouvrage){
        LocalDate date;
        try {
            date = this.entityManager.createNamedQuery(RelanceImpl.QN.FIND_FIRST_DISPO_DATE, LocalDate.class).setParameter("ouvrage", ouvrage).setMaxResults(1).getSingleResult();
        } catch (NoResultException nre){
            date = null;
        }
        return date;
    }
}
