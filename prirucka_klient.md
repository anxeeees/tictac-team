# Uživatelská příručka: Klient Tic Tac Toe

Tento klient umožňuje hrát hru Tic Tac Toe proti jiným hráčům pomocí síťového připojení.

## Obsah

1. [Jak klient funguje](#jak-klient-funguje)
2. [Jak spustit klienta](#jak-spustit-klienta)
3. [Jak hrát](#jak-hrat)

## Jak klient funguje

Klient Tic Tac Toe je implementován v jazyce Java a využívá grafické uživatelské rozhraní (GUI) pro interakci s uživatelem. Zde je stručný popis funkcionality:

- **Grafické rozhraní:** Klient má grafické rozhraní, které zahrnuje herní desku s 3x3 políčky a dolní panel s informačním textem.
- **Klient-server architektura:** Klient se připojuje k serveru pomocí síťového připojení, aby mohl hrát hru proti jiným hráčům.
- **Síťová komunikace:** Klient komunikuje se serverem pomocí soketů a odesílá informace o tazích a přijímá aktualizace herního stavu.
- **Logování událostí:** Klient zaznamenává události v souboru log_client.txt, který obsahuje informace o připojení, hráčových tazích a dalších důležitých událostech.

## Jak spustit klienta

Pro spuštění klienta postupujte následovně:

1. **Otevřete projekt v IntelliJ IDEA:** Otevřete projekt v IntelliJ IDEA a zkontrolujte, zda jsou všechny potřebné závislosti načteny.
2. **Nastavte konfiguraci:** V menu Run vyberte možnost Edit Configurations. Vytvořte novou konfiguraci typu Application a nastavte následující:
   - Name: Run Client
   - Main class: com.tictactoe.tictacteam.client.TicTacToeClient
   - Program arguments: localhost
   - Working directory: (adresář vašeho projektu)
3. **Spusťte klienta:** Klikněte na zelenou šipku vedle konfigurace Run Client pro spuštění klienta.

## Jak hrát

Po spuštění klienta se zobrazí dialogové okno, které vás vyzve k zadání vašeho uživatelského jména. Po zadání jména se zahájí připojení k serveru a zobrazení herní desky.

- **Tahy hráče:** Hráči se střídavě pokouší umístit své značky ('X' nebo 'O') na volná políčka kliknutím na ně.
- **Průběh hry:** Informace o průběhu hry jsou zobrazeny v dolním panelu, včetně upozornění na tazích soupeře a konec hry.
- **Znovu hrát:** Po skončení hry se zobrazí dialogové okno s možností hrát znovu. Kliknutím na tlačítko "Ano" můžete začít novou hru.
