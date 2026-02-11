# 1 Cadet, we want to explore! (10min)

## Background
Kadett! Unser Heimatplanet wird etwas eng! Wir wollen expandieren. Mit unseren hochmodernen Teleskopen haben wir mögliche Sternensysteme ausfindig gemacht, die habitable Planeten haben könnten. Bitte fliege dorthin und prüfe die Planeten, ob diese schöne Strände haben...
Damit unsere Raumschiffe wissen, welche möglichen Orte wir ausfindig gemacht haben, senden wir diese über unseren Satelliten zu euch.

**Achtung:** Durch Störsignale kann es manchmal vorkommen, dass der Satellit falsche Daten empfängt und euch Missionen schickt, die gar nicht existieren! Durch hochtechnische Analysen konnten wir feststellen, dass die Credits in diesem Fall immer <= 0 sind. Bitte lehnt diese Missionen unbedingt ab!

## Anweisung

Dateipfad: `src/main/java/de/riversroses/missions/rest/MissionController.java`
Erstellt die Schnittstellen, damit der Satellit (unser Server) mit eurem Planeten kommunizieren kann.

a) Die Klasse MissionController benötigt einen `de.riversroses.missions.business.MissionService.java`. Dieser soll als privates und finales Feld deklariert sein. Der MissionService soll über einen Constructor mit `new MissionService()` gesetzt werden.

b) Implementiert den Endpunkt `GET /missions`. Dieser gibt den Return-Value von `missionService.generateOrReuseMission()` vom Datentyp `de.riversroses.missions.dto.MissionPayloadDto` zurück.

c) Implementiert den Endpunkt `POST /missions/complete`. Die Funktion nimmt eine Mission vom Datentyp `de.riversroses.missions.dto.MissionCompletionDto` entgegen. Der Erfolg einer Mission wird über `missionService.markCompleted(mission)` gemeldet und schließlich wird `io.micronaut.http.HttpResponse.ok()` zurückgegeben.

d) Verwendet `if/else` um Missionen mit einer Belohnung kleiner gleich 0 auszusortieren, indem ihr `io.micronaut.http.HttpResponse.badRequest()` zurückgebt.
