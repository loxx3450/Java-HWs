CREATE TYPE meal_time AS ENUM (
    'BREAKFAST', 'LUNCH', 'DINNER',
    'SUPPER', 'SNACK', 'ANYTIME'
);
CREATE TYPE food_type AS ENUM (
    'MEAT', 'FISH', 'VEGETARIAN', 'VEGAN', 'DESSERT',
    'SOUP', 'SALAD', 'FAST_FOOD', 'OTHER'
);
CREATE TYPE country AS ENUM (
    'USA', 'CANADA', 'UK', 'FRANCE', 'GERMANY', 'ITALY', 'SPAIN',
    'AUSTRALIA', 'JAPAN', 'CHINA', 'INDIA', 'BRAZIL', 'MEXICO', 'OTHER'
);


CREATE TABLE recipes (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(100) NOT NULL,
    receipt TEXT NOT NULL,
    link VARCHAR(255) NOT NULL,
    origin_country country NOT NULL,
    meal_time meal_time NOT NULL,
    type food_type NOT NULL,
    popularity INTEGER NOT NULL DEFAULT 0
);


CREATE TABLE ingredients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);


CREATE TABLE recipes_ingredients (
    recipe_id INTEGER REFERENCES recipes(id),
    ingredient_id INTEGER REFERENCES ingredients(id),

    PRIMARY KEY (recipe_id, ingredient_id)
);


-- JDBC AUTHENTICATION
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL
);

