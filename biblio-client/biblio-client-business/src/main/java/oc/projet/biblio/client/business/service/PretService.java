package oc.projet.biblio.client.business.service;


import oc.projet.biblio.client.consumer.generated.ExemplaireWS;
import oc.projet.biblio.client.consumer.generated.PretWS;
import oc.projet.biblio.client.consumer.generated.UsagerWS;

import java.time.LocalDate;
import java.util.List;

public interface PretService {

    PretWS find(int id);

    PretWS createPret(ExemplaireWS exemplaire, UsagerWS usager, LocalDate date_pret, LocalDate date_fin);

    List<PretWS> findAll();

    PretWS findByExemplaire(ExemplaireWS e);

    List<PretWS> findAllByUsager(UsagerWS u);
}
