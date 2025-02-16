CREATE TYPE shop_type AS ENUM (
    'GROCERY',
    'HOUSEHOLD',
    'SPORTS',
    'ELECTRONICS',
    'TOYS',
    'OTHER'
);

CREATE TABLE shops (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL check ( phone ~ '^\+?[0-9]{10,15}$' ),
    email VARCHAR(100) NOT NULL,
    website VARCHAR(100) NOT NULL check( website ~ '^(http(s)?://)?(www\.)?[a-zA-Z0-9-]+\.[a-zA-Z]{2,6}(/\S*)?$' ),
    type shop_type NOT NULL,
    description VARCHAR(500) NOT NULL,
    logotype_path VARCHAR(255) NOT NULL
);