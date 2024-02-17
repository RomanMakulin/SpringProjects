package com.example.ServerAPI.services;

import com.example.ServerAPI.dto.user.UserCreateDetails;
import com.example.ServerAPI.dto.user.UserUpdateDetails;
import com.example.ServerAPI.models.Card;
import com.example.ServerAPI.models.Role;
import com.example.ServerAPI.models.User;
import com.example.ServerAPI.repository.UserRepository;
import com.example.ServerAPI.services.openFeignLoadUsers.GenerationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements iUserService {
    /**
     * Управление пользователями в БД через Spring data JPA
     */
    private final UserRepository userRepository;

    @Autowired
    private GenerationService generationService;

    /**
     * Получение всех пользователей из БД
     *
     * @return список пользователей
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Генерация рандомных пользователей
     */
    @Override
    public void loadUsers() {
        generationService.loadUsers().forEach(userRepository::save);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Логика создания нового пользователя
     *
     * @param userCreateDetails аргументы для создания нового пользователя из тела запроса
     * @return новый пользователь
     */
    @Override
    public User createNew(UserCreateDetails userCreateDetails) {
        User user = new User();
        user.setUsername(userCreateDetails.getName());
        user.setCard(new Card());
        user.getCard().setPin(userCreateDetails.getPin());
        user.setPassword(userCreateDetails.getPassword());
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return user;
    }

    /**
     * Логика удаления нового пользователя
     *
     * @param id уникальный идентификатор
     */
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Логика обновления нового пользователя
     *
     * @param userUpdateDetails аргументы для обновления пользователя из  тела запроса
     * @param id                уникальный идентификатор
     * @return обновленный пользователь
     */
    @Override
    public User updateUser(UserUpdateDetails userUpdateDetails, Long id) {
        Optional<User> needUser = userRepository.findById(id);
        User updUser = needUser.orElseThrow();

        updUser.setUsername(userUpdateDetails.getName());
        updUser.setCashMoney(userUpdateDetails.getCashMoney());
        updUser.getCard().setPin(userUpdateDetails.getPin());
        return userRepository.save(updUser);
    }

    /**
     * Обновление имени пользователя
     *
     * @param userDetails передаваемые детали от клиента
     * @param id          уникальный ID
     * @return обновленный пользователь
     */
    @Override
    public User updateNameUser(User userDetails, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userDetails.getUsername());
        userRepository.save(user);
        return user;
    }

    /**
     * Вторая реализация метода обновления пользователя
     *
     * @param user сущность с набором всех полей пользователя
     * @return обновленнный пользователь
     */
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
