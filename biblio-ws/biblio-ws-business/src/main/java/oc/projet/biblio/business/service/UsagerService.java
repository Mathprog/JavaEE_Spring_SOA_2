package oc.projet.biblio.business.service;

import oc.projet.biblio.model.entity.Usager;

import java.time.LocalDate;
import java.util.List;

public interface UsagerService {

    Usager find(int id);

    Usager createUsager(String email);
    Usager findUsagerByEmail(String email);
    Usager findUsager_pretsByEmail(String email);
    Usager findUsager_pretsDetailsByEmail(String email);

    List<Usager> findAll();

    List<Usager> findAllByRelanceDate();

    List<Usager> findAllByPretDate();

    List<Usager> findAllByPretAndRelanceDate(LocalDate date);

    Usager update(Usager usager);
}
