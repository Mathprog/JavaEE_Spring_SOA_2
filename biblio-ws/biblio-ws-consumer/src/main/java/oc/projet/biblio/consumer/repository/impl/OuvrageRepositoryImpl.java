package oc.projet.biblio.consumer.repository.impl;

import oc.projet.biblio.model.repository.OuvrageRepository;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.consumer.entity.impl.OuvrageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class OuvrageRepositoryImpl implements OuvrageRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Ouvrage find(int id){
        return this.entityManager.find(OuvrageImpl.class, id);
    }

    @Override
    public List<Ouvrage> findAll() {
        return entityManager.createNamedQuery(OuvrageImpl.QN.FIND_ALL, Ouvrage.class).getResultList();
    }

    @Override
    public Ouvrage create(String titre, String resume, String auteur, LocalDate publication) {
        Ouvrage ouvrage = new OuvrageImpl();
        ouvrage.setTitre(titre);
        ouvrage.setAuteur(auteur);
        ouvrage.setResume(resume);
        entityManager.persist(ouvrage);
        return ouvrage;
    }

    @Override
    public List<Ouvrage> findAllWithDispo(){
        List<Object[]> ouvrages = entityManager.createNamedQuery(OuvrageImpl.QN.FIND_ALL_DISPO, Object[].class).getResultList();
        return this.utilsCount(ouvrages);
    }

    @Override
    public List<Ouvrage> findAllWithNoDispo(){
        List<Ouvrage> ouvrageList = entityManager.createNamedQuery(OuvrageImpl.QN.FIND_ALL_NOT_DISPO, Ouvrage.class).getResultList();
        for(Ouvrage ouvrage : ouvrageList){
            ouvrage.getReservations();
            ouvrage.getExemplaires();
            ouvrage.calculReservable();
        }
        return ouvrageList;
    }

    @Override
    public List<Ouvrage> findAllOuvrageByResearch(String titre){
        List<Object[]> ouvragesDispos = entityManager.createNamedQuery(OuvrageImpl.QN.FIND_ALL_DISPO_BY_RESEARCH, Object[].class).setParameter("liketitre" , "%" + titre + "%").getResultList();
        List<Ouvrage> ouvragesNotDispos = entityManager.createNamedQuery(OuvrageImpl.QN.FIND_ALL_NOT_DISPO_BY_RESEARCH, Ouvrage.class).setParameter("liketitre" , "%" + titre + "%").getResultList();
        List<Ouvrage> result = utilsCount(ouvragesDispos);
        result.addAll(ouvragesNotDispos);
        return result;
    }

    private List<Ouvrage> utilsCount(List<Object[]> ouvrages){
        List<Ouvrage> ouvragesReturn = new ArrayList<>();
        for (Object[] o : ouvrages){
            Ouvrage ouvrage = (Ouvrage) o[0];
            ouvrage.getExemplaires();
            ouvrage.getReservations();
            ouvrage.calculReservable();
            ouvrage.setNbDispo((Long) o[1]);
            ouvragesReturn.add(ouvrage);
        }
        return ouvragesReturn;
    }
}
