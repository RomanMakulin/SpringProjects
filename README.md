# Демонстрационные проекты
Добрый день! В данном репозитории представлены мои актуальные демонстрационные проекты.

**Мой стек технологий:**
> Java Core, JDK, Spring Framework, Spring Boot, Spring Data JPA, Spring Security, Spring Cloud (Gateway, Eureka, OpenFeign), Grafana, Thymeleaf, MySQL, PostgreSQL, H2, JDBC, Hibernate, Reflection API, Docker, Ubuntu, PuTTY GIT, JUnit, Mockito, Serialization & Externalizable, HTML 5, CSS 3, SCSS, SASS, JavaScript, DOM, Node JS, NPM, Bootstrap;

___

### **Ann Psychology Web-service**   
> Разработал проект для психолога-специалиста в рамках фриланс заказа для упрощения взаимодействия с его клиентами. Есть главная страница с описанием услуг, вход личный кабинет (админ панель и ЛК пользователя) с возможностью регистрации.

- Реализовал в полном объеме весь функционал по ТЗ, самостоятельно выстроил логику и архитектуру приложения в соответствии в паттернами и принципами ООП;
- Разработал уникальный дизайн (макет) и верстку (frontend) по нему с использованием технологий: CSS3, HTML5, SASS
- Реализовал систему аутентификации и регистрации пользователей с помощью Spring Security;
- Разработал панель администратора:
    - реализовал календарь с возможностью выбора окон с датами и планирования рабочего времени
    - реализовал гибкое управление пользователями и их сессиями
    - реализовал систему контроля и выдачи домашних заданий
    - реализовал систему аналитики для отслеживания необходимой статистики
- Разработал панель администратора:
    - реализовал функционал записи на сессию и выбор свободных окон
    - реализовал подключение к API ЮКасса для системы оплаты сессий
    - реализовал гибкое управление собственными сессиями
- Арендовал VDS сервер. ОС - Ubuntu:
    - разработал и подключил базу данных MySQL
    - задеплоил java приложение на арендованный сервер
    - установил сервер Ngnix, подключил SSL протокол, настроил обратный прокси
    - присвоил купленное доменное имя [ann-novikova.ru](https://ann-novikova.ru/)

**Среднее кол-во пользователей в день ~ 40 чел.**
  
[Перейти к проекту](https://github.com/RomanMakulin/SpringProjects/tree/main/AnnPsychology)

``` Стек технологий: Spring Web, Spring Data JPA, MySQL, Spring Security, VDS Server, Hibernate, PuTTY, Ubuntu 22.04, Thymeleaf, Lombok, JUnit, Mockito, HTML 5, CSS 3 ```

___

### **UserCardsTransferApp**   
> Разработал микро-сервисный проект в fullstack подходе, основная идея которого - это администрирование пользователей, управление счетом (операции с банковской карточкой: пополнение, снятие, перевод средств другому юзеру, изменение пин-кода и т.д..) в личном кабинете .
> 
- Реализовал в полном объеме весь функционал по ТЗ, самостоятельно выстроил логику и архитектуру микро-сервисов в соответствии в паттернами и принципами ООП.
- Реализовал облачное пространство для микро-сервисов, используя технологии Spring Cloud;
- Самостоятельно разработал уникальную frontend часть с использованием технологии Thymeleaf, CSS3, HTML5
- Реализовал систему аутентификации и регистрации пользователей с помощью технологии Spring Security
- Реализовал имитацию работы БД на базе H2 и подключил Spring Data JPA
- Подключил логирование транзакций с помощью Spring Integration в отдельный файл
  
[Перейти к проекту](https://github.com/RomanMakulin/SpringProjects/tree/main/CardTransferApp)

``` Стек технологий: Spring Web, Spring Data JPA, Spring Integration, H2 DB, Spring Security 6, Spring Cloud (Gateway, Eureka, OpenFeign), Grafana, Hibernate, Thymeleaf, Lombok, JUnit, Mockito, AOP, HTML 5, CSS 3, Bootstrap ```

___

### **Admin Panel v.2**    (+ spring security)

> Разработал монолитный проект в fullstack подходе, основная идея которого - это управление пользователями (требует авторизации) в базе данных, в панели администратора.
Также имеется личный кабинет у каждого пользователя с возможностью управления им (редактирование персональных данных). У каждого пользователя есть аватар. Данный проект является частью коммерческой разработки (фриланс). Использована актуальная версия Spring Boot;
> 
- Реализовал в полном объеме весь функционал по ТЗ, самостоятельно выстроил логику и архитектуру проекта в соответствии в паттернами и принципами ООП;
- Самостоятельно разработал уникальную frontend часть с использованием технологии Thymeleaf, CSS3, HTML5, Bootstrap;
- Реализовал систему аутентификации и регистрации пользователей с помощью технологии Spring Security.

[Перейти к проекту](https://github.com/RomanMakulin/SpringProjects/tree/main/AdminPanelV2)

``` Стек технологий: Spring Security, Spring Data JPA, Lombok, H2 Database, Spring Web, Hibernate, Thymeleaf, HTML5, CSS3, Bootstrap ```

___

### **Scrum board**   

> Разработал монолитный проект в fullstack подходе, основная идея которого - Scrum доска, разделенная на 3 блока: TO DO, DOING, DONE. На эту доску заносятся пользователи из базы данных и для каждого можно создавать задачи, управлять их статусами, удалять и проводить аналитику. Данный проект является коммерческим (фриланс) и уже передан заказчику. Использована актуальная версия Spring Boot;
> 
- Реализовал в полном объеме весь функционал по ТЗ, самостоятельно выстроил логику и архитектуру проекта в соответствии в паттернами и принципами ООП;
- Самостоятельно разработал frontend часть с использованием технологии Thymeleaf, CSS3, HTML5;
- Разработал уникальный дизайн и структуру проекта.

[Демонстрация работы](https://www.youtube.com/watch?v=hu0x4bNrNXo) / 
[Перейти к проекту](https://github.com/RomanMakulin/SpringProjects/tree/main/Scrum-board)

``` Стек технологий: Thymeleaf, Spring Data JPA, Lombok, H2 Database, Hibernate, Spring Web, HTML + CSS + Bootstrap. ```

___

### **Notes Rest**   
[Перейти к проекту](https://github.com/RomanMakulin/SpringProjects/tree/main/NotesRest)

``` Стек технологий: Spring Data JPA, Lombok, H2 Database, Spring Web, hibernate. ```

___

### **Rick and Morty connection API**   
[Демонстрация работы](https://www.youtube.com/watch?v=jIqD4gj3N8s) / 
[Перейти к проекту](https://github.com/RomanMakulin/SpringProjects/tree/main/RickAndMortyApi)

``` Стек технологий: Lombok, Spring Web, Thymeleaf, HTML5, CSS3. ```

___

### **Trams (Маршруты)**   
Тестовое задание для Московского Транспорта (успешно сдано)
[Перейти к проекту](https://github.com/RomanMakulin/SpringProjects/tree/main/TramsRest)

``` Стек технологий: Spring Data JPA, Lombok, H2 Database, Spring Web, hibernate. ```

___

### **Admin Panel v.1**    
[Демонстрация работы](https://www.youtube.com/watch?v=mq4shToaQZg&t=21s) / 
[Перейти к проекту](https://github.com/RomanMakulin/SpringProjects/tree/main/AdminPanelV1)

``` Стек технологий: lombok, database h2, Spring framework, spring boot, thymeleaf, hibernate. ```

___

### **Naruto connection API**   
[Перейти к проекту](https://github.com/RomanMakulin/SpringProjects/tree/main/NarutoApi)

``` Стек технологий: Lombok, Spring Web, Thymeleaf, HTML5, CSS3. ```
