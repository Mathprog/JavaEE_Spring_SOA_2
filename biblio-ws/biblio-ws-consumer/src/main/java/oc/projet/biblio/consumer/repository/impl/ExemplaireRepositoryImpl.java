package oc.projet.biblio.consumer.repository.impl;


import oc.projet.biblio.model.entity.*;
import oc.projet.biblio.model.repository.ExemplaireRepository;
import oc.projet.biblio.consumer.entity.impl.ExemplaireImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ExemplaireRepositoryImpl implements ExemplaireRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Exemplaire find(int id){
        return this.entityManager.find(ExemplaireImpl.class, id);
    }

    @Override
    public Exemplaire createExemplaire(Ouvrage ouvrage){
            Exemplaire exemplaire = new ExemplaireImpl();
            exemplaire.setOuvrage(ouvrage);
            entityManager.persist(exemplaire);
            return exemplaire;
    }

    @Override
    public Exemplaire findByPret(Pret pret){
        Exemplaire exemplaire = null;
        try{
            exemplaire = entityManager.createNamedQuery(ExemplaireImpl.QN.FIND_BY_PRET, Exemplaire.class).setParameter("pret", pret).getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
        return exemplaire;
    }

    @Override
    public List<Exemplaire> findAll(){
        return this.entityManager.createNamedQuery(ExemplaireImpl.QN.FIND_ALL, Exemplaire.class).getResultList();
    }

    @Override
    public List<Exemplaire> findAllByOuvrage(Ouvrage ouvrage){
        return this.entityManager.createNamedQuery(ExemplaireImpl.QN.FIND_ALL_BY_BOOKS, Exemplaire.class).setParameter("ouvrage", ouvrage).getResultList();
    }

    @Override
    public List<Exemplaire> findAllByUsager(Usager usager){
        return this.entityManager.createNamedQuery(ExemplaireImpl.QN.FIND_ALL_BY_USAGER, Exemplaire.class).setParameter("usager", usager).getResultList();
    }
}
