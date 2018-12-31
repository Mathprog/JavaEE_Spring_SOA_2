package oc.projet.biblio.business.service.impl;

import oc.projet.biblio.business.service.ReservationService;
import oc.projet.biblio.model.entity.Reservation;
import oc.projet.biblio.model.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(propagation = Propagation.MANDATORY)
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public List<Reservation> findAll(){
        return this.reservationRepository.findAll();
    }

}
