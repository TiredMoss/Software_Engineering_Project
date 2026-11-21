--view all shopping items
SELECT * FROM shopping_items;

--view all lists and their owners
SELECT
    u.name,
    sl.list_name
FROM users u
JOIN shopping_lists sl
    ON u.user_id = sl.user_id;

-- View shopping items with their list names
SELECT
    sl.list_name,
    si.item_name,
    si.quantity,
    si.is_purchased
FROM shopping_lists sl
JOIN shopping_items si
    ON sl.list_id = si.list_id;

-- Example: change Milk quantity to 4
 UPDATE shopping_items
 SET quantity = 4
 WHERE item_id = 1;

-- Example: mark Bread as purchased
 --UPDATE shopping_items
 --SET is_purchased = TRUE
 --WHERE item_id = 2;

-- Example: delete an item
 --DELETE FROM shopping_items
 --WHERE item_id = 3;