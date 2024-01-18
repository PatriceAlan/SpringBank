package com.project.SpringBank.services.paiement;

import com.project.SpringBank.entities.Paiement;
import com.project.SpringBank.repositories.PaiementRepository;

public class PaiementService {

    private PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }



}
