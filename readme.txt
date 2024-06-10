# Tic Tac Toe Multiplayer

Tento projekt implementuje multiplayerovou hru Tic Tac Toe s architekturou klient-server. Server zpracovává více klientů a udržuje databázi uživatelských skóre. Každý klient se připojuje k serveru, což umožňuje dvěma hráčům soupeřit ve hře Tic Tac Toe, přičemž skóre se aktualizuje a udržuje mezi jednotlivými sezeními.

## Kompilace a sestavení

### Předpoklady

- Java Development Kit (JDK) 11 nebo vyšší
- Nástroj pro sestavení Maven
- Pro správnou komunikaci s databází tohoto projektu je nutné mít nainstalovaný MySQL klient.
 A v database.properties změnit přihlašovací údaje

## Pokyny ke hře

### Spusťte server:

Ujistěte se, že server běží na určeném hostiteli a portu (výchozí je `localhost:8080`).

### Spusťte klienta:

Po zobrazení výzvy zadejte jedinečné uživatelské jméno. Klient čeká na připojení druhého klienta.

### Průběh hry:

- První hráč, který se připojí, je přiřazen jako 'X' a druhý hráč jako 'O'.
- Střídavě klikněte na políčka na herní desce a provádějte tahy.
- Ve spodní části okna hry jsou zobrazovány zprávy o průběhu hry.

## Logging

Logy jsou vytvářeny v souborech `log_client.txt` a `log_server.txt` pro klientské a serverové aplikace. Tyto protokoly zachycují detaily o připojení, herní akce a chyby pro potřeby ladění.