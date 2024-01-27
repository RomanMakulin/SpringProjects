# NotesRest

Привет! Представляю вам REST full приложение "NotesRest".

**Стек технологий:** Spring Data JPA, Lombok, H2 Database, Spring Web, hibernate.

Приложение использует ResponseEntity обертку над сущностью "Note".

Инструкция по **rest** запросам:
- localhost:8080/api - метод get покажет все заметки;
- localhost:8080/api - метод post создаст новую заметку по requestbody;
- localhost:8080/api - метод put обновит текущую заметку по requestbody;
- localhost:8080/api/id - метод delete удалит текущую заметку по id;
- localhost:8080/api/id - метод покажет заметку по id;

---

Hi! I present you REST full application "NotesRest".

**The technology stack is** Spring Data JPA, Lombok, H2 Database, Spring Web, hibernate.

The application uses ResponseEntity wrapper over the "Note" entity.

Instructions for **rest** requests:
- localhost:8080/api - get method will show all notes;
- localhost:8080/api - the post method will create a new note by requestbody;
- localhost:8080/api - put method will update the current note by requestbody;
- localhost:8080/api/id - delete method will delete the current note by id;
- localhost:8080/api/id - method show note by id;
