package oc.projet.biblio.business.service;


import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Usager;

import java.util.List;

public interface ExemplaireService {
    Exemplaire find(int id);

    List<Exemplaire> findAllExemplaire();

    Exemplaire createExemplaire(Ouvrage ouvrage);

    Exemplaire findByPret(Pret pret);

    List<Exemplaire> findAll();

    List<Exemplaire> findAllByBook(Ouvrage ouvrage);

    List<Exemplaire> findAllByUsager(Usager usager);
}
