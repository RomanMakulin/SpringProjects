package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.UserRepository;
import com.example.sem3HomeTask.repository.UserRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private DataBaseService dataBaseService;

    public DataBaseService getDataBaseService() {
        return dataBaseService;
    }

    public void setDataBaseService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public UserRepository getRepository() {
        return repository;
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Сортировка по возрасту
     * @param users передаем список пользователей
     * @return возвращаем отсортированный список пользователей
     */
    public List<User> sortUsersByAge(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    /**
     * Фильтрация по возрасту
     * @param users передаем список пользователей
     * @param age передаем параметр возраста для фильтрации
     * @return возвращаем отфильтрованный список пользователей
     */
    public List<User> filterUsersByAge(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    /**
     * Подсчет среднего возраста пользователей
     * @param users передаем список пользователей
     * @return получаем строковое сообщение со средним возрастом пользователей
     */
    public String calculateAverageAge(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0) + " = average age";
    }

    /**
     * Добавление пользователя в список
     * @param user пользователь из запроса
     */
    public void  addUserToList(User user)
    {
        repository.getUsers().add(user);
    }

    public void createUserDB(User user){
        dataBaseService.createToDB(user);
    }
}
