package oc.projet.biblio.business.service.impl;

import oc.projet.biblio.business.service.*;
import oc.projet.biblio.model.repository.OuvrageRepository;
import oc.projet.biblio.model.entity.Ouvrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class OuvrageServiceImpl implements OuvrageService {

    @Autowired
    private OuvrageRepository ouvrageRepository;

    @Autowired
    private PretService pretService;

    @Autowired
    private RelanceService relanceService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Override
    public Ouvrage find(int id){
        return this.ouvrageRepository.find(id);
    }

    @Override
    public Ouvrage createOuvrage(String titre, String resume, String auteur, LocalDate publication) {
        return this.ouvrageRepository.create(titre, resume , auteur , publication );
    }

    @Override
    public List<Ouvrage> findAllOuvrage() {
        return ouvrageRepository.findAll();
    }

    @Override
    public List<Ouvrage> findAllWithDispo(){
        return this.ouvrageRepository.findAllWithDispo();
    }

    @Override
    public List<Ouvrage> findAllWithNoDispo(){
        List<Ouvrage> allNoDispo = this.ouvrageRepository.findAllWithNoDispo();
        for (Ouvrage ouvrage: allNoDispo){
            ouvrage.setDateDispo(firstDispoDate(ouvrage));
            ouvrage.setNbReservation(this.countResa(ouvrage));
            ouvrage.setReservations(new HashSet<>(this.reservationService.findAllByOuvrage(ouvrage)));
            ouvrage.setExemplaires(new HashSet<>(this.exemplaireService.findAllByBook(ouvrage)));
            ouvrage.calculReservable();
        }
        return allNoDispo;
    }

    @Override
    public List<Ouvrage> findAllByTitreResearch(String titre){
        return this.ouvrageRepository.findAllOuvrageByResearch(titre);
    }

    @Override
    public Ouvrage findWithExemplairesAndReservations(Ouvrage ouvrage){
        return this.ouvrageRepository.findOuvrageWithExemplairesAndReservations(ouvrage);
    }


    @Override
    public LocalDate firstDispoDate(Ouvrage ouvrage){
        LocalDate pretFirstDate = this.pretService.findFirstDispo(ouvrage);
        LocalDate relanceFirstDAte = this.relanceService.findFirstDispo(ouvrage);
        if( pretFirstDate == null ){
            if( relanceFirstDAte != null){
                return relanceFirstDAte;
            } else {
                return pretFirstDate;
            }
        } else if (relanceFirstDAte != null){
            return (pretFirstDate.isAfter(relanceFirstDAte)) ? relanceFirstDAte : pretFirstDate;
        } else {
            return pretFirstDate;
        }

    }

    @Override
    public int countResa(Ouvrage ouvrage){
        return this.ouvrageRepository.countResa(ouvrage);
    }

}
