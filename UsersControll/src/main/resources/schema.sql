CREATE TABLE IF NOT EXISTS userTable (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName varchar(50) NOT NULL,
    age INT NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(50) NOT NULL
);

INSERT INTO userTable VALUES (DEFAULT, 'Roman Wayz', 25, 'sup.makulin@mail.ru', '12s');