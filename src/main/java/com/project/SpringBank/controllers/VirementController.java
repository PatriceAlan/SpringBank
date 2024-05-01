package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.virement.CreateVirementDTO;
import com.project.SpringBank.DTO.virement.ResponseVirementDTO;
import com.project.SpringBank.entities.Virement;
import com.project.SpringBank.services.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/virements")
public class VirementController {

    private final VirementService virementService;

    @Autowired
    public VirementController(VirementService virementService) {
        this.virementService = virementService;
    }

    @PostMapping
    public ResponseEntity<ResponseVirementDTO> createVirement(@RequestBody CreateVirementDTO virementDTO) {
        ResponseVirementDTO createdVirement = virementService.createVirement(virementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVirement);
    }

    @GetMapping
    public ResponseEntity<List<ResponseVirementDTO>> listerVirements() {
        List<ResponseVirementDTO> virements = virementService.listerVirements();
        return ResponseEntity.ok(virements);
    }
}

