# Database del gioco

Database locale: `data/football_manager.db` (SQLite).
Schema corrente v2: `database/schema.sql`. Seed iniziale: `database/seed.sql`.
Estensione campionati: `database/seeds/002_clubs_2026_27.sql`.
Migrazione per database esistenti: `database/migrations/002_team_competition.sql`.
Il database e i suoi file temporanei sono esclusi da Git; SQL e documentazione
sono versionabili. Nessun commit è stato eseguito.

## Contenuto

- 97 club e 100 squadre iscritte alla stagione 2026/27: 20 in A, 20 in B, 60 in C.
- 97 prime squadre e tre seconde squadre U23 collegate ai rispettivi club.
- 283 giocatori conservati nelle dieci rose iniziali; nessun giocatore aggiunto dal nuovo seed.
- Tabella `manager` con id, nome, email univoca, password e budget personale; i seed aggiuntivi non modificano i manager esistenti.
- `game_state` contiene data del gioco e versione dei dati iniziali.
- `roster_source` registra fonti e data di consultazione.

La relazione è manager proprietario → club → squadre → giocatori.
Il prezzo d'acquisto appartiene al club. Ogni club ha una squadra `FIRST`; Juventus, Atalanta e Inter hanno anche una
squadra `U23`. La stessa struttura permette altre categorie, per esempio `U15`.
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

Per creare un **nuovo database**, eseguire in ordine `schema.sql`, `seed.sql`,
poi `seeds/002_clubs_2026_27.sql`. Schema e seed iniziali non sono ripetibili.
La migrazione 002 non è necessaria per lo schema corrente v2, ma è innocua.
Il seed aggiuntivo 002 è ripetibile. Usare il client SQLite con `-bail` per fermarsi
al primo errore e annullare la transazione incompleta alla chiusura.
Non rieseguire i seed all'avvio del server.

Su **ogni connessione**, abilitare `PRAGMA foreign_keys = ON` prima delle
transazioni: l'impostazione non è memorizzata nel file.
[Documentazione SQLite sulle foreign key](https://www.sqlite.org/foreignkeys.html).

La colonna `manager.password` è testo in chiaro per il prototipo, come richiesto.
Non sono stati creati utenti, password di esempio, sessioni o funzionalità di login.
La registrazione Java utilizza già il database. Il salvataggio della scelta
della squadra resta da implementare dall'utente.

Il file locale conserva le modifiche tra riavvii nello stesso Codespace.
Essendo escluso da Git, i salvataggi personali non vengono trasferiti a un altro
Codespace e vanno copiati separatamente prima di eliminare quello corrente.



## Estensione campionati 2026/27 — 14 settembre 2026

Intervento SQL e documentazione scritti dall'agente AI con deroga esplicita
limitata a questo task. Elenchi verificati nelle seguenti fonti:

| Competizione | Squadre | Fonte |
| --- | ---: | --- |
| Serie A | 20 | [Lega Serie A, elenco 2026/27](https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27) |
| Serie B | 20 | [Lega B, classifica 2026/27](https://www.legab.it/seriebkt/classifica) |
| Serie C, girone A | 20 | [Lega Serie C, girone A 2026/27](https://www.seriec.com/gironi/girone-a) |
| Serie C, girone B | 20 | [Lega Serie C, girone B 2026/27](https://www.seriec.com/gironi/girone-b) |
| Serie C, girone C | 20 | [Lega Serie C, girone C 2026/27](https://www.seriec.com/gironi/girone-c) |

Le fonti sono una fotografia verificata il 2026-09-14, non aggiornamenti live.
Normalizzazioni dei nomi abbreviati: H. Verona → Hellas Verona,
V. Entella → Virtus Entella, Arzignano V. → Arzignano Valchiampo,
Ospitaletto F. → Ospitaletto Franciacorta, Guidonia M. → Guidonia Montecelio,
Picerno → AZ Picerno, Altamura → Team Altamura, Under 23 → U23.

### Schema e seconde squadre

La tabella aggiuntiva `team_competition` collega squadra, stagione, serie e
girone, con fonte e data di verifica. `team.category` continua a distinguere
`FIRST` da `U23`: non è il campionato. Serie A e B non hanno girone;
per la Serie C il girone è obbligatorio (A, B oppure C).

Juventus Next Gen (C/A), Atalanta U23 (C/B), Inter U23 (C/C) sono squadre
dei club Juventus, Atalanta e Inter già esistenti, non società acquistabili
separatamente. Perciò ci sono 100 squadre ma 97 club. La pagina mostra tutte
le squadre e, per le U23, specifica società di appartenenza e prezzo del club.
Il futuro salvataggio dovrà distinguere scelta della squadra e proprietà del club.

### Economia provvisoria e dati preservati

Per i soli nuovi club il seed assegna valori uniformi di prototipo:
A: acquisto 100 M€, budget 20 M€; B: 25 M€ e 5 M€; C: 5 M€ e 1 M€.
Sono scelte di gioco, non valori reali ricercati o valutazioni economiche.
I dieci club originali conservano prezzi e budget precedenti.
Nessuna modifica a manager, proprietari, giocatori, fonti delle rose o data di gioco.
La sola versione del seed passa da `2026-27-v1` a `2026-27-v2-clubs`.
Le 90 nuove squadre non hanno ancora giocatori.

### Aggiornare un database v1 esistente

Dalla radice del progetto, dopo un backup, eseguire:

```bash
sqlite3 -bail data/football_manager.db -cmd '.read database/migrations/002_team_competition.sql' -cmd '.read database/seeds/002_clubs_2026_27.sql' 'PRAGMA integrity_check; PRAGMA foreign_key_check;'
```

Non rieseguire `schema.sql` o il primo `seed.sql` su un database esistente.
La migrazione 002 è per v1/v2; non usarla su future versioni dello schema.
Il nuovo seed inserisce soltanto dati mancanti e verifica che tutte le 100
iscrizioni corrispondano al catalogo. Non aggiorna automaticamente stagioni
o campionati di una carriera già avanzata: in caso di incompatibilità si ferma.

Il database locale è già aggiornato. Backup precedente all'intervento:
`data/football_manager.before-clubs-2026-27.db`.
Il backup, come il database, contiene eventuali dati personali e non va pubblicato.

### Pagina di selezione

`src/main/resources/templates/club-selection.html` contiene le 100 squadre
e i loro ID verificati sul database locale. Il campo radio si chiama
`team_id`, il valore è `team.id`. `data-club-id` serve solo come annotazione
HTML: non è un campo inviato dal form. Il backend dovrà ricavare il club dalla
squadra e validare stagione/categoria/proprietà.
La pagina usa soltanto HTML/CSS, senza lettura dinamica del database:
altri database con ID differenti richiedono di rigenerare o rendere dinamico l'elenco.
Il pulsante di conferma è disabilitato; il salvataggio resta all'utente.

### Verifiche eseguite

- Migrazione su copia del database originale e creazione da zero con schema e seed.
- Doppia esecuzione di migrazione e seed aggiuntivo senza duplicati.
- 20 squadre in A, 20 in B, 20 per ciascuno dei tre gironi di C.
- 97 club, 100 squadre, 283 giocatori; controlli SQLite di integrità e relazioni superati.
- Confronto con il backup: tutti i dati originali preservati, eccetto versione del seed.
- ID squadra/club riproducibili nel database ricreato e coerenti con i 100 radio HTML.
- Nessuna compilazione/esecuzione Java; nessuna verifica visiva in browser.
