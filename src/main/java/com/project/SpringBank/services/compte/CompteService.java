package com.project.SpringBank.services.compte;

import com.project.SpringBank.entities.*;
import com.project.SpringBank.repositories.CompteRepository;
import com.project.SpringBank.services.client.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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

    public Compte creerCompte(Compte compte, List<Long> titulaireIds) {
        if (compte == null || titulaireIds == null) {
            throw new IllegalArgumentException("Le compte ou la liste des titulaires ne peut pas être nulle");
        }

        if (compte.getIban() == null || compte.getIban().isEmpty()) {
            String iban = genererIBAN(compte);
            compte.setIban(iban);
        }

        int cleRIB = calculerCleRIB(compte);
        compte.setCleRIB(cleRIB);

        Compte nouveauCompte = new Compte(
                compte.getIban(),
                compte.getNumeroCompte(),
                compte.getSolde(),
                compte.getCleRIB(),
                compte.getTypeCompte(),
                new HashSet<>(),
                compte.getIntituleCompte(),
                compte.getDateCreation(),
                compte.getTransactions(),
                compte.getVirementsEmis(),
                compte.getVirementsRecus(),
                compte.getCartes()
        );

        for (Long titulaireId : titulaireIds) {
            Client client = clientService.rechercherClient(titulaireId)
                    .orElseThrow(() -> new IllegalArgumentException("Client non trouvé pour l'ID : " + titulaireId));

            nouveauCompte.getClients().add(client);
        }

        return this.compteRepository.save(nouveauCompte);
    }


    public Compte MettreAJourCompte(Compte compte, List<Long> titulairesIds) {
        if (compte == null || titulairesIds == null) {
            throw new IllegalArgumentException("Le compte ou la liste des titulaires ne peut pas être nulle");
        }

        Optional<Compte> compteExist = compteRepository.findById(compte.getIban());

        if (compteExist.isPresent()) {
            Compte compteExistant = compteExist.get();
            return updateAndSaveCompte(compteExistant, compte, titulairesIds);
        } else {
            return creerCompte(compte, titulairesIds);
        }
    }

    private Compte updateAndSaveCompte(Compte compteExistant, Compte compte, List<Long> titulairesIds) {
        compteExistant.setNumeroCompte(compte.getNumeroCompte());
        compteExistant.setSolde(compte.getSolde());
        compteExistant.setTypeCompte(compte.getTypeCompte());
        compteExistant.setIntituleCompte(compte.getIntituleCompte());
        compteExistant.setDateCreation(compte.getDateCreation());
        compteExistant.setTransactions(compte.getTransactions());
        compteExistant.setVirementsEmis(compte.getVirementsEmis());
        compteExistant.setVirementsRecus(compte.getVirementsRecus());
        compteExistant.setCartes(compte.getCartes());

        // Efface les titulaires existants et ajoute les nouveaux titulaires
        compteExistant.getClients().clear();
        for (Long titulaireId : titulairesIds) {
            // Recherche du client par ID
            Client client = clientService.rechercherClient(titulaireId)
                    .orElseThrow(() -> new IllegalArgumentException("Client non trouvé pour l'ID : " + titulaireId));

            // Ajout du client au compte
            compteExistant.getClients().add(client);
        }

        return this.compteRepository.save(compteExistant);
    }


    private String genererIBAN(Compte compte) {
        return "FR76" + CODE_BANQUE + CODE_GUICHET + compte.getNumeroCompte() + compte.getCleRIB();
    }


    private int calculerCleRIB(Compte compte) {
        String numeroCompteAsString = String.valueOf(compte.getNumeroCompte());
        return 97 - (89 * Integer.parseInt(CODE_BANQUE) + 15 * Integer.parseInt(CODE_GUICHET)
                + 3 * Integer.parseInt(numeroCompteAsString)) % 97;
    }

    public Optional<Compte> rechercherCompte(String iban) {
        if (iban == null) {
            throw new IllegalArgumentException("L'IBAN ne peut pas être nul");
        }
        return compteRepository.findById(iban);
    }

    public List<Compte> listerTousLesComptes() {
        return compteRepository.findAll();
    }

    public List<Compte> trouverComptesParType(TypeCompte typeCompte) {
        return compteRepository.findByTypeCompte(typeCompte);
    }

    public void supprimerCompte(String iban) {
        if (iban == null) {
            throw new IllegalArgumentException("L'IBAN ne peut pas être nul");
        }
        compteRepository.deleteById(iban);
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
