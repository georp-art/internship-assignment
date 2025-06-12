-- Δημιουργία πίνακα warehouses
CREATE TABLE warehouses (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            address TEXT NOT NULL,
                            capacity DECIMAL(10,2) NOT NULL,
                            manager_name VARCHAR(100) NOT NULL,
                            created_at TIMESTAMP,
                            updated_at TIMESTAMP
);

-- Εισαγωγή δεδομένων στον πίνακα warehouses
INSERT INTO warehouses (name, address, capacity, manager_name, created_at, updated_at) VALUES
                                                                                           ('Athens warehouse', 'L.Khfisias 123, Athens 18236', 100000.00, 'John Papadopoulos', '2024-01-03', NOW()),
                                                                                           ('Patras warehouse', 'Korinthou 3543, Patra 16523', 10000.00, 'Maria Makri', '2022-05-01', '2023-12-30'),
                                                                                           ('Crete warehouse', 'BIPE Xaniwn, Xania 8976', 5000.00, 'Kostas Papadopoulos', '2021-01-03', NOW());

-- Δημιουργία πίνακα products
CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description VARCHAR(255) NOT NULL,
                          price DECIMAL(10,2) NOT NULL,
                          category VARCHAR(100) NOT NULL,
                          weight DECIMAL(10,2),
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP
);

-- Εισαγωγή δεδομένων στον πίνακα products
INSERT INTO products (name, description, price, category, weight, created_at, updated_at) VALUES
                                                                                              ('laptop lenovo', '14-inch business laptop ryzen 5', 650.00, 'Electronics', 1.70, '2024-03-01', '2025-02-01'),
                                                                                              ('usb charger', '65W fast charge', 23.00, 'Accessories', 0.20, '2024-03-01', NOW()),
                                                                                              ('wireless mouse', 'ergonomic wireless mouse', 30.00, 'Accessories', 0.20, '2023-03-01', NOW());

-- Δημιουργία πίνακα inventory
CREATE TABLE inventory (
                           id SERIAL PRIMARY KEY,
                           product_id INT NOT NULL,
                           warehouse_id INT NOT NULL,
                           quantity DECIMAL(10,2),
                           min_stock INT NOT NULL,
                           max_stock INT NOT NULL,
                           created_at TIMESTAMP DEFAULT NOW(),
                           updated_at TIMESTAMP DEFAULT NOW(),
                           FOREIGN KEY (product_id) REFERENCES products(id),
                           FOREIGN KEY (warehouse_id) REFERENCES warehouses(id),
                           UNIQUE (product_id, warehouse_id)
);

-- Εισαγωγή δεδομένων στον πίνακα inventory
INSERT INTO inventory (product_id, warehouse_id, quantity, min_stock, max_stock) VALUES
                                                                                     (1, 1, 50, 45, 100),
                                                                                     (2, 1, 10, 70, 200),
                                                                                     (1, 2, 10, 70, 200),
                                                                                     (3, 2, 5, 70, 150),
                                                                                     (3, 3, 15, 25, 50);
