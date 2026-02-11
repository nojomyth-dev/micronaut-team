# 2 Modular Retrofitting (Dependency Injection) (15min)

## Background
Kommandeur, wir haben über eine neue Methode kennengelernt, die es uns erlaubt, unsere Fabriken modularer zu gestalten! Wir denken, dass diese auch für dich sinnvoll sein können.
Wir nennen es **Dependency Injection**. Damit können wir Systeme tauschen, ohne die ganze Station umzubauen!

## Anweisung 
Entkoppelt die Logik vom Controller wie folgt:

a) Baue den Constructor in **MissionController** und **MissionService** so um, dass die Dependencies injiziert werden (Constructor Injection, siehe Folien). Denke dabei daran, die Dependencies zu Beans zu machen (Service und Repository).

b) Erstelle ein Interface **MissionLogRepository** (`de.riversroses.missions.db.MissionLogRepository.java`) von dem **InMemoryMissionLogRepository** und **DatabaseMissionLogRepository** erben

c) Annotiere die beiden Klassen so, dass wenn die Bean **JPAMissionLogRepository** vorhanden ist das **DatabaseMissionLogRepository** genutzt wird, ansonsten soll **InMemoryMissionLogRepository** genutzt werden.

d) Probiere aus, ob du Unterschiede bemerkst, wenn **MissionRng** mit @Prototype oder mit @RequestScope annotiert ist.
