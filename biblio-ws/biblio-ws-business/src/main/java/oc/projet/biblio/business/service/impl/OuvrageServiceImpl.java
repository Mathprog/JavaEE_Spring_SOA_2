package oc.projet.biblio.business.service.impl;

import oc.projet.biblio.business.service.OuvrageService;
import oc.projet.biblio.business.service.PretService;
import oc.projet.biblio.business.service.RelanceService;
import oc.projet.biblio.model.repository.OuvrageRepository;
import oc.projet.biblio.model.entity.Ouvrage;
import oc.projet.biblio.model.repository.PretRepository;
import oc.projet.biblio.model.repository.RelanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        return this.ouvrageRepository.findAllWithNoDispo();
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
        return (pretFirstDate.isAfter(relanceFirstDAte)) ? relanceFirstDAte : pretFirstDate;
    }

    @Override
    public Long countResa(Ouvrage ouvrage){
        return this.ouvrageRepository.countResa(ouvrage);
    }

}
