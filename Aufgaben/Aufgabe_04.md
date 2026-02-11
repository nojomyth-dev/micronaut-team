# 4 Look at my new rearview mirrors! (AOP) (20min)

## Background
Groß-Admiral! Wir haben ein... diplomatisches Problem.

Unser Chefingenieur Zorbax hat letzte Woche auf dem intergalaktischen Handelsmarkt "leicht gebrauchte" Raumschiff-Upgrades gekauft. Der Händler --- ein suspekt grinsender Tentakelwesen namens Gleep --- hat ihm versichert, dass die "Premium Rückspiegel mit KI-Unterstützung" ein Schnäppchen seien.

Das Problem? Die Spiegel zeichnen ALLES auf. Jeden Funkspruch. Jede Kursänderung. Sogar wann Commander Chen seinen dritten Kaffee holt (es ist immer um 14:37 Uhr).

Zuerst waren wir genervt. Aber dann... dann haben wir erkannt: Das ist GENIAL!

Stellt euch vor: Wir wissen IMMER, was passiert! Keine verlorenen Missionsprotokolle mehr! Keine "Ich schwöre, ich hab das Formular abgeschickt!"-Ausreden mehr! Die Buchhaltung weint vor Freude!

Aber es gibt einen Haken: Gleep hat uns nur die Hardware verkauft, nicht die Software. Typisch. Jetzt müssen WIR die Überwachungs-KI programmieren.


## Anweisungen
a) Implementiert das `@interface` für die Klasse `de.riversroses.infra.logging.Logged`. Dieses soll ein AroundInvoked Interface sein für Methoden zur Runtime.

b) Implementiert nun den Interceptor `de.riversroses.infra.logging.LoggedInterceptor`. In dieser Klasse sind einige Kommentare von unseren vorherigen Engineers hinterlassen worden.  

c) Fügt die Annotation `de.riversroses.missions.rest.MissionController`s Methoden hinzu! Prüft mit curl, ob die Log-Nachrichten tatsächlich ankommen.

d) Wie könntet ihr die Annotation erweitern, so dass diese auf Klassen funktioniert? Tipp: Schaut auf den Folien. Implementiert dies!

e) Ersetzt eure Method-Level Annotationen nun durch eine Class-Level Annotation und versucht das curl erneut!

---

Optional für schnelle Personen:

f) Implementiert eine @Timed-Bean, eine @Authenticated oder eine Bean eurer Wahl äquivalent. Diese kann zum Beispiel mit System.millis() die Laufzeit einer Methode loggen.
