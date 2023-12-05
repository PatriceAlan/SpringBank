# SpringBank

## Présentation du Projet

Ce projet implique le développement d'une API REST Spring Boot pour la gestion d'un petit système bancaire. 

### Portée du Projet

Le système bancaire peut gérer des clients, leurs comptes et transactions. Les fonctionnalités clés comprennent :

- Informations détaillées sur le client : nom, âge, coordonnées, etc.
- Clients associés à un code bancaire et à un code d'agence.
- Comptes avec soldes, identifiés par IBAN.
- Comptes joints avec plusieurs titulaires dans la même agence.
- Cartes bancaires associées aux comptes, avec des règles spécifiques pour le débit et le crédit.
- Transferts entre les comptes avec des dates d'exécution et des détails sur le bénéficiaire.

![Diagramme de Classe](https://github.com/PatriceAlan/SpringBank/blob/main/diagramDeClasseSpringBank.png?raw=true)