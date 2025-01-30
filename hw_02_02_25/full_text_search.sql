CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	description VARCHAR(50) NOT NULL
);

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

ALTER TABLE products
ADD COLUMN searchable tsvector
GENERATED ALWAYS AS (to_tsvector('english', name || ' ' || description)) stored;

CREATE INDEX products_search_idx ON products USING GIN(searchable);

SELECT id, name, description, 
	ts_rank(searchable, to_tsquery('grill')) as rank
FROM products
WHERE searchable @@ to_tsquery('grill')
ORDER BY rank DESC;