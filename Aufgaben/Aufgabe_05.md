# 5 Operation: Data-Airlock (Validation) (10min)

Kommandeur! Code Rot! Die Weltraumpiraten sind zurück --- und diesmal sind sie KREATIV.

Letzte Nacht hat unser Sicherheitsteam einen Einbruchsversuch abgefangen. Die Piraten haben versucht, uns Missionen mit negativen Belohnungen unterzujubeln. Stellt euch vor: Wir schließen eine Mission ab und SCHULDEN plötzlich 5000 Credits! Captain Zyx hat fast einen Herzinfarkt bekommen, als er die Buchhaltung gesehen hat.

Aber es wird schlimmer.

Unser Verteidigungssystem besteht aktuell aus... if/else-Statements. Hunderte davon. Geschrieben von unseren unbezahlten Praktikanten (die übrigens SEHR glücklich sind, hier zu sein --- bitte fragen Sie nicht nach). 

Letzten Dienstag hat Praktikant Kevin vergessen, auf null zu prüfen. Am Mittwoch hat Praktikant Lisa das else weggelassen. Am Donnerstag hat Praktikant Tim... naja, Tim hat einfach alles gelöscht und gehofft, dass es niemand merkt.

Wir brauchen ein ECHTES Sicherheitssystem!

Die Ingenieursabteilung hat von einer uralten Technologie gehört: Bean Validation. Angeblich kann man damit Regeln EINMAL definieren und sie werden AUTOMATISCH durchgesetzt. Keine vergessenen if-Statements mehr! Keine "Ups, hab ich übersehen"-Ausreden!


## Anweisung
Eure Aufgabe: Ersetzt eure manuellen if/else-Prüfungen durch Micronaut Bean Validation.

a) Nutzt `@NotNull`, `@NotBlank`, `@Min(1)` und `@Positive` in euren DTOs.

b) Aktiviert die Validierung in eurem Controller mit der `@Valid` Annotation am Parameter. Ersetzt eure `if/else` Prüfungen durch Micronaut Validation. Es sollten keine if/else-Anweisungen mehr im Controller vorhanden sein.

c) Prüft nun eine ungültige MissionCompletion zu senden. Euer Planet sollte mit einem 400 Bad Request antworten, noch bevor euer Code ausgeführt wird!



## curl Befehl zum Testen
curl -X PUT http://localhost:8081/missions/complete \
     -H "Content-Type: application/json" \
     -d '{
          "missionId": "MISSION-123",
          "shipId": "SHIP-456",
          "teamId": "TEAM-789",
          "reward": -1
         }'
