-- Landlords
INSERT INTO users (full_name, email, phone, role)
VALUES
    ('Landlord 1', 'landlord1@example.com', '111-111-1111', 'landlord', true),
    ('Landlord 2', 'landlord2@example.com', '111-111-1112', 'landlord'),
    ('Landlord 3', 'landlord3@example.com', '111-111-1113', 'landlord', true),
    ('Landlord 4', 'landlord4@example.com', '111-111-1114', 'landlord'),
    ('Landlord 5', 'landlord5@example.com', '111-111-1115', 'landlord');


-- Clients
INSERT INTO users (full_name, email, phone, role, want_rooms)
VALUES
    ('Client 1', 'client1@example.com', '222-222-2221', 'client', 1),
    ('Client 2', 'client2@example.com', '222-222-2222', 'client', 2),
    ('Client 3', 'client3@example.com', '222-222-2223', 'client', 3),
    ('Client 4', 'client4@example.com', '222-222-2224', 'client', 4),
    ('Client 5', 'client5@example.com', '222-222-2225', 'client', 3),
    ('Client 6', 'client6@example.com', '222-222-2226', 'client', 2),
    ('Client 7', 'client7@example.com', '222-222-2227', 'client', 1),
    ('Client 8', 'client8@example.com', '222-222-2228', 'client', 2),
    ('Client 9', 'client9@example.com', '222-222-2229', 'client', 3),
    ('Client 10', 'client10@example.com', '222-222-2230', 'client', 4),
    ('Client 11', 'client11@example.com', '222-222-2231', 'client', 3),
    ('Client 12', 'client12@example.com', '222-222-2232', 'client', 2),
    ('Client 13', 'client13@example.com', '222-222-2233', 'client', 1),
    ('Client 14', 'client14@example.com', '222-222-2234', 'client', 2),
    ('Client 15', 'client15@example.com', '222-222-2235', 'client', 3),
    ('Client 16', 'client16@example.com', '222-222-2236', 'client', 4),
    ('Client 17', 'client17@example.com', '222-222-2237', 'client', 3),
    ('Client 18', 'client18@example.com', '222-222-2238', 'client', 2),
    ('Client 19', 'client19@example.com', '222-222-2239', 'client', 1),
    ('Client 20', 'client20@example.com', '222-222-2240', 'client', 2);


INSERT INTO clients (user_id) VALUES
    (6), (7), (8), (9), (10),
    (11), (12), (13), (14), (15),
    (16), (17), (18), (19), (20),
    (21), (22), (23), (24), (25),
    (26), (27), (28), (29), (30),
    (31), (32), (33), (34), (35);

-- Flats
INSERT INTO flats (title, description, address, area, price, floor, rooms_count, landlord_id, is_available) VALUES
    ('Flat 1', 'Nice place 1', '1 Street A', 55.5, 500.0, 2, 2, 1, true),
    ('Flat 2', 'Nice place 2', '2 Street A', 60.0, 520.0, 3, 3, 2, true),
    ('Flat 3', 'Nice place 3', '3 Street A', 45.0, 480.0, 1, 1, 3, true),
    ('Flat 4', 'Nice place 4', '4 Street A', 70.0, 600.0, 4, 2, 4, true),
    ('Flat 5', 'Nice place 5', '5 Street A', 65.0, 550.0, 2, 2, 5, true),
    ('Flat 6', 'Nice place 6', '6 Street B', 52.5, 510.0, 3, 1, 1, true),
    ('Flat 7', 'Nice place 7', '7 Street B', 66.0, 560.0, 5, 2, 2, true),
    ('Flat 8', 'Nice place 8', '8 Street B', 61.0, 530.0, 2, 3, 3, true),
    ('Flat 9', 'Nice place 9', '9 Street B', 59.0, 525.0, 1, 2, 4, true),
    ('Flat 10', 'Nice place 10', '10 Street B', 68.0, 570.0, 4, 2, 5, true),
    ('Flat 11', 'Nice place 11', '11 Street C', 50.0, 490.0, 2, 1, 1, true),
    ('Flat 12', 'Nice place 12', '12 Street C', 72.0, 610.0, 3, 3, 2, true),
    ('Flat 13', 'Nice place 13', '13 Street C', 58.0, 540.0, 1, 1, 3, true),
    ('Flat 14', 'Nice place 14', '14 Street C', 64.0, 555.0, 5, 2, 4, true),
    ('Flat 15', 'Nice place 15', '15 Street C', 69.0, 580.0, 3, 2, 5, true),
    ('Flat 16', 'Nice place 16', '16 Street D', 63.0, 545.0, 2, 3, 1, true),
    ('Flat 17', 'Nice place 17', '17 Street D', 60.0, 530.0, 4, 2, 2, true),
    ('Flat 18', 'Nice place 18', '18 Street D', 62.0, 535.0, 1, 2, 3, true),
    ('Flat 19', 'Nice place 19', '19 Street D', 67.0, 565.0, 5, 3, 4, true),
    ('Flat 20', 'Nice place 20', '20 Street D', 71.0, 600.0, 3, 2, 5, true);

-- Past and new rents for some clients

-- Client 6
INSERT INTO rents (flat_id, client_id, started_at, ended_at) VALUES
    (1, 6, '2024-01-01', '2024-02-15'),
    (2, 6, '2024-03-01', NULL);

-- Client 7
INSERT INTO rents (flat_id, client_id, started_at, ended_at) VALUES
    (3, 7, '2024-02-01', NULL);

-- Client 8
INSERT INTO rents (flat_id, client_id, started_at, ended_at) VALUES
    (4, 8, '2024-01-10', '2024-04-01'),
    (5, 8, '2024-04-10', NULL);

-- Client 9
INSERT INTO rents (flat_id, client_id, started_at, ended_at) VALUES
    (6, 9, '2024-03-05', NULL);

-- Client 10
INSERT INTO rents (flat_id, client_id, started_at, ended_at) VALUES
    (7, 10, '2024-03-10', NULL);

-- Client 11
INSERT INTO rents (flat_id, client_id, started_at, ended_at) VALUES
    (8, 11, '2024-02-20', '2024-03-20'),
    (9, 11, '2024-04-01', NULL);

-- Clients 12 to 20, one current rent each
INSERT INTO rents (flat_id, client_id, started_at, ended_at) VALUES
    (10, 12, '2024-04-01', NULL),
    (11, 13, '2024-04-01', NULL),
    (12, 14, '2024-04-01', NULL),
    (13, 15, '2024-04-01', NULL),
    (14, 16, '2024-04-01', NULL),
    (15, 17, '2024-04-01', NULL),
    (16, 18, '2024-04-01', NULL),
    (17, 19, '2024-04-01', NULL),
    (18, 20, '2024-04-01', NULL);
