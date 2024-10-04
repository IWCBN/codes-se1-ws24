# README
## Aufgabe 1
- Die "TranslatorFactory"-Klasse wird genutzt um einen germanTranslator zu erzeugen, sodass die Erzeugung nicht in der Klasse "Client" erfolgen muss. Dabei werden zwei Methoden "createTranslator()" implementiert. Einmal mit einer Paramterübergabe vom Datum und einmal ohne Paramter, sodass das Default Datum aus dem Interface genommen wird. Man könnte die Packages so beschreiben, dass man sagt, dass "view" die Anzeige für den Nutzer darstellt und "control" die Steuerungslogik definiert. Daher passt die TranslatorFactory"-Klasse in den "control" Bereich. Dadurch ist es auch einfacher zu testen.
- Für die Objekt-Erzeugung kann hier das Entwurfsmuster "Factory-Method-Pattern" genutzt werden, da so der Client immer nur eine Methode aufrufen muss und nicht immer bei zB. einem neuen Translator diesen ändern. Durch die Auslagerung in eine Methode kann in dieser dann einfach einmalig der Translator geändert werden.
- Im Source Code des Interfaces müssen die Sichtbarkeiten geändert werden. Das Interface wurde auf public gesetzt, damit der Client dagegen implementierten kann.

## Aufgabe 3
- Vorteile einer seperaten Test-Klasse:
   - (automatiesierte) Pre-Tests sind dadurch möglich
   - jede Klasse kann einzeln getestet werden (bzw. es ist immer bestimmbar welche Klassen getestet werden)
- Der Sinn von Äquivalenzklassen beim Blackbox-Test ist, dass ähnliche Testfälle zusammengefasst werden. Damit wird die Anzahl der Tests von meist unendlich auf eine geringe Anzahl begrenzt und trotzdem ein Funktionieren des Programms mit einer hohen Sicherheit gewährleistet werden. Dabei sollten immer Randfälle (&0), funktioniernde Werte, aber auch Werte die eine Fehlermeldung hervorrufen getestet werden. Das betrifft in unserer Aufgabe mindestens die Zahlen <1 (negative Zahlen und 0), 1, Zahlen zwischen 1 und 10, Zahlen >10.
