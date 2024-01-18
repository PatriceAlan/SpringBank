package com.project.SpringBank.services.virement;

import com.project.SpringBank.repositories.VirementRepository;

public class VirementService {

    private VirementRepository virementRepository;

    public VirementService(VirementRepository virementRepository) {
        this.virementRepository = virementRepository;
    }

}
