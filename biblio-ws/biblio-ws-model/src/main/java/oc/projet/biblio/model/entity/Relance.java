package oc.projet.biblio.model.entity;

import java.time.LocalDate;

public interface Relance {
    int getId();

    void setId(int id);

    LocalDate getDateFin();

    void setDateFin(LocalDate dateFin);

    Pret getPret();

    void setPret(Pret pret);
}
