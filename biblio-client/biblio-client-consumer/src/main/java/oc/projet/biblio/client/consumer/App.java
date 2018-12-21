package oc.projet.biblio.client.consumer;

import oc.projet.biblio.client.consumer.generated.*;

import oc.projet.biblio.client.consumer.ws.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Configuration
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);

    }

   /* @Bean
    CommandLineRunner lookup(UsagerClient quoteClient) {
        return args -> {
            String email_create = "mathieu-martinez-client3";
            GetUsagerCreateResponse responseCreateUsager = quoteClient.getUsagerCreateClientRequest(email_create);
            System.err.println("Usager crée avec l'email : " + email_create + " / Usager id = " +
                    responseCreateUsager.getUsagerWS().getId() + " email = " +
                    responseCreateUsager .getUsagerWS().getEmail());
            GetUsagerResponse response = quoteClient.getUsagerClientRequest();
            System.err.println("Nombre d'usagers dans la BDD : " + response.getUsagerWS().size());

            int id = responseCreateUsager.getUsagerWS().getId();
            GetUsagerByIdResponse responseUsagerById = quoteClient.getUsagerByIdClientRequest(id);
            System.err.println("Usager trouvé avec l'id : " + id + " email = " + responseUsagerById.getUsagerWS().getEmail());

            String email = "mathieu-martinez";
            GetUsagerByEmailResponse responseUsagerByEmail = quoteClient.getUsagerByEmailClientRequest(email);
            System.err.println("Usager trouvé avec l'email : " + email + " / Usager id = " + responseUsagerByEmail.getUsagerWS().getId() + " email = " + responseUsagerByEmail.getUsagerWS().getEmail());



        };
    }

    @Bean
    CommandLineRunner lookup2(OuvrageClient quoteClient) {
        return args -> {

            String titre_create = "Client Spring Framework SOAP";
            GetOuvrageCreateResponse ouvrageCreateResponse = quoteClient.getOuvrageCreateClientRequest(titre_create);
            System.err.println(" Ouvrage crée : id : " + ouvrageCreateResponse.getOuvrageWS().getId() + " titre : " + ouvrageCreateResponse.getOuvrageWS().getTitre());

            GetOuvrageResponse response = quoteClient.geOuvrageClientRequest();
            System.err.println("Nombre d'ouvrages dans la BDD : " + response.getOuvrageWS().size());

            int id = ouvrageCreateResponse.getOuvrageWS().getId();
            GetOuvrageByIdResponse responseOuvrageById = quoteClient.getOuvrageByIdClientRequest(id);
            System.err.println("Ouvrage trouvé avec l'id : " + id + " titre = " + responseOuvrageById.getOuvrageWS().getTitre());

            String titre = "Spring Framework 2";
            GetOuvrageByTitreResponse ouvrageByTitreResponse = quoteClient.getOuvrageByTitreClientRequest(titre);
            System.err.println( "Ouvrage trouvé avec le titre : " + titre + " / Ouvrage id = " + ouvrageByTitreResponse.getOuvrageWS().get(0).getId() + " titre = " + ouvrageByTitreResponse.getOuvrageWS().get(0).getTitre());


        };
    }

    @Bean
    CommandLineRunner lookup3(ExemplaireClient quoteClientExemplaire, OuvrageClient quoteClientOuvrage) {
        return args -> {
            GetExemplaireResponse exemplaireResponse = quoteClientExemplaire.getExemplaireClientRequest();
            System.err.println("Nombre d'exemplaires dans la BDD : " + exemplaireResponse.getExemplaireWS().size());



            String titre = "Spring Framework";
            OuvrageWS ouvrage = quoteClientOuvrage.getOuvrageByTitreClientRequest(titre).getOuvrageWS().get(0);
            GetExemplaireByBookResponse exemplaireByBookResponse = quoteClientExemplaire.getExemplaireByBookCLientRequest(ouvrage);
            System.err.println("Exemplaire trouvé avec l'ouvrage : " + ouvrage.getTitre() + ". Exemplaire id = " + exemplaireByBookResponse.getExemplaireWS().get(0).getId());

            GetExemplaireCreateResponse exemplaireCreateResponse = quoteClientExemplaire.getExemplaireCreateClientRequest(ouvrage);
            System.err.println(" Exemplaire crée : id : " + exemplaireCreateResponse.getExemplaireWS().getId());

            int id = exemplaireCreateResponse.getExemplaireWS().getId();
            GetExemplaireByIdResponse exemplaireByIdResponse = quoteClientExemplaire.getExemplaireByIdCLientRequest(id);
            System.err.println("Exemplaire trouvé avec l'id : " + id + " id = " + exemplaireByIdResponse.getExemplaireWS().getId());



        };
    }

    @Bean
    CommandLineRunner lookup4 (PretClient quoteClientPret, UsagerClient quoteClientUsager, OuvrageClient quoteClientOuvrage, ExemplaireClient quoteClientExemplaire){
        return args -> {
            String email_create = "mathieu-martinez-client4";
            GetUsagerCreateResponse responseCreateUsager = quoteClientUsager.getUsagerCreateClientRequest(email_create);
            System.err.println("Usager crée avec l'email : " + email_create + " / Usager id = " +
                    responseCreateUsager.getUsagerWS().getId() + " email = " +
                    responseCreateUsager .getUsagerWS().getEmail());

            String titre = "Spring Framework";
            OuvrageWS ouvrage = quoteClientOuvrage.getOuvrageByTitreClientRequest(titre).getOuvrageWS().get(0);

            GetExemplaireCreateResponse exemplaireCreateResponse = quoteClientExemplaire.getExemplaireCreateClientRequest(ouvrage);
            System.err.println(" Exemplaire crée : id : " + exemplaireCreateResponse.getExemplaireWS().getId());

            GetPretCreateResponse pretCreateResponse= quoteClientPret.getPretCreateClientRequest(responseCreateUsager.getUsagerWS(), exemplaireCreateResponse.getExemplaireWS(), LocalDate.now(), LocalDate.now().plusWeeks(4));
            System.err.println("Pret Créer : ID = " + pretCreateResponse.getPret().getId() + " de l'usager : " + responseCreateUsager.getUsagerWS().getEmail() +
                    "De l'ouvrage = " + exemplaireCreateResponse.getExemplaireWS().getOuvrage().getTitre() + " Date début : " + pretCreateResponse.getPret().getDatePret().toString()
             + " Date Fin : " + pretCreateResponse.getPret().getDatePret().toString());

            GetPretByUsagerResponse pretByUsagerResponse= quoteClientPret.getPretByUsagerClientRequest(responseCreateUsager.getUsagerWS());
            System.err.println("Pret trouvé avec l'usager : " + responseCreateUsager.getUsagerWS().getEmail() +
                    " Pret : ID = " + pretByUsagerResponse.getPret().get(0).getId());

            GetPretByExemplaireResponse pretByExemplaireResponse = quoteClientPret.getPretByExemplaireClientRequest(exemplaireCreateResponse.getExemplaireWS());
            System.err.println("Pret trouvé avec l'exemplaire : " + pretByExemplaireResponse.getPret().getId() + " Id du pret : " + pretByExemplaireResponse.getPret().getId());


        };
    }

    @Bean
    CommandLineRunner lookup5 (RelanceClient quoteClientRelance, PretClient quoteClientPret, UsagerClient quoteClientUsager, OuvrageClient quoteClientOuvrage, ExemplaireClient quoteClientExemplaire){
        return args -> {
            String email_create = "mathieu-martinez-client5";
            GetUsagerCreateResponse responseCreateUsager = quoteClientUsager.getUsagerCreateClientRequest(email_create);
            System.err.println("Usager crée avec l'email : " + email_create + " / Usager id = " +
                    responseCreateUsager.getUsagerWS().getId() + " email = " +
                    responseCreateUsager .getUsagerWS().getEmail());

            String titre = "Spring Framework";
            OuvrageWS ouvrage = quoteClientOuvrage.getOuvrageByTitreClientRequest(titre).getOuvrageWS().get(0);

            GetExemplaireCreateResponse exemplaireCreateResponse = quoteClientExemplaire.getExemplaireCreateClientRequest(ouvrage);
            System.err.println(" Exemplaire crée : id : " + exemplaireCreateResponse.getExemplaireWS().getId());

            GetPretCreateResponse pretCreateResponse= quoteClientPret.getPretCreateClientRequest(responseCreateUsager.getUsagerWS(), exemplaireCreateResponse.getExemplaireWS(), LocalDate.now(), LocalDate.now().plusWeeks(4));
            System.err.println("Pret Créer : ID = " + pretCreateResponse.getPret().getId() + " de l'usager : " + responseCreateUsager.getUsagerWS().getEmail() +
                    "De l'ouvrage = " + exemplaireCreateResponse.getExemplaireWS().getOuvrage().getTitre() + " Date début : " + pretCreateResponse.getPret().getDatePret().toString()
                    + " Date Fin : " + pretCreateResponse.getPret().getDatePret().toString());


            GetRelanceCreateResponse relanceCreateResponse = quoteClientRelance.getRelanceCreateClientRequest(pretCreateResponse.getPret(), LocalDate.now().plusWeeks(4));
            System.err.println("Relance crée : ID = " + relanceCreateResponse.getRelance().getId() + " Date de fin " + relanceCreateResponse.getRelance().getDateFin().toString());

            GetRelanceResponse relanceResponse = quoteClientRelance.getRelanceClientResponse();
            System.err.println("Nombre de relance trouvée dans la BDD : " + relanceResponse.getRelance().size());

            int id = relanceResponse.getRelance().get(0).getId();
            GetRelanceByIdResponse relanceByIdResponse = quoteClientRelance.getRelanceByIdClientRequest(id);
            System.err.println("Relance trouvée avec l'ID : " + relanceByIdResponse.getRelance().getId() + " DateFin : " + relanceByIdResponse.getRelance().getDateFin().toString());



        };
    }*/
}
