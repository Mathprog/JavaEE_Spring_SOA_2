package oc.projet.biblio.model.repository;

import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Relance;
import oc.projet.biblio.model.entity.Usager;

import java.time.LocalDate;
import java.util.List;

public interface RelanceRepository {

    Relance find(int id);

    Relance create(Pret pret, LocalDate date_fin);

    List<Relance> findALl();

    Relance findByPret(Pret pret);

    List<Relance> findAllByUsager(Usager usager);

    LocalDate findFirstDispoDate(Ouvrage ouvrage);

    List<Relance> findAllByUsagerAndDate(Usager usager, LocalDate date);
}
