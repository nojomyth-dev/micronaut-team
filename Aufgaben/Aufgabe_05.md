# 5 Operation: Data-Airlock (Validation) (10min)

## Background
Kommandeur! Weltraumpiraten versuchen, uns weiter mit korrupten Datenpaketen zu sabotieren!
Wir müssen unsere Endpunkte besser sichern und können uns nicht darauf verlassen das unsere unbezahlten Praktikanten (die sehr glücklich sind) immer If/Else verwenden!


## Anweisung
DTO-Schutz: Nutzt `@NotNull`, `@NotBlank`, `@Min(1)` und `@Positive` in euren DTOs.

Trigger: Aktiviert die Validierung in eurem Controller mit der `@Valid` Annotation am Parameter.
Ersetzt eure `if/else` Prüfungen durch Micronaut Validation.

Erfolg: Versucht eine ungültige Mission zu senden - der Satellit sollte automatisch mit einem 400 Bad Request antworten, noch bevor euer Code ausgeführt wird! Euer Controller sollte bis auf das `@Valid` keine weitere Validierung mehr besitzen!