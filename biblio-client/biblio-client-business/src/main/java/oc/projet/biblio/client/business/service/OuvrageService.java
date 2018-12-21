package oc.projet.biblio.client.business.service;




import oc.projet.biblio.client.consumer.generated.OuvrageWS;

import java.util.List;

public interface OuvrageService {

    List<OuvrageWS> findAllOuvrage();

    OuvrageWS find(int id);

    OuvrageWS createOuvrate(String titre);
    List<OuvrageWS> findAllWithDispo();

    List<OuvrageWS> findAllWithNoDispo();

    List<OuvrageWS> findAllByTitreResearch(String titre);
}
