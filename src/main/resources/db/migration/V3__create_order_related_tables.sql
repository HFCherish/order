CREATE TABLE orderItems (
  order_id VARCHAR(255),
  product_id VARCHAR(255) UNIQUE,
  quantity INTEGER,
  amount DOUBLE,
  FOREIGN KEY (product_id) REFERENCES products(id),
  FOREIGN KEY (order_id) REFERENCES orders(id),
  UNIQUE (order_id, product_id)
);

CREATE TABLE orders (
  id VARCHAR (255) PRIMARY KEY,
  user_id VARCHAR (255) NOT NULL,
  name VARCHAR (255),
  address VARCHAR (255) NOT NULL,
  phone VARCHAR (255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);


