# 3.5 Houston, wir haben ein Problem... oder auch nicht! (15min)

## Background

**Kommandeur! Unsere Kommunikationsoffiziere haben eine beunruhigende Entdeckung gemacht.**
Letzte Woche ist unser Hauptkommunikationssatellit ausgefallen. Für genau 47 Sekunden. In diesen 47 Sekunden hat unsere Flotte **23 Befehle** an den Satelliten für unsere Raumschiffe geschickt - und ALLE sind ins Leere gelaufen. Drei Schiffe sind gegen Asteroiden geflogen, weil sie keine Kursbestätigung bekommen haben. Ein Schiff hat sich im Kreis gedreht, weil es dachte, es hätte keinen Befehl erhalten. Captain Murphy hat 17 Mal versucht, sein Schiff zu registrieren und jetzt haben wir 17 Schiffe mit dem Namen "Murphy's Law I" bis "Murphy's Law XVII". Als ob eins schon nicht schlimm genug gewesen wäre!
**Das Schlimmste?** Unser Code hat einfach... aufgegeben. Keine Wiederholungsversuche. Keine Fehlermeldungen.
Die Ingenieure haben in den alten Archiven der Erde gewühlt und zwei mächtige Technologien gefunden:

1. **Circuit Breaker:** Wie eine Sicherung in eurem Haus! Wenn zu viele Anfragen fehlschlagen, wird der Stromkreis unterbrochen, bevor alles explodiert. Nach einer Weile versucht er es vorsichtig wieder.

2. **Fallback:** Plan B! Wenn der Server nicht antwortet, geben wir nicht auf! Wir haben eine Notfall-Antwort parat! (Einen Default-Return-Value)

---

## Anweisung

Implementiert einen **HTTP-Client** mit **Circuit Breaker** und **Fallback-Mechanismus** für die Kommunikation mit dem Satelliten.

### a) Der Kommunikationskanal --- Game Server Client Interface

Erstellt ein Interface `de.riversroses.infra.client.GameServerClient` mit der Annotation `@Client(id = "game-server")`.

Implementiert folgende Endpunkte:
- `POST /ships/course`: Setzt den Kurs eines Schiffes (Header: `X-Token`, Body: `SetCourseRequestDto`, Return: `void`)
- `GET /scan{?shipId}`: Führt einen Radarscan durch (Header: `X-Token`, Optional Query: `shipId`, Return: `RadarScanResponseDto`)

**Hinweis:** Nutzt `@Header("X-Token")` für den Token und `@Nullable @QueryValue("shipId")` für optionale Query-Parameter.


### b) Die Sicherung einbauen: Circuit Breaker

Fügt dem Interface die `@CircuitBreaker`-Annotation hinzu mit folgenden Parametern:
- `delay = "2s"`: Wartezeit zwischen Wiederholungsversuchen
- `attempts = "3"`:  Maximale Anzahl an Versuchen
- `reset = "30s"`: Zeit bis der Circuit Breaker wieder geschlossen wird

Ein CircuitBreaker agiert im Fehlerfall (Exception) und kann die HTTP-Client-Anfragen erneut absenden. Dies ist sinnvoll, wenn zum Beispiel der Server für kurze Zeit nicht erreichbar ist. Wenn alle Versuche fehlschlagen, wird eine Exception geworfen.

### c) Plan B: Fallback Implementierung

Erstellt eine Klasse `de.riversroses.infra.client.GameServerClientFallback`, die:
- Das `GameServerClient`-Interface implementiert
- Mit `@Fallback` annotiert ist
- Für jede Methode eine sinnvolle Fallback-Antwort liefert:
  - `setCourse()` -> Loggt eine Warnung (inkl. `shipId` aus dem Request)
  - `scan()` -> Loggt eine Warnung, gibt ein leeres `RadarScanResponseDto` zurück

**Hinweis:** Nutzt `@Slf4j` (Lombok) oder erstellt einen Logger manuell für die Warnungen (`public static final log = LoggerFactory.getLogger(GameServerClientFallback.java)`).

Die Fallback-Implementierung erlaubt in Micronaut im Fehlerfall von HTTP-Clients selbst Default-Werte zurückzugeben, damit der Business-Code vereinfacht werden kann (z. B. null-Checks entfernen, weil eine leere Liste zurückgegeben wurde).

### d) Konfiguration

Fügt in eurer `application.yml` die Server-URL hinzu:
```yaml
micronaut:
  http:
    services:
      game-server:
        url: ${GAME_SERVER_URL:http://localhost:8080}
```

Hierbei ist game-server die vorhin in @Client vergebene ID. Es ist somit möglich weitere HTTP Clients zu generieren und konfigurieren.

### e) Zusatzaufgabe für schnelle Pilot*innen

Schaut euch die generierten Klassen im Target-Ordner an. Welche verschiedenen Arten von Klassen findet ihr? Was könnten diese bedeuten?

---

*"Ein guter Pilot hat immer einen Plan B. Ein GROSSARTIGER Pilot hat einen Plan B, der automatisch aktiviert wird, während er Kaffee trinkt."*
– Flughandbuch der Sternenflotte, Kapitel 7: "Wenn alles schiefgeht"

*"Warum haben wir 17 Schiffe mit meinem Namen?"*
– Captain Murphy, immer noch verwirrt