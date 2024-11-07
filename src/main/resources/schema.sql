CREATE TABLE pets (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50),
                      birth_date DATE,
                      type_id INT,
                      owner_id INT
);
