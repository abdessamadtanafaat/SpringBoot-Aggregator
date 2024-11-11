# SpringBoot-Aggregator

## Description
Une application Spring Boot qui agrège des réponses de plusieurs API en une seule réponse synchronisée. Cette application envoie trois requêtes HTTP synchrones à des services externes et retourne les données dans une structure agrégée.

## Fonctionnalités
- Expose un endpoint REST `/aggregateSync`.
- Agrège des données depuis trois APIs externes :
  - Photos : [https://jsonplaceholder.typicode.com/photos](https://jsonplaceholder.typicode.com/photos)
  - Posts : [https://jsonplaceholder.typicode.com/posts](https://jsonplaceholder.typicode.com/posts)
  - Comments : [https://jsonplaceholder.typicode.com/comments](https://jsonplaceholder.typicode.com/comments)

## Structure du Projet
- **AggregatedResponse** : Structure pour stocker les données agrégées.
- **AggregatorController** : Contrôleur REST qui expose le endpoint `/aggregateSync`.
- **AggregatorServiceSync** : Service qui exécute les appels synchrones aux APIs externes et mesure le temps d'exécution.
- **AppConfig** : Configuration Spring pour définir le `RestTemplate` comme bean.
- **Application** : Classe principale pour démarrer l'application Spring Boot.

## Prérequis
- Java 8 ou supérieur
- Maven
- Spring Boot

## Installation et Exécution

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/votre-nom-utilisateur/SpringBoot-Aggregator.git
   cd SpringBoot-Aggregator
