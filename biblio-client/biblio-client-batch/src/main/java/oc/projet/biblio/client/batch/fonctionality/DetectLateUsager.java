package oc.projet.biblio.client.batch.fonctionality;

public interface DetectLateUsager {
    void sendEMail();

    void sendMailAlert();

    void sendMailReservationAlert();

    void deleteLateReservation();
}
