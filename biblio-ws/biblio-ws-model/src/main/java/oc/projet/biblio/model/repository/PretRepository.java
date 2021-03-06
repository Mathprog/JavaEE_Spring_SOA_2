package oc.projet.biblio.model.repository;

import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Usager;

import java.time.LocalDate;
import java.util.List;

public interface PretRepository {

    Pret find(int id);

    Pret create(Exemplaire exemplaire, Usager usager, LocalDate date_pret, LocalDate date_fin);

    List<Pret> findAll();

    Pret findByExemplaire(Exemplaire e);

    List<Pret> findAllByUsager(Usager u);

    Pret findByUsagerAndOuvrage(Usager usager, Ouvrage ouvrage);

    LocalDate findFirstDate(Ouvrage ouvrage);

    List<Pret> findAllByUsagerAndDate(Usager usager, LocalDate localDate);
}
