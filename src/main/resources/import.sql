-- INSERT INTO cart_activities (last_activity) VALUES (NOW());

-- INSERT INTO cart_items (description, amount) VALUES ('Producto 1', 10.99);
-- INSERT INTO cart_items (description, amount) VALUES ('Producto 2', 19.99);

-- Insertar registro en la tabla cart_activities y obtener el ID generado
--INSERT INTO cart_activities (last_activity) VALUES (NOW()) RETURNING activity_id;

-- Insertar registros en la tabla cart_items y obtener los IDs generados
--INSERT INTO cart_items (description, amount) VALUES ('Producto 1', 10.99) RETURNING cart_id;
--INSERT INTO cart_items (description, amount) VALUES ('Producto 2', 19.99) RETURNING cart_id;

-- Establecer la relación entre los registros en la tabla cart_activities_items
-- Utilizar los IDs generados en las sentencias anteriores
-- Insertar registros en la tabla cart_activities_items y utilizar los IDs generados en las otras tablas
--INSERT INTO cart_activities_items (activity_id, cart_id)
--SELECT
--    (SELECT activity_id FROM cart_activities WHERE last_activity = NOW() LIMIT 1),
--    (SELECT cart_id FROM cart_items WHERE description = 'Producto 1' AND amount = 10.99 LIMIT 1);

--INSERT INTO cart_activities_items (activity_id, cart_id)
--SELECT
--    (SELECT activity_id FROM cart_activities WHERE last_activity = NOW() LIMIT 1),
--    (SELECT cart_id FROM cart_items WHERE description = 'Producto 2' AND amount = 19.99 LIMIT 1);
