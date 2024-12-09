INSERT INTO aeroport (nom, ville) VALUES ('CDG', 'Paris');
INSERT INTO aeroport (nom, ville) VALUES ('JFK', 'New York');
INSERT INTO aeroport (nom, ville) VALUES ('DTW', 'Detroit');

-- Vol 1 : CDG -> JFK
INSERT INTO vol (escale, prix, place_disponible, date_depart, date_arrive, aeroport_depart_id, aeroport_arrive_id)
VALUES (true, 25, 10, '2024-12-10 14:00:00', '2024-12-10 20:00:00',
        (SELECT id FROM aeroport WHERE nom = 'CDG'),
        (SELECT id FROM aeroport WHERE nom = 'JFK'));

-- Vol 2 : CDG -> DTW
INSERT INTO vol (escale, prix, place_disponible, date_depart, date_arrive, aeroport_depart_id, aeroport_arrive_id)
VALUES (false, 25, 50, '2024-12-15 09:00:00', '2024-12-15 11:30:00',
        (SELECT id FROM aeroport WHERE nom = 'CDG'),
        (SELECT id FROM aeroport WHERE nom = 'DTW'));

-- Vol 3 : JFK -> CDG
INSERT INTO vol (escale, prix, place_disponible, date_depart, date_arrive, aeroport_depart_id, aeroport_arrive_id)
VALUES (true, 25, 25, '2024-12-20 22:00:00', '2024-12-21 08:00:00',
        (SELECT id FROM aeroport WHERE nom = 'JFK'),
        (SELECT id FROM aeroport WHERE nom = 'CDG'));

-- Vol 4 : JFK -> DTW
INSERT INTO vol (escale, prix, place_disponible, date_depart, date_arrive, aeroport_depart_id, aeroport_arrive_id)
VALUES (false, 25, 30, '2024-12-18 13:00:00', '2024-12-18 21:00:00',
        (SELECT id FROM aeroport WHERE nom = 'JFK'),
        (SELECT id FROM aeroport WHERE nom = 'DTW'));

-- Vol 5 : DTW -> CDG
INSERT INTO vol (escale, prix, place_disponible, date_depart, date_arrive, aeroport_depart_id, aeroport_arrive_id)
VALUES (false, 25, 40, '2024-12-25 06:00:00', '2024-12-25 18:00:00',
        (SELECT id FROM aeroport WHERE nom = 'DTW'),
        (SELECT id FROM aeroport WHERE nom = 'CDG'));

-- Vol 6 : DTW -> JFK
INSERT INTO vol (escale, prix, place_disponible, date_depart, date_arrive, aeroport_depart_id, aeroport_arrive_id)
VALUES (false, 25, 20, '2024-12-31 16:00:00', '2025-01-01 02:00:00',
        (SELECT id FROM aeroport WHERE nom = 'DTW'),
        (SELECT id FROM aeroport WHERE nom = 'JFK'));