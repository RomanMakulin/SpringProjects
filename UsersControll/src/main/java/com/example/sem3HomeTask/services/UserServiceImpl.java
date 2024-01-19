package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.DataBaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис управления пользователями
 * Имплемитирует UserService
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    /**
     * Автоинициализация в рамках бина класс Базы Данных
     */
    private DataBaseRepository dataBaseRepository;

    /**
     * Строковое отображение (маппер) из БД
     */
    private UserRowMapper rowMapper;

    /**
     * Автоинициализация в рамках бина класс уведомлений
     */
    private NotificationService notificationService;

    /**
     * Создание пользователя
     *
     * @param user пользователь
     * @return созданный пользователь
     */
    @Override
    public User createUser(User user) {
        String sql = "INSERT INTO userTable VALUES (DEFAULT, ?, ?, ?)";
        dataBaseRepository.getJdbc().update(sql, user.getFirstName(), user.getAge(), user.getEmail());
        notificationService.notifyUser(user);
        return user;
    }

    /**
     * Получение всех пользователей
     *
     * @return список пользователей
     */
    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM userTable";
        return dataBaseRepository.getJdbc().query(sql, rowMapper.rowMapperUser());
    }

    /**
     * Обновление пользователя
     *
     * @param user пользователь
     */
    @Override
    public void updateUser(User user) {
        String sql = "UPDATE userTable SET firstName = ?, age = ?, email = ? WHERE id = ?;";
        dataBaseRepository.getJdbc().update(sql, user.getFirstName(), user.getAge(), user.getEmail(), user.getId());
    }

    /**
     * Удаление пользователя
     *
     * @param id идентификатор пользователя
     */
    @Override
    public void delUser(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        dataBaseRepository.getJdbc().update(sql, id);
    }

    /**
     * Создание пользователя по нужным параметрам
     *
     * @param name  имя пользователя
     * @param age   возраст пользователя
     * @param email почта пользователя
     */
    public void createUserParam(String name, int age, String email) {
        String sql = "INSERT INTO userTable VALUES (DEFAULT, ?, ?, ?)";
        dataBaseRepository.getJdbc().update(sql, name, age, email);
    }
}
