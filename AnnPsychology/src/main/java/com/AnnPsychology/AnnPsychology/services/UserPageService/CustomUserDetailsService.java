package com.AnnPsychology.AnnPsychology.services.UserPageService;

import com.AnnPsychology.AnnPsychology.config.CustomUserDetails;
import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.models.enums.UserRole;
import com.AnnPsychology.AnnPsychology.repository.DateRepository;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final DateRepository dateRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getByUsername(username);
        return user.map(CustomUserDetails::new).
                orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void createUser(User userDetails) {
        User user = new User();
        user.setId(userDetails.getId());
        user.setUsername(userDetails.getUsername());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDetails.getPassword()));
        user.setUserRole(UserRole.ROLE_USER);
        user.setDateBirth(userDetails.getDateBirth());
        user.setSessionList(userDetails.getSessionList());
        user.setSocialLink(userDetails.getSocialLink());
        user.setPhone(userDetails.getPhone());
        user.setPrice(new BigDecimal(2000));
        userRepository.save(user);
    }

    public void updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userDetails.getUsername());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDetails.getPassword()));
        user.setDateBirth(userDetails.getDateBirth());
        user.setSocialLink(userDetails.getSocialLink());
        user.setPhone(userDetails.getPhone());
        userRepository.save(user);
    }

    public void signUpSession(Long id, LocalDate date, LocalTime time) {

        // Формируем список занятых дат
        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        dateRepository.findAll().forEach(item -> {
            localDateTimeList.add(item.getSessionDate());
        });

        // 1. Дата сесси не должна быть занята
        boolean validDateContain = localDateTimeList.contains(LocalDateTime.of(date, time));

        // 2. Сессия не должна пересекаться с другими
        // 3. Между сессиями должен быть перерыв 30 мин
        boolean validSessionBefore = localDateTimeList.contains(LocalDateTime.of(date, time.plusHours(1))) |
                localDateTimeList.contains(LocalDateTime.of(date, time.plusMinutes(30)));

        boolean validSessionAfter = localDateTimeList.contains(LocalDateTime.of(date, time.minusHours(1))) |
                localDateTimeList.contains(LocalDateTime.of(date, time.minusMinutes(30)));

        if (!validDateContain & !validSessionBefore & !validSessionAfter) {
            User updUser = userRepository.findById(id).orElseThrow();

            Session session = new Session();
            session.setUser(updUser);
            session.setSessionStatus(SessionStatus.SESSION_ACTIVE);
            session.setSessionPrice(updUser.getPrice());

            SessionDate sessionDate = new SessionDate();
            sessionDate.setSessionDate(LocalDateTime.of(date, time));
            session.setSessionDate(sessionDate);

            updUser.getSessionList().add(session);
            userRepository.save(updUser);
        }

    }

}
