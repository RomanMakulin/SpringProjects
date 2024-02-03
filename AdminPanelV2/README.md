# Admin Panel v.2

Привет! Представляю вам вторую версию приложения **"Admin Panel"**.
Данная версия считается расширенной, так как в ней добавлены роли (**user**, **admin**) и для каждой свой личный кабинет с различным функционалом.

> **Стек технологий:** Spring Security, Spring Data JPA, Lombok, H2 Database, Spring Web, Hibernate, Thymeleaf, HTML5, CSS3.
*База данных - H2 так как Docker для поднятия БД не используется.*

**Инструкция к выполнению:**

Есть 2 роли: ROLE_ADMIN, ROLE_USER. У каждого типа пользователей свой точки доступа в нашем приложении, свой личный кабинет и разный функционал.

При запуске приложения, в нашу базу данных H2 автоматически добавляется один администратор для управления и демонстрацией. **Данные администратора - login: Roman, password: 123**.

- **localhost:8080/** - главная страница (выбор: вход/регистрация);
- **localhost:8080/admin** - после успешной авторизации пользователя с ролью "ROLE_ADMIN" он будет автоматически перенаправлен в свой личный кабинет, где можно управлять всеми пользователями и видеть о них всю подробную информацию (ВНИМАНИЕ! Удалить самого себя нельзя, на это стоит ограничение);
- **localhost:8080/user** - аналогично администратору, пользователь с ролью "ROLE_USER" попадает свой ограниченный личный кабинет

При попытке подключения неавторизованного пользователя к существующим страницам - он будет перенаправляться на классическую страницу входа в систему.

*p.s. у пользователя есть поле "image". При его регистрации и обновлении, необходимо указывать url любой картинки, это и будет Вашим аватаром.*

---

Hi! I present you the second version of **"Admin Panel "** application.
This version is considered to be an extended version, as it has added roles (**user**, **admin**) and each of them has its own personal cabinet with different functionality.

> **Technology stack:** Spring Security, Spring Data JPA, Lombok, H2 Database, Spring Web, Hibernate, Thymeleaf, HTML5, CSS3.
**Database is H2 since Docker is not used to bring up the database.

**Instructions for execution:**

There are 2 roles: ROLE_ADMIN, ROLE_USER. Each type of user has a different access point in our application, a different personal account and different functionality.

When we start the app, one admin is automatically added to our H2 database for management and demo. **Admin Data - login: Roman, password: 123**.

- **localhost:8080/** - home page (choice: login/register);
- **localhost:8080/admin** - after successful authorization of the user with the role "ROLE_ADMIN" he will be automatically redirected to his personal cabinet, where you can manage all users and see all detailed information about them (ATTENTION! You cannot delete yourself, there is a restriction on it);
- **localhost:8080/user** - similar to the administrator, the user with the role "ROLE_USER" will be directed to his/her restricted personal cabinet.

When an unauthorized user tries to connect to existing pages - he will be redirected to the classic login page.


*p.s. user has a field "image". When registering and updating it, it is necessary to specify the url of any picture, this will be your avatar.*