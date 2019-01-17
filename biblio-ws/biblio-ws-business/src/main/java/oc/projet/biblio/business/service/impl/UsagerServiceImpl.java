package oc.projet.biblio.business.service.impl;

import oc.projet.biblio.business.service.UsagerService;
import oc.projet.biblio.model.repository.UsagerRepository;
import oc.projet.biblio.model.entity.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional(propagation = Propagation.MANDATORY)
public class UsagerServiceImpl implements UsagerService {


    @Autowired
    private UsagerRepository usagerRepository;

    @Override
    public Usager find(int id){
        return this.usagerRepository.find(id);
    }

    @Override
    public Usager createUsager(String email){
        return usagerRepository.createUsager(email);
    }

    @Override
    public Usager findUsagerByEmail(String email) {
        return usagerRepository.findUsagerByEmail(email);
    }

    @Override
    public Usager findUsager_pretsByEmail(String email){
        return usagerRepository.findUsager_pretsByEmail(email);
    }

    @Override
    public Usager findUsager_pretsDetailsByEmail(String email){
        return this.usagerRepository.findUsager_pretsDetailsByEmail(email);
    }

    @Override
    public List<Usager> findAll(){
        return this.usagerRepository.findAll();
    }

    @Override
    public List<Usager> findAllByRelanceDate(){
        return this.usagerRepository.findAllByRelanceDate();
    }

    @Override
    public List<Usager> findAllByPretDate(){
        return this.usagerRepository.findAllByPretDate();
    }


    @Override
    public List<Usager> findAllByPretAndRelanceDate(LocalDate date){
        List<Usager> usagerList = this.usagerRepository.findAllByPret5DayExpiration(date);
        usagerList.addAll(this.usagerRepository.findAllByRelance5DayExpiration(date));
        return usagerList;
    }

    @Override
    public Usager update(Usager usager){
        return this.usagerRepository.update(usager);
    }
}
