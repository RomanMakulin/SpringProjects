package com.AnnPsychology.AnnPsychology.services.user;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findAll().stream().filter(item -> item.getEmail().equals(email)).findAny();
        return user.map(CustomUserDetails::new).
                orElseThrow(() -> new UsernameNotFoundException(email + " not found!"));
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public boolean createUser(User userDetails) {
        if (userRepository.getByEmail(userDetails.getEmail()) != null) return false;
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDetails.getPassword()));
        user.setDateBirth(userDetails.getDateBirth());
        user.setSocialLink(userDetails.getSocialLink());
        user.setPhone(userDetails.getPhone());
        userRepository.save(user);
        return true;
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
}
