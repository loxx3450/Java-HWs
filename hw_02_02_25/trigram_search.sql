CREATE EXTENSION IF NOT EXISTS pg_trgm;

-- to return more distant matches
SET pg_trgm.similarity_threshold = 0.2;

CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	description VARCHAR(50) NOT NULL
);

CREATE INDEX products_name_trgm_idx ON products USING GIN (name gin_trgm_ops);

INSERT INTO products (name, description) VALUES
-- Beverages
('Coffee', 'Strong espresso with a bold taste'),
('Tea', 'Classic black tea with lemon'),
('Green Tea', 'Fresh organic green tea leaves'),
('Latte', 'Smooth and creamy milk coffee'),
('Cappuccino', 'Espresso with steamed milk and foam'),
('Espresso', 'Bold espresso shot for energy'),
('Americano', 'Espresso with added hot water'),
('Mocha', 'Chocolate-flavored coffee drink'),
('Hot Chocolate', 'Warm cocoa with marshmallows'),
('Milkshake', 'Vanilla milkshake with whipped cream'),

-- Grilled items (5-7 mentions of "grill")
('Grilled Sandwich', 'Turkey and cheese sandwich with grill marks'),
('Grilled Pizza', 'Pizza with a crispy grilled crust'),
('Grilled Steak', 'Juicy steak with perfect grill marks'),
('Burger', 'Cheeseburger with a smoky grill taste'),
('Grilled Vegetables', 'Assorted veggies grilled to perfection'),
('Grils Salmon', 'Fresh salmon fillet with grill lines'),
('Toast', 'Buttered toast with a light grilling sear');

SELECT id, name, 
	similarity(name, 'grill') as sim_score
FROM products
WHERE name % 'grill'
ORDER BY sim_score DESC;