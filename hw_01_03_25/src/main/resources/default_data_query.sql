INSERT INTO ingredients (name) VALUES
    ('Tomato'),
    ('Cheese'),
    ('Chicken'),
    ('Garlic'),
    ('Onion'),
    ('Beef'),
    ('Lettuce'),
    ('Bread'),
    ('Pasta'),
    ('Egg'),
    ('Milk'),
    ('Butter');

INSERT INTO recipes (title, description, receipt, link, origin_country, meal_time, type, popularity) VALUES
    ('Spaghetti Bolognese', 'Classic Italian pasta dish', 'Cook beef, add tomato sauce, mix with pasta.', 'https://example.com/spaghetti', 'ITALY', 'DINNER', 'MEAT', 5),
    ('Chicken Curry', 'Spicy Indian chicken dish', 'Cook chicken with curry spices.', 'https://example.com/chicken-curry', 'INDIA', 'LUNCH', 'MEAT', 4),
    ('Vegetable Salad', 'Healthy mixed salad', 'Chop vegetables and mix.', 'https://example.com/salad', 'USA', 'SNACK', 'VEGETARIAN', 3),
    ('French Toast', 'Sweet breakfast toast', 'Dip bread in egg and fry.', 'https://example.com/french-toast', 'FRANCE', 'BREAKFAST', 'DESSERT', 5),
    ('Sushi Roll', 'Japanese sushi with rice and fish', 'Roll rice with seaweed and fish.', 'https://example.com/sushi', 'JAPAN', 'LUNCH', 'FISH', 5),
    ('Hamburger', 'Classic American burger', 'Grill beef, add cheese, and serve in a bun.', 'https://example.com/burger', 'USA', 'DINNER', 'FAST_FOOD', 4),
    ('Pumpkin Soup', 'Creamy pumpkin soup', 'Blend pumpkin with milk and cook.', 'https://example.com/pumpkin-soup', 'GERMANY', 'SUPPER', 'SOUP', 4);

INSERT INTO recipes_ingredients (recipe_id, ingredient_id) VALUES
-- Spaghetti Bolognese
(1, 2),  -- Cheese
(1, 5),  -- Onion
(1, 6),  -- Beef
(1, 9),  -- Pasta

-- Chicken Curry
(2, 3),  -- Chicken
(2, 4),  -- Garlic
(2, 5),  -- Onion

-- Vegetable Salad
(3, 1),  -- Tomato
(3, 7),  -- Lettuce

-- French Toast
(4, 8),  -- Bread
(4, 10), -- Egg
(4, 11), -- Milk

-- Sushi Roll
(5, 6),  -- Beef
(5, 2),  -- Cheese

-- Hamburger
(6, 6),  -- Beef
(6, 2),  -- Cheese
(6, 8),  -- Bread

-- Pumpkin Soup
(7, 11), -- Milk
(7, 12); -- Butter


INSERT INTO users (login, password, authority)
VALUES
    ('user', 'userpass', 'ROLE_USER'),
    ('admin', 'adminpass', 'ROLE_ADMIN');

