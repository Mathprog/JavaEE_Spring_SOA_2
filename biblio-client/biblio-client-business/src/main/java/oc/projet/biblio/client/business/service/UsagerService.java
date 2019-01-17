package oc.projet.biblio.client.business.service;



import oc.projet.biblio.client.consumer.generated.UsagerWS;

import java.time.LocalDate;
import java.util.List;

public interface UsagerService {

    UsagerWS find(int id);

    UsagerWS createUsager(String email);
    UsagerWS findUsagerByEmail(String email);

    List<UsagerWS> findAll();

    List<UsagerWS> findAllByRelanceDate();

    List<UsagerWS> findAllByPretDate();

    UsagerWS update(UsagerWS usagerWS);

    List<UsagerWS> findAllByPretAndRelanceDate(LocalDate date);
}
