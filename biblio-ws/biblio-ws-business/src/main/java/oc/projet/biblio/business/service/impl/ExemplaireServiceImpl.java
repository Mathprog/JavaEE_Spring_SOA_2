package oc.projet.biblio.business.service.impl;


import oc.projet.biblio.business.service.ExemplaireService;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Usager;
import oc.projet.biblio.model.repository.ExemplaireRepository;
import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Ouvrage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class ExemplaireServiceImpl implements ExemplaireService {

    private Logger logger = LoggerFactory.getLogger(ExemplaireServiceImpl.class);

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Override
    public Exemplaire find(int id){
        logger.info("Method find() : Finding Exemplaire with id : " + id);
        return this.exemplaireRepository.find(id);
    }

    @Override
    public List<Exemplaire> findAllExemplaire() {
        return this.exemplaireRepository.findAll();
    }

    @Override
    public Exemplaire createExemplaire(Ouvrage ouvrage) {
        logger.info("Method createExemplaire(). Creating Pret with Book: id = "+ ouvrage.getId() + " titre = "+ ouvrage.getTitre());
        return this.exemplaireRepository.createExemplaire(ouvrage);
    }

    @Override
    public Exemplaire findByPret(Pret pret){
        logger.info("Method findBypret() : Using Pret : id = " + pret.getId());
        return this.exemplaireRepository.findByPret(pret);
    }

    @Override
    public List<Exemplaire> findAll(){
        logger.info("Method findAll().");
        return this.exemplaireRepository.findAll();
    }

    @Override
    public List<Exemplaire> findAllByBook(Ouvrage ouvrage){
        logger.info("Method findAllByBook() : With Ouvrage : id = "+ ouvrage.getId() + " titre : " + ouvrage.getTitre());
        return this.exemplaireRepository.findAllByOuvrage(ouvrage);
    }


    @Override
    public List<Exemplaire> findAllByUsager(Usager usager){
        return this.exemplaireRepository.findAllByUsager(usager);
    }
}
