package oc.projet.biblio.model.repository;

import oc.projet.biblio.model.entity.Ouvrage;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface OuvrageRepository {

    Ouvrage find(int id);

    List<Ouvrage> findAll();
    Ouvrage create(String nom, String resume, String auteur, LocalDate publication);
    List<Ouvrage> findAllWithDispo();

    @Transactional(readOnly = true)
    List<Ouvrage> findAllWithNoDispo();

    List<Ouvrage> findAllOuvrageByResearch(String titre);

    Ouvrage findOuvrageWithExemplairesAndReservations(Ouvrage ouvrage);

    Long countResa(Ouvrage ouvrage);
}
