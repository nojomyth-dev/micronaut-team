# 4 Look at my new rearview mirrors! (AOP) (20min)

a) Implementiert das @interface für die Klasse `de.riversroses.infra.logging.Logged`. Dieses soll ein AroundInvoked Interface sein für Methoden zur Runtime.

b) Implementiert nun den Interceptor `de.riversroses.infra.logging.LoggedInterceptor`. In dieser Klasse sind einige Kommentare von unseren vorherigen Engineers hinterlassen worden.  

c) Fügt die Annotation `de.riversroses.missions.rest.MissionController`s Methoden hinzu! Prüft mit curl, ob die Log-Nachrichten tatsächlich ankommen.

d) Wie könntet ihr die Annotation erweitern, so dass diese auf Klassen funktioniert? Tipp: Schaut auf den Folien. Implementiert dies!

e) Ersetzt eure Method-Level Annotationen nun durch eine Class-Level Annotation und versucht das curl erneut!

---

Optional für schnelle Personen:

f) Implementiert eine @Timed-Bean oder eine Bean eurer Wahl äquivalent. Diese kann zum Beispiel mit System.millis() die Laufzeit einer Methode loggen.