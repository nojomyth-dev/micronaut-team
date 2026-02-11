# 2 Cadet, we want to explore! (10min)

## Background
Kadett! Unser Heimatplanet wird etwas eng! Wir wollen expandieren. Mit unseren hochmodernen Teleskopen haben wir mögliche Sternensysteme ausfindig gemacht, die habitable Planeten haben könnten. Bitte fliege dorthin und prüfe die Planeten, ob diese schöne Strände haben...
Damit unsere Raumschiffe wissen, welche möglichen Orte wir ausfindig gemacht haben, senden wir diese über unseren Satelliten zu euch.

## Anweisung

Erstellt die Schnittstellen, damit der Satellit (unser Server) mit eurem Planeten kommunizieren kann.

`GET /missions` Implementiert diesen Endpunkt. Er soll eine Missionen zurückgeben. Um missionen von eurem Planeten zu Erhalten nutzt ```MissionService.generateOrReuseMission()``` und versendet das ```MissionPayloadDto``` Objekt mit ```return```.


`POST /missions/complete` Implementiert diesen Endpunkt. Hier meldet der Satellit den Erfolg einer Mission durch das ```MissionCompletionDto```.


## Background

**Achtung:** Durch Störsignale kann es manchmal vorkommen, dass der Satellit falsche Daten empfängt und euch Missionen schickt, die gar nicht existieren! Durch hochtechnische Analysen konnten wir feststellen, dass die Credits in diesem Fall immer <= 0 sind. Bitte lehnt diese Missionen unbedingt ab!

## Anweisung

Security-Check: Störsignale senden manchmal Missionen mit Belohnungen <= 0. 

Sortiert Missionen mit Belohnungen <= 0 aus und beantwortet diese mit `HTTP Statuscode 400` durch Verwendung von `if/else` und `return HttpResponse.badRequest()`.
