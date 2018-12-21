package oc.projet.biblio.model.repository;

import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Usager;

import java.util.List;

public interface ExemplaireRepository {

    Exemplaire find(int id);

    Exemplaire createExemplaire(Ouvrage ouvrage);

    Exemplaire findByPret(Pret pret);

    List<Exemplaire> findAll();

    List<Exemplaire> findAllByOuvrage(Ouvrage ouvrage);

    List<Exemplaire> findAllByUsager(Usager usager);
}
