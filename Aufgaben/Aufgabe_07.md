# 7 The Six Sigamas of Space (Testing) (10min)

## Background
Admiral... Schlechte Neuigkeiten. Ein fehlerhaftes Bauteil hat ein Schiff zerstört. Wir führen ab sofort das Six Sigma Protokoll ein: Wir testen alles!

## Anweisung 
Schreibt automatisierte Tests mit `@MicronautTest`.

Integrität: Prüft, ob GET /missions eine valide Liste zurückgibt. Einmal Leer, einmal mit mocked Missions!
Erfolgsmeldung: Testet, ob POST /missions/complete bei korrekten Daten einen 200 OK sendet.
Abwehr: Testet, ob eine invalide Mission (z.B. negative Credits) wirklich einen 400 Bad Request provoziert.