# RPG tests
Code ablauf:

`index.java -> CharacterCreate.java` 
damit man die main funktion nicht suchen muss.

`CharacterCreate.java -> Erstellung von dem Hauptcharakter und dem adding von gegnern -> Utility.java Display.java Loadout.java -> Comms.java`
Linien 54 - 56 setzten Standards, veraendert diese um die Gegner/Waffen zu veraendern `/Structs` enthaelt hierbei Standard Beispiel Gegner und Waffen.

`Utility.java Display.java Loadout.java -> Input -> Comms.java`
In den 3 Klassen wird der Input "gehandlet" und der Output angezeigt.

`Comms.java -> CONSOLE.java`
Debug console

`Utility.java Display.java Loadout.java -> Inspect.java`
Durch das clicken auf Waffen wird die getUI() funktion aufgerufen um eine UI mit informationen ueber Waffen, Gegner und den Helden anzuzeigen.


Dieser Code Beinhaltet noch kleine Fehler die noch nicht herausgebuegelt wurden. Ich bitte um ihr Verstaendniss.