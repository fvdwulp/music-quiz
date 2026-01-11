# 🎵 MusicQuiz

Voor de programmeeropdracht werd gevraagd voor een simpele Trivia app. Aangezien ik dit iets wat kleinschalig vond
heb ik het wat uitgebreider gemaakt.

Daarom heb ik MusicQuiz gemaakt. Dit is een webapplicatie waarin gebruikers muziekquizzen kunnen spelen en beheren.  
De app maakt gebruik van de **Deezer API** voor muziekfragmenten en is gebouwd met **Spring Boot** en **Hibernate**. De data wordt bij mij lokaal opgeslagen in een **MySQL** database maar op de server wordt **PostgreSQL** gebruikt.

De app heeft een eigen dashboard om muziek quizzen te maken en beheren. Zo kan je liedjes, vragen, antwoorden en quizen toevoegen.
Elke actieve quiz kan gedeeld worden via Dashboard -> quizen -> delen. Dan krijg je een url met de bij behorende quiz en kan deze gespeeld worden.

De quiz leest vervolgens onze eigen API uit om de vragen en mogelijke antwoorden op te halen. De controle op het antwoord wordt vervolgens met een losse call gedaan.

De front-end is geschreven met Vue.js.

---

## 🚀 Features

Het dashboard heeft een login systeem met daarbij 2 rollen. Users en Admins.

### Voor Users
- Songs beheren (gekoppeld aan Deezer track IDs)
- Quizzen aanmaken, bewerken en delen
- Vragen en antwoorden aanmaken en bewerken

### Voor Admins(wat Users kunnen +)
- Gebruikers beheren
- Audit logs bekijken (met paging)

De quiz(front-end) is door iedereen benaderbaar door de url te delen. Zij kunnen dan:
- De quiz spelen
- Nummers afspelen
- Aan het einde het resultaat bekijken

---

## 🚀 De app gebruiken

- De app draait op https://music-quiz-dfn3.onrender.com/
- Hier kan je inloggen https://music-quiz-dfn3.onrender.com/login
  Inloggen kan met username: fabian / password: test123
- Dit is een mogelijke url voor een quiz https://music-quiz-dfn3.onrender.com/quiz/8bcf3564-0f31-4bff-82f6-6c21ccec3ed6

---

## 🛠 Tech stack

- **Java 17+**
- **Spring Boot**
    - Spring MVC
    - Spring Data JPA
    - Spring Security
- **Thymeleaf**
- **MySQL**
- **Deezer API**
- **Hibernate Validator**
- **JUnit & Mockito** (unit testing)

---

## 🔐 Security

- Authenticatie via Spring Security
- Rol-gebaseerde autorisatie (USER / ADMIN)
- Method-level security (`@PreAuthorize`)
- Eigenaarschap van data (users zien alleen hun eigen data)
- Custom error pages

---

## 🧠 Architectuur

```text
Controller
   ↓
Service (business logic + security)
   ↓
Repository (JPA)
   ↓
Database
```

---

## 📊 Audit logging

Elke belangrijke actie wordt automatisch gelogd:
- Aanmaken / wijzigen / verwijderen
- Welke gebruiker
- Welke entiteit
- Tijdstip

Logs zijn:
- Paginated
- Alleen zichtbaar voor admins

---

## 🧪 Testing

Voor demo purposes niet de hele app voorzien van Unit tests. Maar wel een aantal toegevoegd:

- Business logic
- Security & ownership
- Validatie
- Mockito voor mocks
- Geen database nodig voor unit tests

---

## 🌐 Deezer API

De app gebruikt Deezer voor:
- Track previews
- Track IDs

Track IDs worden opgehaald via de previewUrl. Daarom zijn er alleen fragmenten van 30 seconde bechikbaar.

---

## 👤 Auteur

Gemaakt door Fabian van der Wulp