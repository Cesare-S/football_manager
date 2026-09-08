# Database del gioco

Database locale: `data/football_manager.db` (SQLite).
Schema: `database/schema.sql`. Dati iniziali: `database/seed.sql`.
Il database e i suoi file temporanei sono esclusi da Git; SQL e documentazione
sono versionabili. Nessun commit è stato eseguito.

## Contenuto

- 10 club con prezzi differenti, budget operativo e proprietario inizialmente assente.
- 10 prime squadre e 283 giocatori con nome, cognome/nome sportivo, età, ruolo e overall.
- Tabella `manager` vuota, con id, nome, email univoca, password e budget personale.
- `game_state` contiene data del gioco e versione dei dati iniziali.
- `roster_source` registra fonti e data di consultazione.

La relazione è manager proprietario → club → squadre → giocatori.
Il prezzo d'acquisto appartiene al club. Ogni club ha ora una sola squadra
`FIRST`; la stessa struttura permette altre categorie, per esempio `U15`.
Non esiste un limite di giocatori per squadra.
Un manager può possedere al massimo un club in questa versione.
Il database rappresenta un solo mondo di gioco condiviso; salvataggi indipendenti
per ogni manager richiederanno una futura separazione per carriera.

## Dati e stime

Snapshot delle rose **2026/27**, consultato l'**8 settembre 2026**.
Sono inclusi tutti i giocatori elencati nelle dieci pagine qui sotto, anche i
giovani aggregati. È una fotografia delle fonti, non una certificazione federale
dei tesseramenti né un aggiornamento automatico dei trasferimenti.

Le età sono quelle riportate dalle fonti alla consultazione: non sono date
di nascita ricostruite. All'inizio si riferiscono alla data del gioco; in futuro
sarà la simulazione a gestire l'invecchiamento.
I ruoli sono macro-ruoli: POR, DIF, CEN, ATT. Le classificazioni possono differire
da quelle dei siti dei club, soprattutto per esterni e trequartisti.
Sono conservati i nomi sportivi, normalizzando alcuni accenti: i mononimi
(es. Giovane, Dodô, Éderson) hanno cognome vuoto, senza anagrafiche inventate.
Il futuro adattamento Java dovrà considerare questo caso.

Ogni overall è una **stima editoriale soggettiva** della forza attuale, non del
potenziale: 85–89 riferimento internazionale, 80–84 titolare di alto livello,
75–79 giocatore affermato, 70–74 rotazione, 58–69 giovani e riserve.
Non sono rating di Football Manager o EA e non derivano da un modello statistico
validato. `overall_is_estimated` vale 1 per tutti i giocatori iniziali.

Prezzi e budget sono **valori di gioco**, non quotazioni reali.
Gli importi sono euro interi (INTEGER); nessuna cifra decimale.
Il manager parte, per impostazione predefinita, da 1 miliardo di euro, sufficiente
per scegliere qualsiasi club. Il saldo personale e la cassa del club sono distinti.
Il futuro acquisto dovrà aggiornare saldo e proprietario nella stessa transazione.

| Club | Giocatori | Prezzo nel gioco | Budget del club | Fonte rosa/età/ruoli |
| --- | ---: | ---: | ---: | --- |
| Napoli | 27 | 650 M€ | 65 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/napoli/rosa/) |
| Juventus | 31 | 850 M€ | 85 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/juventus/rosa/) |
| Inter | 28 | 900 M€ | 90 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/inter/rosa/) |
| Milan | 28 | 750 M€ | 75 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/milan/rosa/) |
| Como | 27 | 220 M€ | 60 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/como/rosa/) |
| Roma | 26 | 550 M€ | 60 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/roma/rosa/) |
| Lazio | 32 | 250 M€ | 35 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/lazio/rosa/) |
| Fiorentina | 27 | 280 M€ | 40 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/fiorentina/rosa/) |
| Atalanta | 30 | 400 M€ | 50 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/atalanta/rosa/) |
| Bologna | 27 | 180 M€ | 30 M€ | [Virgilio Sport](https://sport.virgilio.it/calcio/squadre/bologna/rosa/) |

Controlli di confronto effettuati anche sulle rose di
[Napoli](https://sscnapoli.it/rosa-ssc-napoli-2025-2026/),
[Juventus](https://www.juventus.com/en/teams/first-team-men/squad/),
[Milan](https://www.acmilan.com/en/teams/men-first-team),
[Como](https://comofootball.com/en/players/) e
[Lazio](https://www.sslazio.it/it/team/lazio/squadra).
Per un catalogo uniforme, l'elenco importato e i macro-ruoli seguono Virgilio.

## Riproduzione e utilizzo

Requisito: SQLite 3.37 o successivo, perché lo schema usa
[tabelle STRICT](https://www.sqlite.org/stricttables.html).

Per creare un **nuovo file** con un client SQLite, eseguire prima
`schema.sql`, poi `seed.sql`. Gli script non cancellano né sostituiscono
dati esistenti: una seconda esecuzione segnala un errore.
Non rieseguire il seed all'avvio del server; serve soltanto all'inizializzazione.
Il file locale è già stato creato e popolato.

Su **ogni connessione**, abilitare `PRAGMA foreign_keys = ON` prima delle
transazioni: l'impostazione non è memorizzata nel file.
[Documentazione SQLite sulle foreign key](https://www.sqlite.org/foreignkeys.html).

La colonna `manager.password` è testo in chiaro per il prototipo, come richiesto.
Non sono stati creati utenti, password di esempio, sessioni o funzionalità di login.
L'applicazione Java non è ancora collegata al database.

Il file locale conserva le modifiche tra riavvii nello stesso Codespace.
Essendo escluso da Git, i salvataggi personali non vengono trasferiti a un altro
Codespace e vanno copiati separatamente prima di eliminare quello corrente.

