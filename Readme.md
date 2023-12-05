# SpringBank

Projet de Gestion Bancaire avec API REST Spring Boot.

## Objectif

Le but de ce projet est de développer une API REST Spring Boot pour la gestion d'un système bancaire de petite envergure.

## Portée du Projet

Le système bancaire a la capacité de gérer les clients, leurs comptes et les transactions associées. Les principales fonctionnalités sont les suivantes :

- **Informations Client :** Enregistrement des détails du client tels que le nom, l'âge, les coordonnées, etc.
- **Gestion des Comptes :**
    - Création de comptes avec soldes, identifiés par IBAN.
    - Prise en charge de comptes joints avec plusieurs titulaires dans la même agence.
- **Cartes Bancaires :**
    - Attribution de cartes bancaires aux comptes.
    - Définition de règles spécifiques pour le débit et le crédit des cartes.
- **Transactions :**
    - Réalisation de transferts entre les comptes.
    - Enregistrement des dates d'exécution et des détails sur le bénéficiaire des transactions.

## Structure du Projet

La structure du projet suit une architecture Spring Boot standard, avec des packages distincts pour les contrôleurs, les services, les entités, etc. Les dépendances nécessaires à la persistance des données et aux tests sont correctement configurées dans le fichier `pom.xml`.

## Implémentation

L'API REST est mise en œuvre en suivant les meilleures pratiques de Spring Boot. Une gestion efficace des erreurs, et la sécurisation des opérations sensibles, telles que les transferts et les mises à jour de compte, sont assurées.

## Conclusion

Ce projet vise à fournir une solution robuste et évolutive pour la gestion d'un système bancaire. Les normes de codage, les tests unitaires et une documentation approfondie sont encouragés pour faciliter la maintenance future.
Ce projet vise à fournir une solution robuste et évolutive pour la gestion d'un système bancaire. Les normes de codage, les tests unitaires et une documentation approfondie sont encouragés pour faciliter la maintenance future.
![Diagramme de Classe](https://github.com/PatriceAlan/SpringBank/blob/main/diagramDeClasseSpringBank.png?raw=true)