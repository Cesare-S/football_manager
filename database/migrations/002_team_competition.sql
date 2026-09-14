PRAGMA foreign_keys = ON;
BEGIN IMMEDIATE;

-- Scritto dall'agente AI con deroga esplicita del 2026-09-14.
-- Il campionato appartiene alla squadra in una stagione, non al club.
-- category in team rimane FIRST/U23; non contiene A/B/C.
CREATE TABLE IF NOT EXISTS team_competition (
    season TEXT NOT NULL CHECK (length(trim(season)) > 0),
    team_id INTEGER NOT NULL REFERENCES team(id) ON DELETE RESTRICT,
    league TEXT NOT NULL CHECK (league IN ('A', 'B', 'C')),
    group_name TEXT,
    source_url TEXT NOT NULL CHECK (length(trim(source_url)) > 0),
    verified_on TEXT NOT NULL,
    PRIMARY KEY (season, team_id),
    CHECK ((league IN ('A', 'B') AND group_name IS NULL)
        OR (league = 'C' AND group_name IS NOT NULL AND group_name IN ('A', 'B', 'C')))
) STRICT;

CREATE INDEX IF NOT EXISTS idx_team_competition_league
    ON team_competition (season, league, group_name);

PRAGMA user_version = 2;
COMMIT;
