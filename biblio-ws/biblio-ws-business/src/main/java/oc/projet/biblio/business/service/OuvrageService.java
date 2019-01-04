package oc.projet.biblio.business.service;


import oc.projet.biblio.model.entity.Ouvrage;

import java.time.LocalDate;
import java.util.List;

public interface OuvrageService {

    List<Ouvrage> findAllOuvrage();

    Ouvrage find(int id);

    Ouvrage createOuvrage(String titre, String resume, String auteur, LocalDate publication);
    List<Ouvrage> findAllWithDispo();

    List<Ouvrage> findAllWithNoDispo();

    List<Ouvrage> findAllByTitreResearch(String titre);

    Ouvrage findWithExemplairesAndReservations(Ouvrage ouvrage);
}
