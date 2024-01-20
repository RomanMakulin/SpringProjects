package com.example.sem3HomeTask.services.users;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.DataBaseRepository;
import com.example.sem3HomeTask.services.NotificationService;
import com.example.sem3HomeTask.services.db.cfgSql;
import com.example.sem3HomeTask.services.db.RowMapperService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис управления пользователями
 * Имплемитирует UserService
 */
@AllArgsConstructor
@Data
@Service
public class UserServiceImpl implements UserService {
    /**
     * Автоинициализация в рамках бина класс Базы Данных
     */
    private DataBaseRepository dataBaseRepository;

    /**
     * Строковое отображение (маппер) из БД
     */
    private RowMapperService rowMapper;

    /**
     * Автоинициализация в рамках бина класс уведомлений
     */
    private NotificationService notificationService;

    /**
     * Файл конфигурации Базы Данных (содержит все запросы)
     */
    private cfgSql cfgSql;


    /**
     * Создание пользователя
     *
     * @param user пользователь
     * @return созданный пользователь
     */
    @Override
    public User createUser(User user) {
        dataBaseRepository.getJdbc().update(cfgSql.getCreateUser(),
                user.getFirstName(),
                user.getAge(),
                user.getEmail(),
                user.getPassword());
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
        return dataBaseRepository.getJdbc().query(cfgSql.getAllUsers(), rowMapper.rowMapperUser());
    }

    /**
     * Обновление пользователя
     *
     * @param user пользователь
     */
    @Override
    public void updateUser(User user) {
        dataBaseRepository.getJdbc().update(cfgSql.getUpdateUserByID(),
                user.getFirstName(),
                user.getAge(),
                user.getEmail(),
                user.getPassword(),
                user.getId());
    }

    /**
     * Удаление пользователя
     *
     * @param id идентификатор пользователя
     */
    @Override
    public void delUser(int id) {
        dataBaseRepository.getJdbc().update(cfgSql.getDelUserByID(), id);
    }

    /**
     * Создание пользователя по нужным параметрам
     *
     * @param name  имя пользователя
     * @param age   возраст пользователя
     * @param email почта пользователя
     */
    public void createUserParam(String name, int age, String email, String password) {
        dataBaseRepository.getJdbc().update(cfgSql.getCreateUser(), name, age, email, password);
    }
}
