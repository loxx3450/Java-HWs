-- task1
-- 1.1
BEGIN;
	DO $$
	DECLARE 
		new_order_id INTEGER;
		new_drink_id INTEGER;
	BEGIN
		INSERT INTO orders (client_id, worker_id, order_date, price)
			VALUES (1, 7, CURRENT_DATE, 99.99)
		RETURNING id INTO new_order_id;
		
		INSERT INTO products (price, type)
			VALUES (9.99, 'drink')
		RETURNING id INTO new_drink_id;

		INSERT INTO orders_products (order_id, product_id) 
			VALUES (new_order_id, new_drink_id);
	END $$;
COMMIT;

-- 1.2
BEGIN;
	DO $$
	DECLARE 
		new_order_id INTEGER;
		new_dessert_id INTEGER;
	BEGIN
		INSERT INTO orders (client_id, worker_id, order_date, price)
			VALUES (1, 7, CURRENT_DATE, 99.99)
		RETURNING id INTO new_order_id;
		
		INSERT INTO products (price, type)
			VALUES (9.99, 'dessert')
		RETURNING id INTO new_dessert_id;

		INSERT INTO orders_products (order_id, product_id) 
			VALUES (new_order_id, new_dessert_id);
	END $$;
COMMIT;

-- 1.3
BEGIN;
	DO $$
	DECLARE 
		new_schedule_unit_id INTEGER;
	BEGIN
		INSERT INTO schedule_units (date, shift_start, shift_end)
			VALUES ('2025-02-03', '09:00:00', '17:00:00')
		RETURNING id INTO new_schedule_unit_id;
		
		INSERT INTO schedule_units_workers (schedule_unit_id, worker_id) VALUES 
			(new_schedule_unit_id, 7),
			(new_schedule_unit_id, 8);
	END $$;
COMMIT;

-- 1.4
BEGIN;
	DO $$
	DECLARE 
		new_drink_id INTEGER;
	BEGIN
		INSERT INTO products (price, type) 
			VALUES (9.99, 'drink')
		RETURNING id INTO new_drink_id;
		
		INSERT INTO product_translations (product_id, locale, translation) VALUES 
			(new_drink_id, 'en', 'cappucino'),
			(new_drink_id, 'de', 'Cappucino');
	END $$;
COMMIT;

-- task 2
-- 2.1
UPDATE schedule_units
SET shift_start = shift_start + INTERVAL '1 hour'
WHERE id = 1;

-- 2.2
UPDATE product_translations
SET translation = 'new_cappucino2'
WHERE product_id = 8 
	AND locale = 'en' 
	AND EXISTS (
		SELECT 1 
	    FROM products
	    WHERE id = 8 AND type = 'drink'
	);

-- 2.3
UPDATE orders_products
SET quantity = quantity + 1
WHERE order_id = 6 AND product_id = 7;

-- 2.4
UPDATE product_translations
SET translation = 'new_tiramisu'
WHERE product_id = 9
	AND locale = 'en' 
	AND EXISTS (
		SELECT 1 
	    FROM products
	    WHERE id = 9 AND type = 'dessert'
	);
	
-- task 3
-- 3.1
-- FK in orders_products is ON DELETE CASCADE
DELETE 
FROM orders
WHERE id = 2

-- 3.2
DELETE 
FROM orders
WHERE id IN (
	SELECT order_id
	FROM orders_products
	WHERE product_id IN (
		SELECT id
		FROM products p 
			LEFT JOIN product_translations t ON p.id = t.product_id
		WHERE p.type = 'dessert' AND t.locale = 'en' AND t.translation = 'tiramisu'
	)
)

-- 3.3
-- FK in schedule_units_workers is ON DELETE CASCADE
DELETE 
FROM schedule_units
WHERE date = '2025-02-03';

-- 3.4
DELETE 
FROM schedule_units
WHERE date BETWEEN '2025-02-01' AND '2025-02-05';

-- task 4
-- i print everything (make extra joins) on purpose
-- 4.1
SELECT o.id as order_id, o.order_date, o.price, 
	c.id as client_id, c.first_name, c.last_name, c.birth_date, c.phone_number, c.address, c.discount,
	w.id as worker_id, w.first_name, w.last_name, w.birth_date, w.phone_number, w.address 
FROM orders o
	INNER JOIN clients c ON o.client_id = c.id
	INNER JOIN workers w ON o.worker_id = w.id
WHERE o.id IN (
	SELECT order_id
	FROM orders_products
	WHERE product_id IN (
		SELECT id
		FROM products p 
			LEFT JOIN product_translations t ON p.id = t.product_id
		WHERE p.type = 'dessert' AND t.locale = 'en' AND t.translation = 'tiramisu'
	)
);

-- 4.2
SELECT su.id as timetable_unit_id, su.date, su.shift_start, su.shift_end,
	w.id as worker_id, w.first_name, w.last_name, w.birth_date, w.phone_number, w.address
FROM schedule_units su 
	INNER JOIN schedule_units_workers suw ON suw.schedule_unit_id = su.id
	INNER JOIN workers w ON suw.worker_id = w.id
WHERE date = '2025-02-03';

-- 4.3
SELECT o.id as order_id, o.order_date, o.price, 
	c.id as client_id, c.first_name, c.last_name, c.birth_date, c.phone_number, c.address, c.discount,
	w.id as worker_id, w.first_name, w.last_name, w.birth_date, w.phone_number, w.address
FROM orders o
	INNER JOIN clients c ON o.client_id = c.id
	INNER JOIN workers w ON o.worker_id = w.id
WHERE worker_id = (
		SELECT w.id
		FROM workers w 
			INNER JOIN positions p ON w.position_id = p.id
		WHERE w.phone_number = '+1234567890' AND p.title = 'waiter'
	)
	
-- 4.4
SELECT o.id as order_id, o.order_date, o.price, 
	c.id as client_id, c.first_name, c.last_name, c.birth_date, c.phone_number, c.address, c.discount,
	w.id as worker_id, w.first_name, w.last_name, w.birth_date, w.phone_number, w.address
FROM orders o
	INNER JOIN clients c ON o.client_id = c.id
	INNER JOIN workers w ON o.worker_id = w.id
WHERE c.phone_number = '+1122334455'

-- task 5
-- i do not have a clue what this means