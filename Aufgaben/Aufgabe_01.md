# 1 Welcome to Space Sector 4-Apfel-07 (10min)

## Background
Bevor wir die Sterne erobern können, müssen wir erst wissen, wer ihr seid. Eure erste Aufgabe: Zeigt dem Sektor, wer ihr seid!

## Anweisung

Klont das [Repository](https://github.com/nojomyth-dev/micronaut-team) und öffnet es in IntelliJ
```bash
git clone https://github.com/nojomyth-dev/micronaut-team
```

Navigiert zu `src/main/resources/application.yml` und tragt euren eindeutigen Token in folgendem Format ein `<teamnummer>-<erste Buchstaben der Vornamen>-<ein zufälliges Wort>`

**keine Umlaute**

**Beispiel: 04-FC-Momo**
```yaml
team:
  token: "cmd-demo-token-123"
  name: "Demo Squadron"
  planet-name: "Demo Prime"
```

An der Tafel findet ihr die Base-URL, welche ihr hier eintragt: 
```yaml
game-server:
  base-url: "http://localhost:8080"
```

Startet den Service entweder mit folgendem Command oder innerhalb ihrer IntelliJ IDE

```bash
mvn mn:run
```

Ihr solltet nun euren Planeten und Raumschiffe auf der Karte sehen können. (Hinweis: Das kann ein paar Sekunden dauern).
