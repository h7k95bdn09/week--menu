CREATE TABLE IF NOT EXISTS houses (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50) NOT NULL,  
     image_name VARCHAR(255),
     description VARCHAR(255) NOT NULL,
     price INT NOT NULL,
     capacity INT NOT NULL,
     postal_code VARCHAR(50) NOT NULL,
     address VARCHAR(255) NOT NULL,
     phone_number VARCHAR(50) NOT NULL,
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 );
 
 CREATE TABLE IF NOT EXISTS food (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50) NOT NULL,  
     price INT NOT NULL,
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 );
 
 CREATE TABLE IF NOT EXISTS recipe (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50) NOT NULL, 
     image_name VARCHAR(255) NOT NULL, 
     how_to VARCHAR(255) NOT NULL,
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 );
 
 CREATE TABLE IF NOT EXISTS recipe_food (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     recipe_id INT NOT NULL,
     food_id INT NOT NULL,  
     piece INT NOT NULL,
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 	 FOREIGN KEY (recipe_id) REFERENCES recipe (id),
 	 FOREIGN KEY (food_id) REFERENCES food (id)
 );
 

CREATE OR REPLACE VIEW recipe_summary AS
    SELECT 
        recipe_id,
        r.name,
        r.image_name,
        SUM(f.price * rf.piece) AS total
    FROM
        recipe_food rf
            LEFT OUTER JOIN
        recipe r ON (rf.recipe_id = r.id)
            LEFT OUTER JOIN
        food f ON (rf.food_id = f.id)
    GROUP BY rf.recipe_id , r.name , r.image_name
 ;