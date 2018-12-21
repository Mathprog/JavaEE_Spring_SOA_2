package oc.projet.biblio.model.entity;

import java.time.LocalDate;

public interface Pret {
    int getId();

    void setId(int id);

    Usager getUsager();

    void setUsager(Usager usager);

    LocalDate getDatePret();

    void setDatePret(LocalDate datePret);

    LocalDate getDateFin();

    void setDateFin(LocalDate dateFin);

    Exemplaire getExemplaire();

    void setExemplaire(Exemplaire exemplaire);

    Relance getRelance();

    void setRelance(Relance relance);
}
