-- task 2
-- 2.1
INSERT INTO positions (title) VALUES 
	('admin'),
	('confectioner'),
	('waiter'),
	('barista');

-- 2.2
INSERT INTO workers (first_name, last_name, birth_date, phone_number, address, position_id)
	VALUES ('John', 'Doe', '1990-05-15', '+1234567890', '123 Main St, NY', (SELECT id FROM positions WHERE title = 'barista'));
	
-- 2.3
INSERT INTO workers (first_name, last_name, birth_date, phone_number, address, position_id)
	VALUES ('Alice', 'Smith', '1985-09-23', '+1987654321', '456 Elm St, LA', (SELECT id FROM positions WHERE title = 'confectioner'));
	
-- 2.4
INSERT INTO clients (first_name, last_name, birth_date, phone_number, address)
	VALUES ('Michael', 'Brown', '1992-07-10', '+1122334455', '789 Oak St, SF');
	
-- task 3
-- 3.1
UPDATE products
SET price = 99.99
WHERE id = (SELECT id 
			FROM products p 
				INNER JOIN product_translations t ON p.id = t.product_id 
			WHERE t.locale = 'en' AND t.translation = 'latte');
			
-- no info about which confectioner/barista -> will take the first one in the list
-- 3.2
UPDATE workers
SET address = '654 Cedar St, FL'
WHERE id = (SELECT w.id 
		    FROM workers w
		   		INNER JOIN positions p ON w.position_id = p.id
		   	WHERE p.title = 'confectioner'
		    LIMIT 1);

-- 3.3
UPDATE workers
SET phone_number = '+0987654321'
WHERE id = (SELECT w.id 
		    FROM workers w
		   		INNER JOIN positions p ON w.position_id = p.id
		   	WHERE p.title = 'barista'
		    LIMIT 1);

-- 3.4
-- phone_number is unique Field
UPDATE clients 
SET discount = 15
WHERE phone_number = '+1122334455';

-- task 4
-- 4.1
DELETE 
FROM products
WHERE id = (
    SELECT p.id 
    FROM products p
    	INNER JOIN product_translations t ON p.id = t.product_id
    WHERE p.type = 'dessert' AND t.locale = 'en' AND t.translation = 'tiramisu'
	LIMIT 1
);

-- 4.2
-- phone_number is unique Field
DELETE 
FROM workers 
WHERE id = (
	SELECT w.id
	FROM workers w
		INNER JOIN positions p ON w.position_id = p.id
	WHERE p.title = 'waiter' AND w.phone_number = '+0987654321'
	LIMIT 1
);

-- 4.3
DELETE 
FROM workers 
WHERE id = (
	SELECT w.id
	FROM workers w
		INNER JOIN positions p ON w.position_id = p.id
	WHERE p.title = 'barista' AND w.phone_number = '+0987654321'
	LIMIT 1
);

-- 4.4
-- phone_number is unique Field
DELETE 
FROM clients
WHERE phone_number = '+1122334455';

-- task 5
-- 5.1
-- only english translation (original) will be printed
SELECT p.id, t.translation, p.price
FROM products p
	INNER JOIN product_translations t ON p.id = t.product_id
WHERE p.type = 'drink' AND t.locale = 'en';

-- 5.2
SELECT p.id, t.translation, p.price
FROM products p
	INNER JOIN product_translations t ON p.id = t.product_id
WHERE p.type = 'dessert' AND t.locale = 'en';

-- 5.3
SELECT w.id, w.first_name, w.last_name, w.birth_date, w.phone_number, w.address, w.position_id, p.title 
FROM workers w
	INNER JOIN positions p ON w.position_id = p.id
WHERE p.title = 'barista'

-- 5.4
SELECT w.id, w.first_name, w.last_name, w.birth_date, w.phone_number, w.address, w.position_id, p.title 
FROM workers w
	INNER JOIN positions p ON w.position_id = p.id
WHERE p.title = 'waiter'