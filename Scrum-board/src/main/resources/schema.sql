CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    email varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title varchar(50) NOT NULL,
    description varchar(50) NOT NULL,
    task_status varchar(50) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


INSERT INTO users VALUES (DEFAULT, 'Roman', 'sup.makulin@mail.ru');
INSERT INTO users VALUES (DEFAULT, 'Olga', 'sadasdad@mail.ru');
INSERT INTO users VALUES (DEFAULT, 'Ann', '123gfg@mail.ru');

INSERT INTO tasks VALUES (DEFAULT, 'Test', 'Test descr', 'TO_DO', 1);