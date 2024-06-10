# Tic Tac Toe Multiplayer

Tento projekt implementuje multiplayerovou hru Tic Tac Toe s architekturou klient-server. Server zpracovává více klientů a udržuje databázi uživatelských skóre. Každý klient se připojuje k serveru, což umožňuje dvěma hráčům soupeřit ve hře Tic Tac Toe, přičemž skóre se aktualizuje a udržuje mezi jednotlivými sezeními.

## Kompilace a sestavení

### Předpoklady

- Java Development Kit (JDK) 11 nebo vyšší
- Nástroj pro sestavení Maven

## Pokyny ke hře

### Spusťte server:

1. Otevřete projekt v IntelliJ IDEA.
2. Klikněte na `Run` -> `Edit Configurations...`.
3. V dialogovém okně klikněte na `+` v levém horním rohu a vyberte `Application`.
4. Vyplňte následující pole:
   - **Name:** `Run Server`
   - **Main class:** `com.tictactoe.tictacteam.server.TicTacToeServer`
   - **Working directory:** (adresář vašeho projektu)
5. Klikněte na `Apply` a poté na `OK`.
6. Klikněte na zelenou šipku vedle `Run Server` konfigurace pro spuštění serveru.

### Spusťte klienta:

1. Otevřete projekt v IntelliJ IDEA.
2. Klikněte na `Run` -> `Edit Configurations...`.
3. V dialogovém okně klikněte na `+` v levém horním rohu a vyberte `Application`.
4. Vyplňte následující pole:
   - **Name:** `Run Client`
   - **Main class:** `com.tictactoe.tictacteam.client.TicTacToeClient`
   - **Program arguments:** `localhost`
   - **Working directory:** (adresář vašeho projektu)
5. Klikněte na `Apply` a poté na `OK`.
6. Klikněte na zelenou šipku vedle `Run Client` konfigurace pro spuštění klienta.
7. Po zobrazení výzvy zadejte jedinečné uživatelské jméno. Klient čeká na připojení druhého klienta.

### Průběh hry:

- První hráč, který se připojí, je přiřazen jako 'X' a druhý hráč jako 'O'.
- Střídavě klikněte na políčka na herní desce a provádějte tahy.
- Ve spodní části okna hry jsou zobrazovány zprávy o průběhu hry.

## Logging

Logy jsou vytvářeny v souborech `log_client.txt` a `log_server.txt` pro klientské a serverové aplikace. Tyto protokoly zachycují detaily o připojení, herní akce a chyby pro potřeby ladění.
- Střídavě klikněte na políčka na herní desce a provádějte tahy.
- Ve spodní části okna hry jsou zobrazovány zprávy o průběhu hry.

## Logging

Logy jsou vytvářeny v souborech `log_client.txt` a `log_server.txt` pro klientské a serverové aplikace. Tyto protokoly zachycují detaily o připojení, herní akce a chyby pro potřeby ladění.
