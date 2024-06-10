# Uživatelská příručka: Server Tic Tac Toe

Tento server umožňuje hrát hru Tic Tac Toe pomocí síťového připojení mezi klienty.

## Obsah

1. [Jak server funguje](#jak-server-funguje)
2. [Jak spustit server](#jak-spustit-server)

## Jak server funguje

Server Tic Tac Toe je implementován v jazyce Java a poskytuje síťovou infrastrukturu pro klienty, aby mohli hrát hru mezi sebou. Zde je stručný popis funkcionality:

- **Síťová architektura:** Server používá síťové sokety pro přijímání připojení od klientů a vytváření nových instancí hry pro každého hráče.
- **Připojování klientů:** Server naslouchá na určeném portu a přijímá připojení od klientů, kteří chtějí hrát hru Tic Tac Toe.
- **Vytváření her:** Pro každou novou hru server vytvoří instanci třídy Game, která zajišťuje průběh hry mezi hráči.
- **Logování událostí:** Server zaznamenává události v souboru log_server.txt, který obsahuje informace o spuštění serveru, připojení klientů a dalších důležitých událostech.

## Jak spustit server

Pro spuštění serveru postupujte následovně:

1. **Otevřete projekt v IntelliJ IDEA:** Otevřete projekt v IntelliJ IDEA a zkontrolujte, zda jsou všechny potřebné závislosti načteny.
2. **Nastavte konfiguraci:** V menu Run vyberte možnost Edit Configurations. Vytvořte novou konfiguraci typu Application a nastavte následující:
   - Name: Run Server
   - Main class: com.tictactoe.tictacteam.server.TicTacToeServer
   - Program arguments: (žádné)
   - Working directory: (adresář vašeho projektu)
3. **Spusťte server:** Klikněte na zelenou šipku vedle konfigurace Run Server pro spuštění serveru.
