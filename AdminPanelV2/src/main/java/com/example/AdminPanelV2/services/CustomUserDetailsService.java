package com.example.AdminPanelV2.services;

import com.example.AdminPanelV2.config.CustomUserDetails;
import com.example.AdminPanelV2.models.Role;
import com.example.AdminPanelV2.models.User;
import com.example.AdminPanelV2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getByUsername(username);
        return user.map(CustomUserDetails::new).
                orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
    }

    //region CRUD

    /**
     * Получение всех пользователей из БД
     *
     * @return список пользователей
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Поиск пользователя по уникальному идентификатору
     *
     * @param id идентификатор пользователя
     * @return результат поиска - пользователь
     */
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Обновление пользователя в БД
     *
     * @param detailsUser параметры пользователя из frontend
     */
    public void updateUser(User detailsUser) {
        User user = getById(detailsUser.getId())
                .orElseThrow(() -> new UsernameNotFoundException(detailsUser.getUsername() + " not found!"));
        user.setUsername(detailsUser.getUsername());
        user.setAge(detailsUser.getAge());
        user.setPassword(new BCryptPasswordEncoder().encode(detailsUser.getPassword()));
        user.setEmail(detailsUser.getEmail());
        user.setRole(detailsUser.getRole());
        userRepository.save(user);
    }

    /**
     * Создание нового пользователя
     *
     * @param detailsUser параметры пользователя из frontend
     * @return новый пользователь
     */
    public User createUser(User detailsUser) {
        User user = new User();
        user.setUsername(detailsUser.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(detailsUser.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setAge(detailsUser.getAge());
        user.setEmail(detailsUser.getEmail());
        return userRepository.save(user);
    }

    /**
     * Создание администратора для REST запроса
     *
     * @param name     имя
     * @param email    почта
     * @param age      возраст
     * @param password пароль
     * @return новый администратор
     */
    public User creteAdmin(String name, String email, int age, String password) {
        User user = new User();
        user.setUsername(name);
        user.setAge(age);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setEmail(email);
        user.setRole(Role.ROLE_ADMIN);
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя из БД
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    //endregion

}
