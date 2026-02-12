# 6 Operation Teapot! 

## Background
Kommandeur! Die Piraten... sie geben einfach nicht auf!

Trotz unserer neuen Validation-Protokolle hämmern sie IMMER NOCH gegen unsere Systeme. Jeden Tag. Jede Stunde. Jede Minute. Unsere Serverkosten explodieren! Weltrauminternet kostet 50 Credits pro Megabyte --- und diese Idioten schicken uns täglich GIGABYTES an Müll-Anfragen!

Die Buchhaltung ist am Limit. Gestern hat der Finanzchef angefangen, leise zu weinen, als er die Rechnung gesehen hat.

Aber DANN hatte Lieutenant Kowalski eine brillante Idee. Sie hat in den alten Archiven der Erde gestöbert und eine uralte Legende gefunden: HTTP Status Code 418 --- "I'm a teapot".

"Was soll das bedeuten?", haben wir gefragt.

"Keine Ahnung", sagte sie. "Aber stellt euch vor, wie VERWIRRT die Piraten sein werden!"

Der Plan ist genial in seiner Absurdität:
Anstatt den Piraten einen normalen Fehler zu schicken, antworten wir mit... "Ich bin eine Teekanne". 
Die Piraten werden denken, sie haben das falsche System gehackt. Oder dass wir verrückt geworden sind. Oder beides. So oder so --- sie werden uns in Ruhe lassen, weil niemand eine Teekanne angreifen will!
Unser Praktikant meinte, dass es ein Handtuch auch einfach getan hätte, aber die Produktionskosten für diese Menge an Handtüchern und um diese erst in den Weltraum zu befördern..


## Anweisung
Eure Aufgabe: Implementiert einen Error Handler, der Validierungsfehler abfängt und die Piraten mit maximaler Verwirrung bestraft.

a) Nutzt die `@Error`-Annotation direkt in eurem `MissionController`. Ziel ist es, den Standard-Validierungsfehler (der durch `@Valid` ausgelöst wird) abzufangen und die Piraten abzuweisen.

b) Fügt eine Methode im `MissionController` hinzu, die auf die `ConstraintViolationException` reagiert.

c) Markiert diese Methode mit der `@Error`-Annotation. Nutzt `HttpResponse.status(HttpStatus.I_AM_A_TEAPOT)`

d) Versucht eine ungültige MissionCompletion zu senden - es sollte nun mit `418 I'm a teapot` antworten.
**Tipp**: `curl -i localhost:8081/missions`
Das -i zeigt hier minimale Zusatzinformationen an, inklusive dem Status-Code.


## curl Befehl zum Testen
curl -X PUT http://localhost:8081/missions/complete \
     -H "Content-Type: application/json" \
     -d '{
          "missionId": "MISSION-123",
          "shipId": "SHIP-456",
          "teamId": "TEAM-789",
          "reward": -1
         }'
