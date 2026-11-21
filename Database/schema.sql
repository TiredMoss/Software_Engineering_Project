-- Table 1: Users
CREATE TABLE users (
    user_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Table 2: Shopping Lists
CREATE TABLE shopping_lists (
    list_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INTEGER NOT NULL,
    list_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);

-- Table 3: Shopping Items
CREATE TABLE shopping_items (
    item_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    list_id INTEGER NOT NULL,
    item_name VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 1,
    is_purchased BOOLEAN NOT NULL DEFAULT FALSE,

    FOREIGN KEY (list_id)
        REFERENCES shopping_lists(list_id)
        ON DELETE CASCADE,

    CONSTRAINT positive_quantity CHECK (quantity > 0)
);