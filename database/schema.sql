-- SQLite >= 3.37. Eseguire su un database nuovo.
-- Abilitare le foreign key anche su OGNI futura connessione JDBC.
PRAGMA foreign_keys = ON;
BEGIN;

CREATE TABLE manager (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL CHECK (length(trim(name)) > 0),
    email TEXT NOT NULL COLLATE NOCASE UNIQUE CHECK (length(trim(email)) > 0),
    -- Credenziale in chiaro per il prototipo, come richiesto. Nessun account nel seed.
    password TEXT NOT NULL CHECK (length(password) > 0),
    budget_eur INTEGER NOT NULL DEFAULT 1000000000 CHECK (budget_eur >= 0),
    created_at TEXT NOT NULL DEFAULT (strftime('%Y-%m-%dT%H:%M:%SZ', 'now'))
) STRICT;

CREATE TABLE club (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL UNIQUE CHECK (length(trim(name)) > 0),
    purchase_price_eur INTEGER NOT NULL CHECK (purchase_price_eur > 0),
    budget_eur INTEGER NOT NULL CHECK (budget_eur >= 0),
    -- NULL: disponibile. Un manager può possedere un club in questa prima versione.
    owner_manager_id INTEGER UNIQUE REFERENCES manager(id) ON DELETE RESTRICT
) STRICT;

CREATE TABLE roster_source (
    id INTEGER PRIMARY KEY,
    url TEXT NOT NULL UNIQUE,
    season TEXT NOT NULL,
    retrieved_on TEXT NOT NULL,
    notes TEXT NOT NULL DEFAULT ''
) STRICT;

CREATE TABLE team (
    id INTEGER PRIMARY KEY,
    club_id INTEGER NOT NULL REFERENCES club(id) ON DELETE RESTRICT,
    name TEXT NOT NULL CHECK (length(trim(name)) > 0),
    -- Nessun limite alla rosa; nuove categorie (es. U15) possono essere aggiunte.
    category TEXT NOT NULL DEFAULT 'FIRST' CHECK (length(trim(category)) > 0),
    roster_source_id INTEGER REFERENCES roster_source(id),
    UNIQUE (club_id, category)
) STRICT;

CREATE TABLE player (
    id INTEGER PRIMARY KEY,
    team_id INTEGER NOT NULL REFERENCES team(id) ON DELETE RESTRICT,
    name TEXT NOT NULL CHECK (length(trim(name)) > 0),
    -- Vuoto per i nomi sportivi unici (es. Giovane): non inventiamo un cognome.
    surname TEXT NOT NULL DEFAULT '',
    age INTEGER NOT NULL CHECK (age BETWEEN 0 AND 120),
    role TEXT NOT NULL CHECK (role IN ('POR', 'DIF', 'CEN', 'ATT')),
    overall INTEGER NOT NULL CHECK (overall BETWEEN 1 AND 100),
    overall_is_estimated INTEGER NOT NULL DEFAULT 1 CHECK (overall_is_estimated IN (0, 1)),
    source_id INTEGER REFERENCES roster_source(id)
) STRICT;

CREATE INDEX idx_player_team ON player(team_id);
CREATE INDEX idx_player_source ON player(source_id);
CREATE INDEX idx_team_roster_source ON team(roster_source_id);

CREATE TABLE game_state (
    id INTEGER PRIMARY KEY CHECK (id = 1),
    game_date TEXT NOT NULL,
    seed_version TEXT NOT NULL,
    rating_method TEXT NOT NULL,
    economy_note TEXT NOT NULL
) STRICT;

PRAGMA user_version = 1;
COMMIT;
