package oc.projet.biblio.model.repository;

import oc.projet.biblio.model.entity.Exemplaire;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Usager;

import java.time.LocalDate;
import java.util.List;

public interface PretRepository {

    Pret find(int id);

    Pret create(Exemplaire exemplaire, Usager usager, LocalDate date_pret, LocalDate date_fin);

    List<Pret> findall();

    Pret findByExemplaire(Exemplaire e);

    List<Pret> findAllByUsager(Usager u);
}
