CREATE TABLE IF NOT EXISTS clients(
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL CHECK (first_name ~ '^[A-Za-z ,.''-]+$'),
	last_name VARCHAR(50) NOT NULL CHECK (last_name ~ '^[A-Za-z ,.''-]+$'),
	birth_date DATE NOT NULL CHECK (birth_date < CURRENT_DATE),
	phone_number VARCHAR(20) NOT NULL UNIQUE CHECK (phone_number ~ '^\+?[0-9 \-\(\)]{10,20}$'),
	address VARCHAR(50) NOT NULL CHECK (address ~ '[A-Za-z0-9''\.\-\s\,]+'),
	discount SMALLINT NOT NULL DEFAULT(0) CHECK (discount BETWEEN 0 AND 100)
);

CREATE TABLE IF NOT EXISTS positions (
	id SERIAL PRIMARY KEY,
	title VARCHAR(50) NOT NULL UNIQUE CHECK (title ~ '^[A-Za-z-]+( [A-Za-z-]+)*$')
);

CREATE TABLE IF NOT EXISTS workers (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL CHECK (first_name ~ '^[A-Za-z ,.''-]+$'),
	last_name VARCHAR(50) NOT NULL CHECK (last_name ~ '^[A-Za-z ,.''-]+$'),
	birth_date DATE NOT NULL CHECK (birth_date <= CURRENT_DATE - INTERVAL '18 years'),
	phone_number VARCHAR(20) NOT NULL UNIQUE CHECK (phone_number ~ '^\+?[0-9 \-\(\)]{10,20}$'),
	address VARCHAR(50) NOT NULL CHECK (address ~ '[A-Za-z0-9''\.\-\s\,]+'),
	position_id INTEGER NOT NULL REFERENCES positions(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS schedule_units (
	id SERIAL PRIMARY KEY,
	date DATE NOT NULL CHECK (date >= CURRENT_DATE),
	shift_start TIME NOT NULL,
	shift_end TIME NOT NULL CHECK (shift_end > shift_start),
	
	UNIQUE (date, shift_start, shift_end)
);

CREATE TABLE IF NOT EXISTS schedule_units_workers (
	id SERIAL PRIMARY KEY,
	schedule_unit_id INTEGER NOT NULL REFERENCES schedule_units(id) ON DELETE CASCADE,
	worker_id INTEGER NOT NULL REFERENCES workers(id),
	
	UNIQUE(schedule_unit_id, worker_id)
);

-- Normally would create extra table for this
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'product_type') THEN
        CREATE TYPE product_type AS ENUM ('drink', 'dessert');
    END IF;
END $$;

CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	price NUMERIC(5, 2) NOT NULL CHECK (price > 0),
	type product_type NOT NULL
);

DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'language') THEN
        CREATE TYPE language AS ENUM ('en', 'de', 'ua');
    END IF;
END $$;


CREATE TABLE IF NOT EXISTS product_translations (
	product_id INTEGER REFERENCES products(id) ON DELETE CASCADE,
	locale language NOT NULL DEFAULT('en'),
	translation VARCHAR(50) NOT NULL,
	
	PRIMARY KEY (product_id, locale)
);

CREATE TABLE IF NOT EXISTS orders (
	id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL REFERENCES clients(id),
	worker_id INTEGER NOT NULL REFERENCES workers(id),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	price SMALLINT NOT NULL CHECK(price >= 0)
);

CREATE TABLE IF NOT EXISTS orders_products (
	id SERIAL PRIMARY KEY,
	order_id INTEGER NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
	product_id INTEGER NOT NULL REFERENCES products(id),
	quantity SMALLINT NOT NULL DEFAULT(1) CHECK (quantity > 0),
	
	UNIQUE (order_id, product_id)
);