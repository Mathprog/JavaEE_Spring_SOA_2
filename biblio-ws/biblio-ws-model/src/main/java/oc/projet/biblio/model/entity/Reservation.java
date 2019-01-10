package oc.projet.biblio.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Reservation {
    int getId();

    void setId(int id);

    Ouvrage getOuvrage();

    void setOuvrage(Ouvrage ouvrage);

    Usager getUsager();

    void setUsager(Usager usager);

    LocalDateTime getDateReservation();

    void setDateReservation(LocalDateTime dateReservation);

    LocalDate getDateLimite();

    void setDateLimite(LocalDate dateLimite);

    int getUsagerPlace();

    void setUsagerPlace(int usagerPlace);
}