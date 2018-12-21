package oc.projet.biblio.consumer.repository.impl;

import oc.projet.biblio.model.repository.UsagerRepository;
import oc.projet.biblio.model.entity.Usager;
import oc.projet.biblio.consumer.entity.impl.UsagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UsagerRepositoryImpl implements UsagerRepository {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Usager find(int id){
        return this.entityManager.find(UsagerImpl.class, id);
    }


    @Override
    public Usager createUsager(String email) {
        Usager testExists = this.findUsagerByEmail(email);
        if(testExists == null){
            Usager usager = new UsagerImpl();
            usager.setEmail(email);
            this.entityManager.persist(usager);
            return usager;
        } else {
            return null;
        }

    }

    @Override
    public Usager findUsagerByEmail(String email) {
        Usager usager = null;
        try {
            usager = entityManager.createNamedQuery(UsagerImpl.QN.FIND_BY_EMAIL, Usager.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
        return usager;
    }

    @Override
    public Usager findUsager_pretsByEmail(String email){
        Usager usager = null;
        try {
            usager = entityManager.createNamedQuery(UsagerImpl.QN.FIND_ALL_PRETS, Usager.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
        return usager;
    }

    @Override
    public Usager findUsager_pretsDetailsByEmail(String email){
        Usager usager = null;
        try {
            usager = entityManager.createNamedQuery(UsagerImpl.QN.FIND_PRETS_DETAILS, Usager.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
            return usager;
    }

    @Override
    public List<Usager> findAll(){
        return this.entityManager.createNamedQuery(UsagerImpl.QN.FIND_ALL, Usager.class).getResultList();
    }

    @Override
    public List<Usager> findAllByRelanceDate(){
        return this.entityManager.createNamedQuery(UsagerImpl.QN.FIND_ALL_BY_RELANCE_DATE, Usager.class).getResultList();
    }

    @Override
    public List<Usager> findAllByPretDate(){
        return this.entityManager.createNamedQuery(UsagerImpl.QN.FIND_ALL_BY_PRET_DATE, Usager.class).getResultList();
    }
}
