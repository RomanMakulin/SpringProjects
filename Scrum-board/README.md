# Scrum Board

Привет! Представляю вам приложение "Scrum Board".

Приложение выполнено в REST и WEB представлениях.

**Стек технологий:** Thymeleaf, Spring Data JPA, Lombok, H2 Database, Hibernate, Spring Web, HTML + CSS + Bootstrap.

**WEB:**

Есть база данных h2, в которой уже инициализированы пользователи и какие то задачи для них. Наша доска активности имитирует реальную Scrum доску с возможностью управления задачами пользователя.

У каждого работника есть свой список задач, которыми можно управлять: менять статусы выполнения, перемещая в соответствующую зону на доске, редактировать, удалять и др... Каждая зона активности задачи (статус) подсвечивается своим цветом.

Сущности-таблицы "User" и "Task" связаны между собой каскадным методом (OneToMany & ManyToOne).

Вторая таблица на странице - это список всех пользователей в системе (в базе данных), отображающая более подробную статистику по каждому работнику (личные данные, количество активных задач). Также можно добавлять новые задачи выбранному пользователю.

**REST инструкция:**

Есть 2 контроллера - управление пользователями и задачами.

Задачи:
- localhost:8080/tasks - метод get покажет все задачи в БД;
- localhost:8080/tasks/give/id - метод post создаст новую задачу по заданному ID.

Пользователи:
- localhost:8080/users - метод get покажет все задачи в БД;
- localhost:8080/create-user  -метод post создаст нового пользователя;
- localhost:8080/update-user - метод post обновит пользователя
- localhost:8080/delete-user - метод post удалить пользователя;

Видео демонстрация: https://www.youtube.com/watch?v=hu0x4bNrNXo

---

# Scrum Board

Hi, I present you the application "Scrum Board".

The application is made in REST and WEB views.

**Technology Stack:** Thymeleaf, Spring Data JPA, Lombok, H2 Database, Hibernate, Spring Web, HTML + CSS + Bootstrap.

**WEB:**

We have a h2 database, which already has initialized users and some tasks for them. Our activity board mimics a real Scrum board with the ability to manage user tasks.

Each worker has his own list of tasks that can be managed: change the status of the task by moving it to the appropriate zone on the board, edit, delete, etc.... Each task activity zone (status) is highlighted with its own color.

Entity-tables "User" and "Task" are linked by cascading method (OneToMany & ManyToOne).

The second table on the page is a list of all users in the system (in the database), displaying more detailed statistics for each worker (personal data, number of active tasks). You can also add new tasks to the selected user.

**REST instruction:**

There are 2 controllers - user management and task management.

Tasks:
- localhost:8080/tasks - get method will show all tasks in the database;
- localhost:8080/tasks/give/id - the post method will create a new task by the given ID.

Users:
- localhost:8080/users - the get method will show all tasks in the database;
- localhost:8080/create-user - the post method will create a new user;
- localhost:8080/update-user - the post method will update the user.
- localhost:8080/delete-user - the post method will delete the user;

Video demonstration: https://www.youtube.com/watch?v=hu0x4bNrNXo