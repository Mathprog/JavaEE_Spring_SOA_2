package oc.projet.biblio.client.batch;

import oc.projet.biblio.client.batch.fonctionality.DetectLateUsager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */


@SpringBootApplication(scanBasePackages = "oc.projet.biblio.client")
@EnableScheduling
@Configuration
public class App 
{

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner lookup(DetectLateUsager detectLateUsager) {
        return args -> {
                System.err.println("Activation de la fonctionalité d'envoie de mail :");
                detectLateUsager.sendEMail();
            System.err.println("Détection des prêts arrivant à expiration. ");
                detectLateUsager.sendMailAlert();

                System.err.println("Détection des réservation à venir récupérer..");
                detectLateUsager.sendMailReservationAlert();

                System.err.println("\n\n Suppression des réservations en retard.");
                detectLateUsager.deleteLateReservation();

        };
    }
}
