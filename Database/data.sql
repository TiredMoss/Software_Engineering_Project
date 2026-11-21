-- Create a sample user
INSERT INTO users (name, email)
VALUES ('Test User', 'demo-shopping@example.com')
ON CONFLICT (email) DO NOTHING;

-- Create a shopping list for the user
INSERT INTO shopping_lists (user_id, list_name)
SELECT user_id, 'Weekly Groceries'
FROM users
WHERE email = 'demo-shopping@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM shopping_lists
      WHERE user_id = users.user_id
        AND list_name = 'Weekly Groceries'
  );

-- Add sample items to the list
INSERT INTO shopping_items
    (list_id, item_name, quantity)
SELECT
    sl.list_id,
    items.item_name,
    items.quantity
FROM shopping_lists sl
JOIN users u ON sl.user_id = u.user_id
CROSS JOIN (
    VALUES
        ('Milk', 2),
        ('Bread', 1),
        ('Eggs', 12)
) AS items(item_name, quantity)
WHERE u.email = 'demo-shopping@example.com'
  AND sl.list_name = 'Weekly Groceries'
  AND NOT EXISTS (
      SELECT 1
      FROM shopping_items si
      WHERE si.list_id = sl.list_id
        AND si.item_name = items.item_name
  );

  -- Create a sample user2
  INSERT INTO users (name, email)
  VALUES ('User2', 'demo2-shopping@example.com')
  ON CONFLICT (email) DO NOTHING;

  -- Create a shopping list for user2
  INSERT INTO shopping_lists (user_id, list_name)
  SELECT user_id, 'Birthday List'
  FROM users
  WHERE email = 'demo2-shopping@example.com'
    AND NOT EXISTS (
        SELECT 1
        FROM shopping_lists
        WHERE user_id = users.user_id
          AND list_name = 'Birthday List'
    );

  -- Add sample items to the list for the user2
  INSERT INTO shopping_items
      (list_id, item_name, quantity)
  SELECT
      sl.list_id,
      items.item_name,
      items.quantity
  FROM shopping_lists sl
  JOIN users u ON sl.user_id = u.user_id
  CROSS JOIN (
      VALUES
          ('Cake', 1),
          ('Candeles', 5),
          ('Cupcake', 12)
  ) AS items(item_name, quantity)
  WHERE u.email = 'demo2-shopping@example.com'
    AND sl.list_name = 'Birthday List'
    AND NOT EXISTS (
        SELECT 1
        FROM shopping_items si
        WHERE si.list_id = sl.list_id
          AND si.item_name = items.item_name
    );
