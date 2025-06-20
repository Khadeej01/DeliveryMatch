# 🚚 DeliveryMatch

**DeliveryMatch** est une application web innovante de co-transport collaboratif mettant en relation des **conducteurs** et des **expéditeurs de colis**. Elle a pour but d’**optimiser les trajets**, **réduire les coûts de transport** et **minimiser l’impact environnemental** en mutualisant les déplacements.



## 🛠️ Fonctionnalités

- 🔐 Authentification et gestion des comptes utilisateurs (Conducteurs, Expéditeurs, Administrateurs)
- 📦 Publication d’annonces de trajet par les conducteurs
- 🔍 Recherche de trajets par les expéditeurs
- 📨 Envoi et gestion des demandes de livraison
- 📈 Dashboard administrateur avec statistiques Chart.js
- 📝 Historique des trajets, colis et demandes
- ✅ Système de vérification d’utilisateurs
- ⭐ Système d’évaluations entre utilisateurs
- 🔔 Notifications utilisateur (acceptation/refus, confirmation, etc.)

---

## 🧑‍💻 User Stories

### 🔸 Utilisateur

- Créer un compte
- Se connecter / se déconnecter
- Modifier ses informations
- Recevoir des notifications

### 🚗 Conducteur

- Publier une annonce de trajet
- Visualiser et gérer les demandes reçues
- Accepter ou refuser les colis
- Consulter son historique
- Noter les expéditeurs

### 📦 Expéditeur

- Rechercher des trajets
- Envoyer une demande de transport
- Consulter son historique
- Noter les conducteurs

### 🛠️ Administrateur

- Dashboard de gestion complet
- Valider ou suspendre les comptes
- Gérer les annonces publiées
- Visualiser les statistiques via Chart.js

---

## 🧪 Technologies utilisées

### Backend :
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL 
- Swagger / Postman

### Frontend :
- Angular 18
- Angular Material  / Tailwind CSS
- Chart.js

### Autres :
- Docker
- JUnit 5 (tests unitaires)
- UML (Diagrammes de séquence et de cas d’usage)

---

## ⚙️ Installation

### Prérequis :
- Node.js v18+
- Angular CLI
- Java 21
- Maven
- Docker
- MySQL 

### Backend
```bash
cd backend
./mvnw spring-boot:run
