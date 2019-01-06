package oc.projet.biblio.business.service.impl;

import oc.projet.biblio.business.service.PretService;
import oc.projet.biblio.business.service.ReservationService;
import oc.projet.biblio.model.entity.*;
import oc.projet.biblio.model.repository.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class PretServiceImpl implements PretService {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ReservationService reservationService;

    @Override
    public Pret find(int id){
        return pretRepository.find(id);
    }

    @Override
    public Pret createPret(Exemplaire exemplaire, Usager usager, LocalDate date_pret, LocalDate date_fin) {
        Reservation reservation = this.reservationService.findByUsagerAndOuvrage(usager, exemplaire.getOuvrage());
        if( reservation != null ){

        }
        Pret pret = this.pretRepository.create(exemplaire, usager, date_pret, date_fin);
        if( pret != null ){
            exemplaire.setPret(pret);
        }
        return pret;
    }

    @Override
    public List<Pret> findAll(){
        return this.pretRepository.findAll();
    }

    @Override
    public Pret findByExemplaire(Exemplaire e){
        return this.pretRepository.findByExemplaire(e);
    }

    @Override
    public List<Pret> findAllByUsager(Usager u){
        return this.pretRepository.findAllByUsager(u);
    }

    @Override
    public Pret findByUsagerAndOuvrage(Usager usager, Ouvrage ouvrage){
        return this.pretRepository.findByUsagerAndOuvrage(usager, ouvrage);
    }

    @Override
    public LocalDate findFirstDispo(Ouvrage ouvrage){
        return this.pretRepository.findFirstDate(ouvrage);
    }

}
