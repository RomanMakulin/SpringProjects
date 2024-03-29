
# Cards user transfer app

Привет! Представляю вашему вниманию проект, состоящий из нескольких микро-сервисов,  основная идея которого - это управление пользователями и действиями с их банковской карточкой. Приложение выполнено на базе  **серверной**  части (RESTfull) и  **клиентской**  (с использованием thymeleaf).

> Компоненты - модули (сервисы) приложения: server, client, gateway, eureka, UsersBaseApi

> Общий стек: Spring Web, Spring Data JPA, Spring Integration, H2 DB, Spring Security 6, Spring Cloud (Gateway, Eureka, OpenFeign), Grafana, Thymeleaf, Lombok, JUnit, Mockito, AOP, HTML 5, CSS 3, Bootstrap

**Общая инстркция по запуску сервиса:**
- Запустить 5 микросервисов;
- localhost:8765/server/load - рандомно загрузить 10 пользователей в БД;
- localhost:8765/main/user/{id} - личный кабинет пользователя с набором функций (все действия с транзакциями логируются в файл с помощью Spring Integration - "fileLog");

### 1) Сервер - ServerApi (REST).

> Стек технологий: Spring Web, Spring Data JPA, H2 DataBase, Hibernate, Lombok, JUnit, Mockito, AOP, Eureka client, Gateway, OpenFeign client

И так, у нас есть 2 главные сущности: пользователь и банковская карточка, которая у него есть. При регистрации нового пользователя, мы автоматически создаем и регистрируем в системе свою банковскую карту со своим пин-кодом.

Все операции с данными выполняются с использованием базы данных (репозиторий spring data jpa, hibernate, h2 database). В качестве инструмента разработчика была использована технология AOP (aspects, transactions - для корректной работы переводов).
Генерация пользователей происходит на стороне отдельного сервиса UsersBaseApi, к которому мы подключаемся как к внешнему API, берем данные и загружаем в собственнуб БД.

Также сервис покрыт тестами с использованием JUnit и технологии Mockito.

**Инструкция по работе с данными и запуску приложения:**

**users manage:**

```
Серверные REST запросы
- localhost:8765/server (PUT запрос) - создание нового пользователя. В тело запроса прописываем имя пользователя
и пин-код. "name", "pin". Остальные данные задаются автоматически;
- localhost:8765/server (GET запрос) - получение списка всех пользователей в базе данных;
- localhost:8765/server/{id} (DELETE запрос) - удаление пользователя по ID;
- localhost:8765/server/{id} (POST запрос) - обновление пользователя по ID. В тело запроса передаем поля:
"name", "cashMoney", "pin". Обновление наличных денег - функция для удобства в тестировании API;
- localhost:8765/server/update/{id} (POST запрос) - обновление имени пользователя по ID. Значение имени приходит от клиент сервиса;
- localhost:8765/server/{id} (GET запрос) - получение конкретного пользователя по ID;
```

**cards manage:**

```
- localhost:8765/server/recieve (POST запрос) - пополнение карточки пользователю. В тело запроса передается
"idUser", "money", "pin";
- localhost:8765/server/withdraw (POST запрос) - снятие денег с карточки в наличные. В тело запроса передается
"idUser", "money", "pin";
- localhost:8765/server/transfer (POST запрос) - перевод денег с карточки другому пользователю. 
В тело запроса передается "idSender", "idReciver", "moneyRecive" "pin".

```

### 2) UserClient - ClientAPI (WEB).

> Стек технологий: Spring Web, Thymeleaf, Lombok, Eureka client, Gateway, Open Feign, HTML + CSS

Клиентская часть реализует отрисовку наших web страниц при помощи шаблонизатора **Thymeleaf**, которые наполняются данными к ранее подключенному серверу, как к внешнему API.
А также принимает все данные с сервера, как по внешнему API с использованием **Spring Cloud OpenFeign**.

### 3) UsersBaseApi (REST).

Сервис, генерирующий рандомных пользователей.
> Стек технологий: Spring Web,  Lombok, JUnit, Mockito, Eureka client, Gateway

### 4) Spring Cloud Gateway.

Покдлючение Gateway Server, настройка конфига и маршрутизации.

### 5) Spring Cloud Eureka.

Подключение Spring Cloud Eureka, настройка конфига.
