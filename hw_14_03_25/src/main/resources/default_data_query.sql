-- Landlords
INSERT INTO users (full_name, email, phone, role, is_verified)
VALUES
    ('Landlord 1', 'landlord1@example.com', '111-111-1111', 'landlord', true),
    ('Landlord 2', 'landlord2@example.com', '111-111-1112', 'landlord', false),
    ('Landlord 3', 'landlord3@example.com', '111-111-1113', 'landlord', true),
    ('Landlord 4', 'landlord4@example.com', '111-111-1114', 'landlord', true),
    ('Landlord 5', 'landlord5@example.com', '111-111-1115', 'landlord', false);


-- Clients
INSERT INTO users (full_name, email, phone, role)
VALUES
    ('Client 1', 'client1@example.com', '222-222-2221', 'client'),
    ('Client 2', 'client2@example.com', '222-222-2222', 'client'),
    ('Client 3', 'client3@example.com', '222-222-2223', 'client'),
    ('Client 4', 'client4@example.com', '222-222-2224', 'client'),
    ('Client 5', 'client5@example.com', '222-222-2225', 'client'),
    ('Client 6', 'client6@example.com', '222-222-2226', 'client'),
    ('Client 7', 'client7@example.com', '222-222-2227', 'client'),
    ('Client 8', 'client8@example.com', '222-222-2228', 'client'),
    ('Client 9', 'client9@example.com', '222-222-2229', 'client'),
    ('Client 10', 'client10@example.com', '222-222-2230', 'client'),
    ('Client 11', 'client11@example.com', '222-222-2231', 'client'),
    ('Client 12', 'client12@example.com', '222-222-2232', 'client'),
    ('Client 13', 'client13@example.com', '222-222-2233', 'client'),
    ('Client 14', 'client14@example.com', '222-222-2234', 'client'),
    ('Client 15', 'client15@example.com', '222-222-2235', 'client'),
    ('Client 16', 'client16@example.com', '222-222-2236', 'client'),
    ('Client 17', 'client17@example.com', '222-222-2237', 'client'),
    ('Client 18', 'client18@example.com', '222-222-2238', 'client'),
    ('Client 19', 'client19@example.com', '222-222-2239', 'client'),
    ('Client 20', 'client20@example.com', '222-222-2240', 'client');

-- Flats
INSERT INTO flats (title, description, address, area, price, floor, rooms_count, landlord_id) VALUES
    ('Flat 1', 'Nice place 1', '1 Street A', 55.5, 500.0, 2, 2, 1),
    ('Flat 2', 'Nice place 2', '2 Street A', 60.0, 520.0, 3, 3, 2),
    ('Flat 3', 'Nice place 3', '3 Street A', 45.0, 480.0, 1, 1, 3),
    ('Flat 4', 'Nice place 4', '4 Street A', 70.0, 600.0, 4, 2, 4),
    ('Flat 5', 'Nice place 5', '5 Street A', 65.0, 550.0, 2, 2, 5),
    ('Flat 6', 'Nice place 6', '6 Street B', 52.5, 510.0, 3, 1, 1),
    ('Flat 7', 'Nice place 7', '7 Street B', 66.0, 560.0, 5, 2, 2),
    ('Flat 8', 'Nice place 8', '8 Street B', 61.0, 530.0, 2, 3, 3),
    ('Flat 9', 'Nice place 9', '9 Street B', 59.0, 525.0, 1, 2, 4),
    ('Flat 10', 'Nice place 10', '10 Street B', 68.0, 570.0, 4, 2, 5),
    ('Flat 11', 'Nice place 11', '11 Street C', 50.0, 490.0, 2, 1, 1),
    ('Flat 12', 'Nice place 12', '12 Street C', 72.0, 610.0, 3, 3, 2),
    ('Flat 13', 'Nice place 13', '13 Street C', 58.0, 540.0, 1, 1, 3),
    ('Flat 14', 'Nice place 14', '14 Street C', 64.0, 555.0, 5, 2, 4),
    ('Flat 15', 'Nice place 15', '15 Street C', 69.0, 580.0, 3, 2, 5),
    ('Flat 16', 'Nice place 16', '16 Street D', 63.0, 545.0, 2, 3, 1),
    ('Flat 17', 'Nice place 17', '17 Street D', 60.0, 530.0, 4, 2, 2),
    ('Flat 18', 'Nice place 18', '18 Street D', 62.0, 535.0, 1, 2, 3),
    ('Flat 19', 'Nice place 19', '19 Street D', 67.0, 565.0, 5, 3, 4),
    ('Flat 20', 'Nice place 20', '20 Street D', 71.0, 600.0, 3, 2, 5);

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
    (1, 11, '2024-04-01', NULL);

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
