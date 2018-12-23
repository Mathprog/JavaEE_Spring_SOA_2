package oc.projet.biblio.business.service.impl;

import oc.projet.biblio.business.service.*;
import oc.projet.biblio.model.entity.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySources({ @PropertySource("classpath*:application-test.properties"), @PropertySource("classpath*:log4j.properties")})
@SpringBootTest
@Transactional(propagation = Propagation.REQUIRED)
@ActiveProfiles("test")
@Sql({"classpath:sql/javaee_test_structure.sql","classpath:sql/javaee_test_data.sql"})
public class ServiceImplTest {

    @Autowired
    private UsagerService usagerService;

    @Autowired
    private PretService pretService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private OuvrageService ouvrageService;

    @Autowired
    private RelanceService relanceService;

    /*@Test
     @Rollback(false)
     public void populateBdd() {
         String email = "mathieu-martinez";
         String titre = "Spring Framework";
         Usager usager = usagerService.createUsager(email);
         /*
             Ouvrage non disponible
          * /
        Ouvrage ouvrage = ouvrageService.createOuvrate(titre, "Eh oui il est partout ce petit binoclard.","Harry Potter" ,"sample-1.jpg" , LocalDate.now().minusYears(4) );

        /*
            Ouvrage avec 1 pret et 1 disponible.
         * /
        Ouvrage ouvrage2 = ouvrageService.createOuvrate("Spring Framework 2", "Je suis ton père.","Luc","spring.jpg" , LocalDate.now().minusYears(4));
        Exemplaire exemplaire = exemplaireService.createExemplaire(ouvrage);
        Exemplaire exemplaire2 = exemplaireService.createExemplaire(ouvrage2);
        Exemplaire exemplaire3 = exemplaireService.createExemplaire(ouvrage2);
        Pret pret2 = pretService.createPret(exemplaire2, usager, LocalDate.now(), LocalDate.now().plusWeeks(4));
        Pret pret = pretService.createPret(exemplaire, usager, LocalDate.now(), LocalDate.now().plusWeeks(4));
        Relance relance = relanceService.createRelance(pret, LocalDate.now().plusWeeks(8));

        /*
         * Ouvrage avec deux exemplaires disponibles et pas de pret
         * /
        Ouvrage ouvrageDispo = ouvrageService.createOuvrate("Ouvrage disponible", "La communauté de l'anneau doit être préservée.", "Aragorn","apache-maven.jpg" ,  LocalDate.now().minusYears(4));
        Exemplaire exemplaireDispo = exemplaireService.createExemplaire(ouvrageDispo);
        Exemplaire exemplaireDispo2 = exemplaireService.createExemplaire(ouvrageDispo);

    }*/

    @Test
    @Rollback(false)
    public void findUserByEmail_NotExisting(){
        String email = "mathieu-martin";
        Usager  usagerFound = usagerService.findUsagerByEmail(email);
        assertNull(usagerFound);
        /*usagerFound = usagerService.find(37);
        assertNotNull(usagerFound);*/
    }

    @Test
    @Rollback(false)
    public void createUserByEmail_AlreadyExisting(){
        String email = "mathieu-martinez";
        Usager usager = usagerService.createUsager(email);
        assertNull(usager);
    }

    @Test
    public void Usager_Find_All_Infos(){
        String email = "mathieu-martinez";
        Usager usager = usagerService.findUsagerByEmail(email);
        assertNotNull(usager);
        List<Relance> relances = this.relanceService.findAllByUsager(usager);
        List<Pret> prets = this.pretService.findAllByUsager(usager);

        assertNotNull(relances);
        assertNotNull(prets);
        assertEquals(relances.size(), 1);
        assertEquals(prets.size(), 2);
    }


    @Test
    @Rollback(false)
    public void findUserByEmail_Existing(){
        String email = "mathieu-martinez";
        Usager  usagerFound = usagerService.findUsagerByEmail(email);
        assertEquals(usagerFound.getEmail(), email);
    }

    @Test
    @Rollback(true)
    public void findUserByDate(){
        String email = "mathieu-martinez@gmail.com";
        String titre = "Spring Framework 3";
        Usager usager = usagerService.createUsager(email);
        Ouvrage ouvrage = ouvrageService.createOuvrate(titre,"Je suis ton père.","Luc", LocalDate.now().minusYears(4));
        Exemplaire exemplaire = exemplaireService.createExemplaire(ouvrage);
        Exemplaire exemplairePretLate = exemplaireService.createExemplaire(ouvrage);
        Exemplaire exemplaireRelanceLate = exemplaireService.createExemplaire(ouvrage);
        Pret pret = pretService.createPret(exemplaire, usager, LocalDate.now(), LocalDate.now().plusWeeks(4));
        Pret pretLate = pretService.createPret(exemplairePretLate, usager, LocalDate.now().minusWeeks(5), LocalDate.now().minusWeeks(1));
        Pret pretRelanceLate = pretService.createPret(exemplaireRelanceLate, usager, LocalDate.now(), LocalDate.now().plusWeeks(4));
        Relance relance = relanceService.createRelance(pret, LocalDate.now().plusWeeks(8));
        Relance relanceLate = relanceService.createRelance(pretRelanceLate, LocalDate.now().minusWeeks(1));

        List<Usager> usagerPretLate = this.usagerService.findAllByPretDate();
        assertEquals(usagerPretLate.size(), 1);
        assertEquals(usagerPretLate.get(0).getEmail(), email);
        List<Usager> usagerRelanceLate = this.usagerService.findAllByRelanceDate();
        assertEquals(usagerRelanceLate.size(), 1);
        assertEquals(usagerRelanceLate.get(0).getEmail(), email);
    }

   @Test
    @Rollback(false)
    public void findOuvrages(){
            List<Ouvrage> ouvrages = ouvrageService.findAllOuvrage();
            assertNotNull(ouvrages);

            List<Ouvrage> ouvrageFindByResearch = ouvrageService.findAllByTitreResearch("Spring");
            assertNotNull(ouvrageFindByResearch);
            assertEquals(ouvrageFindByResearch.size(), 2);

            ouvrageFindByResearch = ouvrageService.findAllByTitreResearch("2");
            assertEquals(ouvrageFindByResearch.size(), 1);
    }

   @Test
   @Rollback(false)
    public void findOuvrage_Disponible_NotNull(){
        List<Ouvrage> ouvragesDispo = ouvrageService.findAllWithDispo();
        assertNotNull(ouvragesDispo);
        assertEquals(ouvragesDispo.get(0).getNbDispo(), (Long)1L);
        assertEquals(ouvragesDispo.get(0).getTitre(), "Spring Framework 2");
        assertEquals(ouvragesDispo.get(1).getNbDispo(), (Long)2L);
        assertEquals(ouvragesDispo.get(1).getTitre(), "Ouvrage disponible");
        assertEquals(ouvragesDispo.size(), 2);
    }

   @Test
    public void findOuvrage_NonDisponible_FindAll(){
        List<Ouvrage> ouvragesNonDispo = ouvrageService.findAllWithNoDispo();
        assertEquals(ouvragesNonDispo.size(), 1);
        assertEquals(ouvragesNonDispo.get(0).getTitre(), "Spring Framework");
    }

    @Test
    public void findRelance_FindAll(){
        List<Relance> relances = this.relanceService.findAll();
        assertNotNull(relances);
        assertEquals(relances.size(), 1);
    }

    @Test
    @Rollback(true)
    public void findRelance_ByPret(){
        String email = "mathieu-martinez@gmail.com";
        String titre = "Spring Framework 3";
        Usager usager = usagerService.createUsager(email);
        Ouvrage ouvrage = ouvrageService.createOuvrate(titre, "Je suis ton père.","Luc", LocalDate.now().minusYears(4));
        Exemplaire exemplaire = exemplaireService.createExemplaire(ouvrage);
        Pret pret = pretService.createPret(exemplaire, usager, LocalDate.now(), LocalDate.now().plusWeeks(4));
        Relance relance = relanceService.createRelance(pret, LocalDate.now().plusWeeks(8));

        Relance relanceFound = relanceService.findByPret(pret);
        List<Relance> relancesUsager = relanceService.findAllByUsager(usager);
        assertNotNull(relanceFound);
        assertEquals(relanceFound.getDateFin().toString(), relance.getDateFin().toString());
        assertNotNull(relancesUsager);
        assertEquals(relancesUsager.size(), 1);
    }

    @Test
    @Rollback(true)
    public void findPret_All_ByExemplaire_ByUser(){
        String email = "mathieu-martinez@gmail.com";
        String titre = "Spring Framework 3";
        Usager usager = usagerService.createUsager(email);
        Ouvrage ouvrage = ouvrageService.createOuvrate(titre,"Je suis ton père.","Luc", LocalDate.now().minusYears(4));
        Exemplaire exemplaire = exemplaireService.createExemplaire(ouvrage);
        Pret pret = pretService.createPret(exemplaire, usager, LocalDate.now(), LocalDate.now().plusWeeks(4));

        List<Pret> allPret = pretService.findAll();
        assertEquals(allPret.size(), 3);

        Pret pretByExemplaire = pretService.findByExemplaire(exemplaire);
        assertNotNull(pretByExemplaire);

        List<Pret> pretsByUsager = pretService.findAllByUsager(usager);
        assertEquals(pretsByUsager.size(), 1);
    }

    @Test
    @Rollback(true)
    public void findExemplaire_All_ByPret_ByOuvrage(){
        String email = "mathieu-martinez@gmail.com";
        String titre = "Spring Framework 3";
        Usager usager = usagerService.createUsager(email);
        Ouvrage ouvrage = ouvrageService.createOuvrate(titre, "Je suis ton père.","Luc", LocalDate.now().minusYears(4));
        Exemplaire exemplaire = exemplaireService.createExemplaire(ouvrage);
        Pret pret = pretService.createPret(exemplaire, usager, LocalDate.now(), LocalDate.now().plusWeeks(4));

        List<Exemplaire> exemplaires = this.exemplaireService.findAll();
        assertEquals(exemplaires.size(), 6 );

        List<Exemplaire> exemplairesOuvrage = this.exemplaireService.findAllByBook(ouvrage);
        assertEquals(exemplairesOuvrage.size(), 1);

        List<Exemplaire> exemplairesUsager = this.exemplaireService.findAllByUsager(usager);
        assertEquals(exemplairesUsager.size(), 1);

        Exemplaire exemplairePret = this.exemplaireService.findByPret(pret);
        assertNotNull(exemplairePret);
    }


}