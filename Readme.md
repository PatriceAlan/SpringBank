# SpringBank

Projet de Gestion Bancaire avec API REST Spring Boot.

## Objectif

Le but de ce projet est de développer une API REST Spring Boot pour la gestion d'un système bancaire de petite envergure.

## Portée du Projet

Le système bancaire a la capacité de gérer les clients, leurs comptes et les transactions associées. Les différentes entités sont les suivantes :


1. **Client :**
  - Attributs : nom, âge, coordonnées.
  - Relations :
    - Un client peut avoir plusieurs comptes.
    - Un client est associé à un code bancaire et un code d'agence.

2. **Compte :**
  - Attributs : IBAN, solde, intitulé de compte, date de création.
  - Relations :
    - Un compte appartient à un client.
    - Un compte peut avoir plusieurs cartes bancaires.
    - Un compte peut être impliqué dans plusieurs transactions (émetteur ou bénéficiaire).
    - Pour les comptes joints, un compte peut avoir plusieurs titulaires.

3. **Carte Bancaire :**
  - Attributs : règles spécifiques de débit et de crédit.
  - Relations :
    - Une carte est associée à un seul compte.

4. **Transaction :**
  - Attributs : type de transaction, source, date, montant.
  - Relations :
    - Une transaction concerne un seul compte (émetteur ou bénéficiaire).
    - Une transaction est liée à un virement ou un paiementCarte spécifique à travers l'attribut idSource qui représente l'id de soit du virement ou soit du 
    paiementCarte.

5. **Virement :**
  - Attributs : libellé du virement.
  - Relations :
    - Un virement implique deux comptes (compte débiteur et compte créditeur).

### Diagramme de Classe UML

![Diagramme de Classe](https://github.com/PatriceAlan/SpringBank/blob/main/diagramme_de_classe_spring_bank.png?raw=true)

## Structure du Projet

La structure du projet suit une architecture Spring Boot standard, avec des packages distincts pour les contrôleurs, les services, les entités, etc. Les dépendances nécessaires à la persistance des données et aux tests sont correctement configurées dans le fichier `pom.xml`.

## Implémentation

L'API REST est mise en œuvre en suivant les meilleures pratiques de Spring Boot. Une gestion efficace des erreurs, et la sécurisation des opérations sensibles, telles que les transferts et les mises à jour de compte, sont assurées.

## Conclusion

Ce projet vise à fournir une solution robuste et évolutive pour la gestion d'un système bancaire. Les normes de codage, les tests unitaires et une documentation approfondie sont encouragés pour faciliter la maintenance future.
