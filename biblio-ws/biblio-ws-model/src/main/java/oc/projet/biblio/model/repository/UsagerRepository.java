package oc.projet.biblio.model.repository;

import oc.projet.biblio.model.entity.Usager;

import java.util.List;

public interface UsagerRepository {

   Usager findUsagerByEmail(String email);

    Usager find(int id);

    Usager createUsager(String email);
    Usager findUsager_pretsByEmail(String email);

    Usager findUsager_pretsDetailsByEmail(String email);

    List<Usager> findAll();


  List<Usager> findAllByRelanceDate();

  List<Usager> findAllByPretDate();
}
