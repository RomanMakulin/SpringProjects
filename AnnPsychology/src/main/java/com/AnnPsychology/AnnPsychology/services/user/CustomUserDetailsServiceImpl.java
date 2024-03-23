package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.config.CustomUserDetails;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


/**
 * Кастомный сервис управления пользователями
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService, iUserDetailsService {
    /**
     * Репозиторий взаимодействия с Базой Данных
     */
    private final UserRepository userRepository;

    /**
     * Определить пользователя по email.
     * Метод в рамках реализации Spring Security
     *
     * @param email уникальная почта пользователя
     * @return найденный пользователь класса UserDetails
     * @throws UsernameNotFoundException Ошибка при определении (нахождении) пользователя
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findAll().stream().filter(item -> item.getEmail().equals(email)).findAny();
        return user.map(CustomUserDetails::new).
                orElseThrow(() -> new UsernameNotFoundException(email + " not found!"));
    }

    /**
     * Создание (регистрация) нового пользователя в системе
     *
     * @param userDetails принимаемые внешние параметры пользователя
     * @return логическое значение выполнения метода
     */
    @Override
    public boolean createUser(User userDetails) {
        if (userRepository.getByEmail(userDetails.getEmail()) != null) return false;
        User user = new User();
        userRepository.save(setUserDetails(userDetails, user));
        return true;
    }

    /**
     * Обновление ползователя
     *
     * @param id          ID пользователя
     * @param userDetails принимаемые внешние параметры пользователя
     */
    @Override
    public void updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.save(setUserDetails(user, userDetails));
    }

    /**
     * Метод задания новых параметров (полей) пользователю
     *
     * @param userDetails принимаемые внешние параметры пользователя
     * @return новый пользователь
     */
    public User setUserDetails(User userDetails, User user) {
        user.setUsername(userDetails.getUsername());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDetails.getPassword()));
        user.setDateBirth(userDetails.getDateBirth());
        user.setSocialLink(userDetails.getSocialLink());
        user.setPhone(userDetails.getPhone());
        return user;
    }

    /**
     * Получить авторизованного (текущего) пользователя
     *
     * @return пользователь
     */
    @Override
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        loadUserByUsername(userDetails.getEmail());
        return userRepository.findById(userDetails.getUser().getId()).orElseThrow();
    }

}
