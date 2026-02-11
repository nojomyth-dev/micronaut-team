# 10 For Eternity! (Data Access) (10min)

## Background

Groß-Admiral! Glückwunsch zur Beförderung! Um eure Erfolge in den Geschichtsbüchern festhalten zu können, müssen wir.. Geschichtsbücher erstmal wieder benutzen. Wer benutzt heutzutage noch Bücher.. Naja egal! Lasst uns eure Erfolge feiern und für die Ewigkeit niederschreiben. Zudem sind die Kosten um jedes mal aufs neue nach Planeten zu scannen etwas teuer.

## Anweisung

Eure Aufgabe: Nutzt Micronaut Data, um eure Missionen persistent zu machen.
Entity: Markiert eure Missions-Klasse als @MappedEntity.
Repository: Erstellt ein Interface, das von CrudRepository erbt.
Persistence: Speichert jede generierte Mission in der Datenbank ab.
Überlebenstest: Startet euren Service neu — eure Erfolge und Missionen müssen den Neustart überleben!
TODO: Validieren dass das überhaupt die richtigen schritte sind lol