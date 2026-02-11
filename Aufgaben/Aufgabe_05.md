# 5 Operation: Data-Airlock (Validation) (10min)

## Background
Kommandeur! Weltraumpiraten versuchen, uns mit korrupten Datenpaketen zu sabotieren. Wir müssen unsere Schotten dicht machen! Bitte beschützt uns!

## Anweisung

Überprüft, ob die Mission korrekt abgeschlossen wurde.

Verwendet die Validation Annotationen `@Valid`, `@NotNull`, `@NotBlank`. Objekte in Post entgegennehmen

Ersetzt eure `if/else` Prüfungen durch Micronaut Bean Validation.

Nutzt `@NotNull`, `@NotBlank`, `@Min(1)` und `@Positive` in euren Request-Klassen.


DTO-Schutz: Nutzt `@NotNull`, `@NotBlank`, `@Min(1)` und `@Positive` in euren Request-Klassen.
Trigger: Aktiviert die Validierung in eurem Controller mit der `@Valid` Annotation am Parameter.
Erfolg: Versucht eine ungültige Mission zu senden — der Satellit sollte automatisch mit einem 400 Bad Request antworten, noch bevor euer Code ausgeführt wird! Euer Controller sollte bis auf das `@Valid` keine weitere Validierung mehr besitzen.
