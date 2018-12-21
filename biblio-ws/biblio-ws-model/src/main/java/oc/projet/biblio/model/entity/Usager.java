package oc.projet.biblio.model.entity;

import java.util.Set;

public interface Usager {
    Integer getId();

    void setId(Integer id);

    String getEmail();

    void setEmail(String email);

    Set<Pret> getPrets();

    void setPrets(Set<Pret> prets);
}
