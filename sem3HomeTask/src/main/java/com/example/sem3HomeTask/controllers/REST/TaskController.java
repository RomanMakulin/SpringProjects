package com.example.sem3HomeTask.controllers.REST;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TasksService service;

    /**
     * Get запрос для получения списка запросов
     * @return список задач, которые можно реализовать в рамках приложения
     */
    @GetMapping
    public List<String> getAllTasks()
    {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return  tasks;
    }


    /**
     * Get запрос на сортировку пользователей
     * localhost:8080/tasks/sort
     * @return Отсортированный список пользователей
     */
    @GetMapping("/sort")
    public List<User> sortUsersByAge() {
        return service.sortUsersByAge(service.getRepository().getUsers());
    }

    /**
     * Get запрос на фильтрацию пользователей
     * localhost:8080/filter/{age}
     * @param age - передаваемый параметр (возраст)
     * @return отфильтрованный список пользователей
     */
    @GetMapping("/filter/{age}")
    public List<User> filterUsersByAge(@PathVariable int age){
        return service.filterUsersByAge(service.getRepository().getUsers(), age);
    }

    /**
     * Get запрос для подсчета среднего возраста пользователей
     * localhost:8080/tasks/calk
     * @return Средний возраст пользователей в строковом формате
     */
    @GetMapping("/calk")
    public String calculateAverageAge(){
        return service.calculateAverageAge(service.getRepository().getUsers());
    }

}
