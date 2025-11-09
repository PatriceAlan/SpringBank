package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.virement.CreateVirementRequestDTO;
import com.project.SpringBank.DTO.virement.CreateVirementResponseDTO;
import com.project.SpringBank.services.VirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/virements")
@RequiredArgsConstructor
public class VirementController {

    private final VirementService virementService;

    @PostMapping
    public ResponseEntity<CreateVirementResponseDTO> createVirement(@RequestBody CreateVirementRequestDTO virementDTO) {
        CreateVirementResponseDTO createdVirement = virementService.createVirement(virementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVirement);
    }

    @GetMapping
    public ResponseEntity<List<CreateVirementResponseDTO>> listerVirements() {
        List<CreateVirementResponseDTO> virements = virementService.listerVirements();
        return ResponseEntity.ok(virements);
    }
}

