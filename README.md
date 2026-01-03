# 🎵 MusicQuiz

MusicQuiz is een webapplicatie waarin gebruikers muziekquizzen kunnen spelen en beheren.  
De app maakt gebruik van de **Deezer API** voor muziekfragmenten en is gebouwd met **Spring Boot**.

Gebruikers kunnen:
- 🎧 Quizzen spelen met echte muziekfragmenten
- 📝 Quizzen, vragen en songs beheren
- 👤 Accounts en rollen gebruiken (ADMIN)
- 📊 Acties worden automatisch gelogd (audit logging)

---

## 🚀 Features

### Voor gebruikers
- Muziekquizzen spelen
- Muziekfragmenten beluisteren via Deezer
- Eigen voortgang en resultaten bekijken
- Songs beheren (gekoppeld aan Deezer track IDs)
- Quizzen en vragen aanmaken en bewerken

### Voor admins(wat users kunnen +)
- Gebruikers beheren
- Audit logs bekijken (met paging)

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

Voor demo purposes niet de hele voorzien van Unit tests. Maar wel een aantal toegevoegd:

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