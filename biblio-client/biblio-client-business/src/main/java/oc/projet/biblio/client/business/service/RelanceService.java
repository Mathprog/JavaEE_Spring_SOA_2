package oc.projet.biblio.client.business.service;



import oc.projet.biblio.client.consumer.generated.PretWS;
import oc.projet.biblio.client.consumer.generated.RelanceWS;
import oc.projet.biblio.client.consumer.generated.UsagerWS;

import java.time.LocalDate;
import java.util.List;

public interface RelanceService {

    RelanceWS find(int id);

    RelanceWS createRelance(PretWS pret, LocalDate date_fin);

    List<RelanceWS> findAll();

    RelanceWS findByPret(PretWS pret);

    List<RelanceWS> findAllByUsager(UsagerWS usager);

    List<RelanceWS> findAllByUsagerAndDate(UsagerWS usagerWS, LocalDate date);
}
