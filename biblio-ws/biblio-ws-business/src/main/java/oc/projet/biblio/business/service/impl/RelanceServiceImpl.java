package oc.projet.biblio.business.service.impl;

import oc.projet.biblio.business.service.RelanceService;
import oc.projet.biblio.model.entity.Usager;
import oc.projet.biblio.model.repository.RelanceRepository;
import oc.projet.biblio.model.entity.Pret;
import oc.projet.biblio.model.entity.Relance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class RelanceServiceImpl implements RelanceService {

    @Autowired
    private RelanceRepository relanceRepository;

    @Override
    public Relance find(int id){
        return this.relanceRepository.find(id);
    }

    @Override
    public Relance createRelance(Pret pret, LocalDate date_fin) {
        Relance relance ;
        if( pret.getDateFin().isAfter(LocalDate.now())){
            relance = null;
        } else {
            relance = this.relanceRepository.create(pret, date_fin);
        }
        return relance;
    }

    @Override
    public List<Relance> findAll(){
        return this.relanceRepository.findALl();
    }

    @Override
    public Relance findByPret(Pret pret){
        return this.relanceRepository.findByPret(pret);
    }

    @Override
    public List<Relance> findAllByUsager(Usager usager){
        return this.relanceRepository.findAllByUsager(usager);
    }
}
