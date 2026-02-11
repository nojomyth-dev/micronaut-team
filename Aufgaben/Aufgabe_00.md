# 0 Welcome to Space Sector 4-Apfel-07 (10min)

## Background
Bevor wir die Sterne erobern können, müssen wir erst wissen, wer ihr seid. Eure erste Aufgabe: Zeigt dem Sektor, wer ihr seid!

## Anweisung

Findet euch bitte in Zweiergruppen zusammen, idealerweise sollte mindestens eine Person Erfahrung in Java haben.

Öffnet IntelliJ und pullt das vorinstallierte Repository (Ctrl + T).  
Wechselt anschließend auf den Branch `0-vorher` (`git checkout 0-vorher && git pull`).

Navigiert zu `src/main/resources/application.yml` und tragt euren eindeutigen Token in folgendem Format ein `<teamnummer>-<erste Buchstaben der Vornamen>-<ein zufälliges Wort>` (am Anfang der Datei). Wählt zusätzlich einen Namen für euer Raumschiff (name) und euren Planeten (planet-name).

**keine Umlaute!**

**Beispiel: 01-FC-Momo**
```yaml
team:
  token: "01-FC-Momo"
  name: "Cat Delivery"
  planet-name: "Fluffy Cats"
```

An der Tafel findet ihr die Base-URL, welche ihr hier eintragt: 
```yaml
game-server:
  base-url: "http://<ip_address>:8080"
```

Startet den Service entweder mit folgendem Command oder innerhalb eurer IntelliJ IDE

```bash
mvn mn:run
```

Ihr solltet nun euren Planeten und Raumschiffe auf der Karte sehen können. (Hinweis: Das kann ein paar Sekunden dauern).
Falls etwas nicht funktioniert, gebt uns bitte Bescheid!

Ihr könnt die Karte ebenfalls öffnen, loggt euch bitte mit eurem oben gewählten Token ein.
Die URL (`http://<ip_address>:8080/index.html`) erhaltet ihr von uns an der Tafel (gleiche wie oben).