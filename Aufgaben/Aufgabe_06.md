# 6 Operation Teapot! 

## Background
Trotz besserer Sicherheitsprotokolle versuchen die Piraten uns immernoch ungültige Missionen unterzujubeln! Weltrauminternet ist nicht billig!
Wir müssen sie irgendwie überlisten uns in ruhe zu lassen..


## Anweisung

Nutzt die `@Error`-Annotation direkt in eurem `MissionController`. Ziel ist es, den Standard-Validierungsfehler (der durch `@Valid` ausgelöst wird) abzufangen und die Piraten abzuweisen.

- Fügt eine Methode im `MissionController` hinzu, die auf die `ConstraintViolationException` reagiert.
-  Markiert diese Methode mit der `@Error`-Annotation.
- Nutzt `HttpResponse.status(HttpStatus.I_AM_A_TEAPOT)`

Erfolg: Versucht eine ungültige Mission zu senden - der Satellit sollte mit einem `418 I'm a teapot` antworten. 