package com.example.ServerAPI.services;

import com.example.ServerAPI.dto.user.UserCreateDetails;
import com.example.ServerAPI.dto.user.UserUpdateDetails;
import com.example.ServerAPI.models.Card;
import com.example.ServerAPI.models.User;
import com.example.ServerAPI.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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

    /**
     * Получение всех пользователей из БД
     *
     * @return список пользователей
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
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
