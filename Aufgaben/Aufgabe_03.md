# 3 Modular Retrofitting (Dependency Injection) (15min)

## Background
Kommandeur, wir haben über eine neue Methode kennengelernt, die es uns erlaubt, unsere Fabriken modularer zu gestalten! Wir denken, dass diese auch für dich sinnvoll sein können.
Wir nennen es **Dependency Injection**. Damit können wir Systeme tauschen, ohne die ganze Station umzubauen!

## Anweisung 
Entkoppelt die Logik vom Controller.

Repository-Pattern: Erstellt ein Interface MissionRepository und lasst  InMemoryMissionRepository davon erben.

The Power of DI: Nutzt das Constructor-Injection Pattern, um euren Service zu injizieren.

Challenge (Optional, falls ihr schnell seid): Erstellt eine zweite Klasse DatabaseMissionRepository. Nutzt Micronaut-Annotationen (wie `@Requires` oder `@Primary`), um zwischen den Repositories zu wechseln, ohne den Code im Controller zu ändern.
