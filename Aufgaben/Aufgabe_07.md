# 7 The Six Sigamas of Space (Testing) (10min)

## Background
Admiral... Setzen Sie sich besser hin. Wir haben schlechte Neuigkeiten.

Erinnern Sie sich an die USS Prometheus? Unser schnellstes Schiff? Das mit den Ledersitzen und dem eingebauten Espresso-Automaten?

Es ist explodiert.

Nicht im Kampf. Nicht durch Piraten. Nein --- ein EINZELNES fehlerhaftes Bauteil im Navigationsmodul. Ein Praktikant (ja, schon wieder einer) hatte den Code für die Triebwerkssteuerung geschrieben. Er war sich "ziemlich sicher", dass alles funktioniert. Er hatte es "auf seinem Rechner getestet". 
Spoiler: Es funktionierte NICHT.
Captain Rodriguez konnte sich gerade noch retten. Der Espresso-Automat... hatte weniger Glück. 

**GENUG IST GENUG!**
Ab sofort führen wir das Six Sigma Protokoll ein! Benannt nach dem legendären Ingenieur Sigma Sigma Sigma Sigma Sigma Sigma, der so paranoid war, dass er alles SECHSMAL testete. Der Mann vertraute nicht mal seinem eigenen Spiegelbild!
Von nun an gilt: Kein Code erreicht die Produktion ohne automatisierte Tests!
Eure Aufgabe: Schreibt automatisierte Tests mit @MicronautTest.

## Anweisung 
Schreibt automatisierte Tests mit `@MicronautTest`.

a) Prüft, ob GET /missions eine valide Mission zurückgibt. Injecte hierfür das MissionLogRepository und füge manuell eine neue Mission ein. Teste, ob diese Mission tatsächlich ankommt. (Prüft unbedingt auch die Felder! Tipp: assertEquals())

b) Testet, ob POST /missions/complete bei korrekten Daten einen 200 OK sendet. 
b) Testet, ob POST /missions/complete bei falschen Daten (negative Credits) einen 418 OK sendet.
**Hinweis:** Ihr müsst hierfür assertThrows() benutzen. 
