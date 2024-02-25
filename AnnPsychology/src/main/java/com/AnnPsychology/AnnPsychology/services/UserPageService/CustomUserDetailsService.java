package com.AnnPsychology.AnnPsychology.services.UserPageService;

import com.AnnPsychology.AnnPsychology.config.CustomUserDetails;
import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.models.enums.UserRole;
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
import java.util.Date;
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

    public User getById(Long id){
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
