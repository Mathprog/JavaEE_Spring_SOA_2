package oc.projet.biblio.business.service;

import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Usager;

import java.time.LocalDate;
import java.util.List;

public interface PretService {

    Pret find(int id);

    Pret createPret(Exemplaire exemplaire, Usager usager, LocalDate date_pret, LocalDate date_fin);

    List<Pret> findAll();

    Pret findByExemplaire(Exemplaire e);

    List<Pret> findAllByUsager(Usager u);

    Pret findByUsagerAndOuvrage(Usager usager, Ouvrage ouvrage);
}
