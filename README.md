
# ITLens - API REST Survey IT

## Table of Contents
- [Aperçu du Projet](#aperçu-du-projet)
- [Architecture de l'Application](#architecture-de-lapplication)
- [Diagramme de Classe](#diagramme-de-classe)
- [Fonctionnalités](#fonctionnalités)
- [Prise en Main (Getting Started)](#prise-en-main-getting-started)
    - [Prérequis](#prérequis)
    - [Installation](#installation)
    - [Configuration de la Base de Données](#configuration-de-la-base-de-données)
    - [Démarrage de l'Application](#démarrage-de-lapplication)
- [Développement](#développement)
    - [Structure du Code](#structure-du-code)
    - [Gestion des DTOs et Mappings](#gestion-des-dtos-et-mappings)
    - [Gestion des Exceptions](#gestion-des-exceptions)
- [Tests](#tests)
- [Documentation de l'API](#documentation-de-lapi)
- [Annexes](#annexes)

---

## Aperçu du Projet
**ITLens** est une API REST conçue pour la gestion de sondages dans le domaine IT. Cette application permet de créer et gérer des sondages structurés, d’enregistrer les réponses des utilisateurs, et d’obtenir des statistiques en temps réel. Elle est développée avec Spring Boot et utilise une base de données relationnelle pour la persistance des données.

Les utilisateurs peuvent créer des sondages avec des chapitres et des sous-chapitres, ajouter des questions à choix multiple, puis analyser les résultats des sondages de manière détaillée.

---

## Architecture de l'Application
Le projet **ITLens** suit une architecture en couches, favorisant la séparation des responsabilités pour une meilleure maintenabilité et évolutivité.

- **Contrôleurs** : Gèrent les requêtes HTTP et orchestrent les appels vers les services.
- **Services** : Contiennent la logique métier, traitent les données et appliquent les règles de gestion.
- **Repositories** : Interfaces d’accès aux données utilisant Spring Data JPA pour interagir avec la base de données.
- **DTOs et Mappers** : Objets de transfert de données pour structurer les réponses et MapStruct pour le mappage entre les entités et les DTOs.

---

## Diagramme de Classe
Le diagramme de classe représente la structure du modèle de données de l'application, comprenant les entités principales et leurs relations.

![Diagramme de Classe](https://simplonline-v3-prod.s3.eu-west-3.amazonaws.com/media/image/png/itlens-dc-671e96b532997801426173.png)

---

## Fonctionnalités

- **Gestion des Surveys** : Créer, mettre à jour et supprimer des sondages, chapitres, sous-chapitres et questions.
- **Participation aux Surveys** : Enregistrer les réponses des utilisateurs à chaque question.
- **Consultation des Résultats** : Calculer et afficher les résultats en pourcentage pour chaque question d'un sondage.
- **Documentation de l'API** : Swagger pour une documentation interactive et testable de l'API.

---

## Getting Started

### Prérequis
- **Java 17 ou supérieur**
- **Maven** pour la gestion des dépendances (maven 3.9+)
- **PostgreSQL ou MySQL** pour la base de données

### Installation
1. Clonez le dépôt du projet sur votre machine locale :
   ```bash
   git clone https://github.com/username/itlens.git
   cd itlens
   ```

2. Compilez le projet avec Maven :
   ```bash
   mvn clean install
   ```

### Configuration de la Base de Données
1. **Créez une base de données** (par exemple `itlens_db`) dans PostgreSQL ou MySQL.

2. **Modifiez le fichier de configuration** dans `src/main/resources/application.properties` avec vos informations de connexion :
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/itlens_db
   spring.datasource.username=<username>
   spring.datasource.password=<password>
   spring.jpa.hibernate.ddl-auto=update
   ```

### Démarrage de l'Application
1. Démarrez l’application :
   ```bash
   mvn spring-boot:run
   ```

2. L’API est accessible sur `http://localhost:8080`.

---

## Développement

### Structure du Code
Le projet est structuré en plusieurs packages principaux :
ITLens
````
ITLens
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── youcode
│   │   │           └── itlens
│   │   │               ├── common                    # Dossier commun
│   │   │               ├── owner                     # Gestion des propriétaires
│   │   │               └── survey                    # Gestion des enquêtes
│   │   └── resources                                 # Ressources (fichiers de configuration, etc.)
│   └── test
└── pom.xml                                           # Configuration Maven
````

### Gestion des DTOs et Mappings
Le mappage entre les entités et les DTOs est géré par **MapStruct**. Cela permet une conversion automatique des données pour isoler les entités de la base de données des échanges de données via l'API publique.

### Gestion des Exceptions
L'application utilise `@RestControllerAdvice` pour gérer globalement les exceptions. Les validations d’entrée, comme la vérification de l'existence d'entités (annotation `@Exists`), sont implémentées pour chaque endpoint afin d'assurer l'intégrité des données.

---

## Tests

### Tests Unitaires et d'Intégration
- **Tests unitaires** : Utilisation de JUnit et Mockito pour tester les services et la logique métier.
- **Tests d’intégration** : Vérification de l’interaction entre les couches et de la persistance des données.

Pour exécuter tous les tests, utilisez la commande :
```bash
mvn test
```

### Stratégie de Test
Les tests couvrent :
- La création, mise à jour et suppression de surveys.
- La validation des réponses aux questions.
- La récupération des statistiques et résultats pour un sondage.

---

## Documentation de l'API

La documentation de l'API est générée avec **Swagger**, permettant de visualiser et de tester facilement les différents endpoints.

1. **Accès à Swagger** : Une fois l'application démarrée, accédez à la documentation Swagger à l’adresse suivante :
   ```
   http://localhost:8080/swagger-ui.html
   ```

2. **Fonctionnalités de Swagger** : Tous les endpoints sont documentés, avec des exemples de requêtes et des descriptions des paramètres et réponses.

---

## Annexes

### Variables d’Environnement pour la Production
Pour sécuriser les informations sensibles (par exemple, mots de passe de la base de données), il est recommandé d'utiliser des variables d'environnement au lieu de stocker ces informations directement dans le fichier `application.properties`.

### Optimisations pour la Production
- **Cache des Requêtes** : Activez le cache pour optimiser les performances des requêtes de lecture.
- **Sécurité** : Protégez les endpoints sensibles avec des mécanismes de sécurité (comme OAuth2 ou JWT).

---

## Contribution
Les contributions au projet **ITLens** sont les bienvenues. Veuillez suivre les règles suivantes :
1. Forkez le dépôt.
2. Créez une branche pour votre fonctionnalité (`feature/ma-fonctionnalité`).
3. Effectuez un pull request avec une description de votre travail.

---

## Licence
Ce projet est sous licence MIT. Veuillez consulter le fichier `LICENSE` pour plus de détails.
