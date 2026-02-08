# Rivers & Roses (Micronaut Workshop)

Welcome to the **Rivers & Roses** project! This application serves as your autonomous agent (bot) and Mission Provider in the "Rivers & Roses" space mining game.

Built with **Micronaut 4**, this project demonstrates how to build a robust, reactive microservice that interacts with a central Game Server.

---

## Table of Contents

* [Architecture Overview](https://www.google.com/search?q=%23-architecture-overview)
* [Key Features](https://www.google.com/search?q=%23-key-features)
* [Prerequisites](https://www.google.com/search?q=%23-prerequisites)
* [Configuration](https://www.google.com/search?q=%23-configuration)
* [Running the Application](https://www.google.com/search?q=%23-running-the-application)
* [H2 Database Console](https://www.google.com/search?q=%23-h2-database-console)
* [Project Structure](https://www.google.com/search?q=%23-project-structure)

---

## Architecture Overview

The **Rivers & Roses** plays two roles:

1. **The Bot (Client):** It registers your team planet and ships with the Game Server, scans the environment, and automates movement to collect resources.
2. **The Mission Provider (Server):** It exposes endpoints that the Game Server calls to fetch missions and report completions.

---

## Key Features

This project showcases "The Micronaut Way" of building applications:

* **Declarative HTTP Client:** `GameServerClient` uses interfaces to communicate with the Game Server, featuring built-in **Circuit Breaker** patterns for resilience.
* **Micronaut Data JPA:** Repository pattern (`MissionLogRepository`) using compiled-time query generation.
* **AOP (Aspect Oriented Programming):** Custom `@Logged` annotation and `LoggedInterceptor` for method execution tracking.
* **Scheduled Tasks:** `PatrolStrategyService` runs on a fixed delay to automate game logic.
* **DTOs & Records:** Uses Java `record`s for immutable data transfer objects (DTOs) and `Serdeable` for reflection-free JSON serialization.
* **In-Memory Database:** H2 Database with a programmatic web console for debugging.

---

## Prerequisites

* **Java 21** (JDK)
* **Maven 3.9+**
* **Game Server** (We will provide you with the URL!)

---

## Configuration

The main configuration is located in `src/main/resources/application.yml`.

### Team Settings

Configure your identity in the game:

```yaml
team:
  token: "cmd-your-unique-token"  # Your persistent identity (keep secret!)
  name: "My Micronaut Squad"      # Team Display name
  planet-name: "Micronaut Prime"  # Your home base

```

### Game Server Connection

```yaml
game-server:
  base-url: "http://localhost:8080" # URL of the central game server, will be given to you

```

### Strategy Tuning

Adjust how your bot behaves:

```yaml
strategy:
  home-bias: 0.5        # Probability (0.0-1.0) of returning to base vs random patrol
```

---

## Running the Application

1. **Compile and Run:**
```bash
./mvnw mn:run

```


The application will start on port **8081** (by default).
2. **Verify Startup:**
Check the logs for:
```text
INFO  d.r.p.b.RegistrationService - Registered team My Micronaut Squad...
INFO  d.r.infra.db.H2ConsoleService - H2 Console started: http://localhost:8082

```



---

## H2 Database Console

This project includes a custom H2 Console service for inspecting your `mission_log` and internal state.

* **URL:** [http://localhost:8082](https://www.google.com/search?q=http://localhost:8082)
* **Driver Class:** `org.h2.Driver`
* **JDBC URL:** `jdbc:h2:mem:teamdb`
* **User Name:** `sa`
* **Password:** *(leave empty)*

---

## Project Structure

```text
src/main/java/de/riversroses/
├── config/             # Configuration Properties records
├── infra/
│   ├── client/         # GameServerClient (HTTP) & Fallbacks
│   ├── db/             # H2 Console Service
│   ├── error/          # Global Exception Handlers
│   └── logging/        # AOP @Logged Annotation & Interceptor
├── missions/           # Mission Provider Logic (Server side)
│   ├── business/       # Logic to generate missions & rewards
│   ├── db/             # JPA Repositories
│   ├── model/          # JPA Entities
│   └── rest/           # Endpoints exposed to Game Server
├── planet/             # Team Registration Logic
├── ship/               # Ship DTOs
└── strategy/           # The "Bot" Logic (PatrolStrategyService)

```

---

## Testing

Run the included JUnit 5 / Micronaut Test suite:

```bash
./mvnw test
```

See `src/test/java/de/riversroses/missions/MissionControllerTest.java` for an example of testing controllers with an in-memory database context.
