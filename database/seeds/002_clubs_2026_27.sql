-- Scritto dall'agente AI con deroga esplicita del 2026-09-14.
-- Catalogo squadre 2026/27: 20 A, 20 B, 60 C. Fonti verificate il 2026-09-14.
-- Eseguire dopo schema.sql + seed.sql + migrations/002_team_competition.sql.
-- Ripetibile: non sovrascrive nomi, prezzi, budget, proprietari o rose esistenti.
-- Nuovi club: prezzi/budget PROVVISORI DI GIOCO, non stime finanziarie:
-- A 100/20 milioni EUR, B 25/5 milioni EUR, C 5/1 milioni EUR.
-- Le seconde squadre appartengono al club della prima squadra.
-- I dati dei giocatori NON sono inclusi in questo seed.
PRAGMA foreign_keys = ON;
BEGIN IMMEDIATE;

CREATE TEMP TABLE catalog_2026_27 (
    ordinal INTEGER PRIMARY KEY,
    team_name TEXT NOT NULL UNIQUE,
    club_name TEXT NOT NULL,
    category TEXT NOT NULL,
    league TEXT NOT NULL,
    group_name TEXT,
    source_url TEXT NOT NULL
);

INSERT INTO catalog_2026_27 VALUES
(1, 'Atalanta', 'Atalanta', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(2, 'Bologna', 'Bologna', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(3, 'Cagliari', 'Cagliari', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(4, 'Como', 'Como', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(5, 'Fiorentina', 'Fiorentina', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(6, 'Frosinone', 'Frosinone', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(7, 'Genoa', 'Genoa', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(8, 'Inter', 'Inter', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(9, 'Juventus', 'Juventus', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(10, 'Lazio', 'Lazio', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(11, 'Lecce', 'Lecce', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(12, 'Milan', 'Milan', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(13, 'Monza', 'Monza', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(14, 'Napoli', 'Napoli', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(15, 'Parma', 'Parma', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(16, 'Roma', 'Roma', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(17, 'Sassuolo', 'Sassuolo', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(18, 'Torino', 'Torino', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(19, 'Udinese', 'Udinese', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(20, 'Venezia', 'Venezia', 'FIRST', 'A', NULL, 'https://www.legaseriea.it/serie-a/news/aspettando-il-calendario-della-serie-a-enilive-2026-27'),
(21, 'Arezzo', 'Arezzo', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(22, 'Ascoli', 'Ascoli', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(23, 'Avellino', 'Avellino', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(24, 'Benevento', 'Benevento', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(25, 'Carrarese', 'Carrarese', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(26, 'Catanzaro', 'Catanzaro', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(27, 'Cesena', 'Cesena', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(28, 'Cremonese', 'Cremonese', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(29, 'Empoli', 'Empoli', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(30, 'Hellas Verona', 'Hellas Verona', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(31, 'Juve Stabia', 'Juve Stabia', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(32, 'L.R. Vicenza', 'L.R. Vicenza', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(33, 'Mantova', 'Mantova', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(34, 'Modena', 'Modena', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(35, 'Padova', 'Padova', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(36, 'Palermo', 'Palermo', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(37, 'Pisa', 'Pisa', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(38, 'Sampdoria', 'Sampdoria', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(39, 'Südtirol', 'Südtirol', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(40, 'Virtus Entella', 'Virtus Entella', 'FIRST', 'B', NULL, 'https://www.legab.it/seriebkt/classifica'),
(41, 'AlbinoLeffe', 'AlbinoLeffe', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(42, 'Alcione Milano', 'Alcione Milano', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(43, 'Arzignano Valchiampo', 'Arzignano Valchiampo', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(44, 'Carpi', 'Carpi', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(45, 'Cittadella', 'Cittadella', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(46, 'Desenzano', 'Desenzano', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(47, 'Dolomiti Bellunesi', 'Dolomiti Bellunesi', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(48, 'Folgore Caratese', 'Folgore Caratese', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(49, 'Giana Erminio', 'Giana Erminio', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(50, 'Juventus Next Gen', 'Juventus', 'U23', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(51, 'Lecco', 'Lecco', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(52, 'Lumezzane', 'Lumezzane', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(53, 'Novara', 'Novara', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(54, 'Ospitaletto Franciacorta', 'Ospitaletto Franciacorta', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(55, 'Pergolettese', 'Pergolettese', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(56, 'Pro Vercelli', 'Pro Vercelli', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(57, 'Renate', 'Renate', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(58, 'Trento', 'Trento', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(59, 'Treviso', 'Treviso', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(60, 'Union Brescia', 'Union Brescia', 'FIRST', 'C', 'A', 'https://www.seriec.com/gironi/girone-a'),
(61, 'Atalanta U23', 'Atalanta', 'U23', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(62, 'Campobasso', 'Campobasso', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(63, 'Forlì', 'Forlì', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(64, 'Grosseto', 'Grosseto', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(65, 'Gubbio', 'Gubbio', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(66, 'Guidonia Montecelio', 'Guidonia Montecelio', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(67, 'Latina', 'Latina', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(68, 'Livorno', 'Livorno', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(69, 'Ostiamare', 'Ostiamare', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(70, 'Perugia', 'Perugia', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(71, 'Pescara', 'Pescara', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(72, 'Pianese', 'Pianese', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(73, 'Pineto', 'Pineto', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(74, 'Ravenna', 'Ravenna', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(75, 'Reggiana', 'Reggiana', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(76, 'Sambenedettese', 'Sambenedettese', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(77, 'Spezia', 'Spezia', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(78, 'Torres', 'Torres', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(79, 'Vado', 'Vado', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(80, 'Vis Pesaro', 'Vis Pesaro', 'FIRST', 'C', 'B', 'https://www.seriec.com/gironi/girone-b'),
(81, 'Audace Cerignola', 'Audace Cerignola', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(82, 'AZ Picerno', 'AZ Picerno', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(83, 'Bari', 'Bari', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(84, 'Barletta', 'Barletta', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(85, 'Casarano', 'Casarano', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(86, 'Casertana', 'Casertana', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(87, 'Catania', 'Catania', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(88, 'Cavese', 'Cavese', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(89, 'Cosenza', 'Cosenza', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(90, 'Crotone', 'Crotone', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(91, 'Foggia', 'Foggia', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(92, 'Giugliano', 'Giugliano', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(93, 'Inter U23', 'Inter', 'U23', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(94, 'Monopoli', 'Monopoli', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(95, 'Potenza', 'Potenza', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(96, 'Salernitana', 'Salernitana', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(97, 'Savoia', 'Savoia', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(98, 'Scafatese', 'Scafatese', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(99, 'Sorrento', 'Sorrento', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c'),
(100, 'Team Altamura', 'Team Altamura', 'FIRST', 'C', 'C', 'https://www.seriec.com/gironi/girone-c');

INSERT INTO club (name, purchase_price_eur, budget_eur)
SELECT club_name,
    CASE league WHEN 'A' THEN 100000000 WHEN 'B' THEN 25000000 ELSE 5000000 END,
    CASE league WHEN 'A' THEN 20000000 WHEN 'B' THEN 5000000 ELSE 1000000 END
FROM catalog_2026_27
WHERE category = 'FIRST'
ORDER BY ordinal
ON CONFLICT (name) DO NOTHING;

INSERT INTO team (club_id, name, category)
SELECT c.id, s.team_name, s.category
FROM catalog_2026_27 s JOIN club c ON c.name = s.club_name
WHERE 1
ORDER BY s.ordinal
ON CONFLICT (club_id, category) DO NOTHING;

INSERT INTO team_competition (season, team_id, league, group_name, source_url, verified_on)
SELECT '2026/27', t.id, s.league, s.group_name, s.source_url, '2026-09-14'
FROM catalog_2026_27 s
JOIN club c ON c.name = s.club_name
JOIN team t ON t.club_id = c.id AND t.category = s.category
WHERE 1
ON CONFLICT (season, team_id) DO NOTHING;

-- Interrompe la transazione se dati preesistenti sono incompatibili con il catalogo.
CREATE TEMP TABLE catalog_check (ok INTEGER NOT NULL CHECK (ok = 1));
INSERT INTO catalog_check
SELECT CASE WHEN COUNT(*) = 100 THEN 1 ELSE 0 END
FROM catalog_2026_27 s
JOIN club c ON c.name = s.club_name
JOIN team t ON t.club_id = c.id AND t.category = s.category AND t.name = s.team_name
JOIN team_competition tc ON tc.team_id = t.id AND tc.season = '2026/27'
    AND tc.league = s.league AND tc.group_name IS s.group_name;

INSERT INTO catalog_check
SELECT CASE WHEN COUNT(*) = 100 THEN 1 ELSE 0 END
FROM team_competition WHERE season = '2026/27';

UPDATE game_state SET seed_version = '2026-27-v2-clubs'
WHERE id = 1 AND seed_version = '2026-27-v1';

DROP TABLE catalog_check;
DROP TABLE catalog_2026_27;
COMMIT;
