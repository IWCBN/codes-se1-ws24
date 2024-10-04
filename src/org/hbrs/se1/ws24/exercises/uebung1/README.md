# README
## Aufgabe 1
- Die "TranslatorFactory"-Klasse wird genutzt um einen germanTranslator zu erzeugen, sodass die Erzeugung nicht in der Klasse "Client" erfolgen muss. Dabei werden zwei Methoden "createTranslator()" implementiert. Einmal mit einer Paramterübergabe vom Datum und einmal ohne Paramter, sodass das Default Datum aus dem Interface genommen wird. Man könnte die Packages so beschreiben, dass man sagt, dass "view" die Anzeige für den Nutzer darstellt und "control" die Steuerungslogik definiert. Daher passt die TranslatorFactory"-Klasse in den "control" Bereich. Dadurch ist es auch einfacher zu testen.
- Für die Objekt-Erzeugung kann hier das Entwurfsmuster "Factory-Method-Pattern" genutzt werden, da so der Client immer nur eine Methode aufrufen muss und nicht immer bei zB einem neuen Translator diesen ändern. Durch die Auslagerung in eine Methode kann in dieser dann einfach einmalig der Translator geändert werden.
- Im Source Code des Interfaces müssen die Sichtbarkeiten geändert werden. Das Interface wurde auf public gesetzt, damit der Client dagegen implementierten kann.

## Aufgabe 3
