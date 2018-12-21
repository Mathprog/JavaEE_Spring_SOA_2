package oc.projet.biblio.model.entity;

public interface Exemplaire {
    Integer getId();

    void setId(Integer id);

    Ouvrage getOuvrage();

    void setOuvrage(Ouvrage ouvrage);

    Pret getPret();

    void setPret(Pret pret);
}
