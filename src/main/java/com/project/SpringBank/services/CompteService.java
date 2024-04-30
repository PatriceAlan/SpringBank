package com.project.SpringBank.services;

import com.project.SpringBank.DTO.compte.CreateCompteDTO;
import com.project.SpringBank.DTO.compte.ResponseCompteDTO;
import com.project.SpringBank.entities.*;
import com.project.SpringBank.repositories.CompteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Builder
public class CompteService {

    private final CompteRepository compteRepository;
    private final ClientService clientService;

    private static final String CODE_BANQUE = "30003";
    private static final String CODE_GUICHET = "02054";

    public CompteService(CompteRepository compteRepository, ClientService clientService) {
        this.compteRepository = compteRepository;
        this.clientService = clientService;
    }

    public Compte createCompte(CreateCompteDTO createCompteDTO) {

        Compte compte = Compte.builder()
                .numeroCompte(createCompteDTO.getNumeroCompte())
                .solde(createCompteDTO.getSolde())
                .cleRIB(calculerCleRIB(createCompteDTO))
                .typeCompte(createCompteDTO.getTypeCompte())
                .titulaireCompte(new HashSet<>(createCompteDTO.getTitulaireCompte()))
                .intituleCompte(createCompteDTO.getIntituleCompte())
                .dateCreation(LocalDate.now())
                .iban(genererIBAN(createCompteDTO))
                .build();

        return compteRepository.save(compte);
    }


    public ResponseCompteDTO mapCompteToResponseDTO(Compte compte){
        return ResponseCompteDTO.builder()
                .iban(compte.getIban())
                .numeroCompte(compte.getNumeroCompte())
                .typeCompte(compte.getTypeCompte().name())
                .titulaireCompte(compte.getTitulaireCompte())
                .intituleCompte(compte.getIntituleCompte())
                .dateCreation(compte.getDateCreation())
                .build();
    }

    public List<ResponseCompteDTO> listComptes(){
        List<Compte> comptes = compteRepository.findAll();
        return comptes.stream()
                .map(this::mapCompteToResponseDTO)
                .toList();
    }

    private String genererIBAN(CreateCompteDTO compte) {
        return "FR76" + CODE_BANQUE + CODE_GUICHET + compte.getNumeroCompte() + compte.getCleRIB();
    }


    private int calculerCleRIB(CreateCompteDTO compte) {
        String numeroCompteAsString = String.valueOf(compte.getNumeroCompte());
        return 97 - (89 * Integer.parseInt(CODE_BANQUE) + 15 * Integer.parseInt(CODE_GUICHET)
                + 3 * Integer.parseInt(numeroCompteAsString)) % 97;
    }


    public List<Transaction> listerTransactionsDuCompte(String iban) {
        if (iban == null) {
            throw new IllegalArgumentException("L'IBAN ne peut pas être nul");
        }

        Optional<Compte> compteOptional = compteRepository.findById(iban);

        if (compteOptional.isPresent()) {
            Compte compte = compteOptional.get();
            return compte.getTransactions();
        } else {
            throw new EntityNotFoundException("Compte non trouvé avec l'IBAN : " + iban);
        }
    }

    public List<Virement> listerVirementsEmisDuCompte(String iban) {
        if (iban == null) {
            throw new IllegalArgumentException("L'IBAN ne peut pas être nul");
        }

        Optional<Compte> compteOptional = compteRepository.findById(iban);

        if (compteOptional.isPresent()) {
            Compte compte = compteOptional.get();
            return compte.getVirementsEmis();
        } else {
            throw new EntityNotFoundException("Compte non trouvé avec l'IBAN : " + iban);
        }
    }

    public List<Virement> listerVirementsRecusDuCompte(String iban) {
        if (iban == null) {
            throw new IllegalArgumentException("L'IBAN ne peut pas être nul");
        }

        Optional<Compte> compteOptional = compteRepository.findById(iban);

        if (compteOptional.isPresent()) {
            Compte compte = compteOptional.get();
            return compte.getVirementsRecus();
        } else {
            throw new EntityNotFoundException("Compte non trouvé avec l'IBAN : " + iban);
        }
    }


}
