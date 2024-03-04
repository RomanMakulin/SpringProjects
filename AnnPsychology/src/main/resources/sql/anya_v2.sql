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
    date_birth DATE NOT NULL,
    phone VARCHAR(300) NOT NULL,
    `password`VARCHAR(300) NOT NULL,
    user_role VARCHAR(30) NOT NULL,
    price DECIMAL NOT NULL
);

-- создаем таблицу сессий
CREATE TABLE sessions_date
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    session_date DATETIME
);

-- создаем таблицу сессий
CREATE TABLE user_session
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    session_price INT,
    session_link VARCHAR(305),
    payment_status BOOL,
    session_status VARCHAR(30),
    session_homework VARCHAR(1000),
    
    date_id INT NOT NULL, -- задаем айди группы 
    FOREIGN KEY (date_id)  REFERENCES sessions_date (id) ON DELETE SET NULL ON UPDATE SET NULL,
    
    user_id INT NOT NULL, -- задаем айди группы 
    FOREIGN KEY (user_id)  REFERENCES users (id) ON DELETE SET NULL ON UPDATE SET NULL
    
);


select * from users;
