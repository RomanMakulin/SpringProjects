DROP DATABASE anya;

-- создаем базу данных под названием "anya"
CREATE DATABASE anya;

-- выбираем базу данных для дальнейшей работы с запросами
use anya;

-- создаем таблицу пользователей
CREATE TABLE users
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(55) NOT NULL,
    last_name VARCHAR(55) NOT NULL,
    email VARCHAR(200) NOT NULL,
    social_link VARCHAR(300) NOT NULL,
    date_birth DATE,
    `password`VARCHAR(300) NOT NULL,
    user_role VARCHAR(30) NOT NULL
);

-- создаем таблицу сессий
CREATE TABLE user_session
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    session_title VARCHAR(25),
    session_description VARCHAR(25),
    session_price INT,
    session_date DATE,
    session_link VARCHAR(305),
    payment_status BOOL,
    session_status VARCHAR(30),
    
    user_id INT NOT NULL, -- задаем айди группы 
    FOREIGN KEY (user_id)  REFERENCES users (id) ON DELETE CASCADE
    
);

select * from users;
