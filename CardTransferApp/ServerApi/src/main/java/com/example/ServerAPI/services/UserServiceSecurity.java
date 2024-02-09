//package com.example.ServerAPI.services;
//
//import com.example.ServerAPI.Config.CustomUserDetails;
//import com.example.ServerAPI.models.Card;
//import com.example.ServerAPI.models.Role;
//import com.example.ServerAPI.models.User;
//import com.example.ServerAPI.repository.UserRepository;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@Data
//@RequiredArgsConstructor
//public class UserServiceSecurity implements UserDetailsService {
//    private final UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.getByUsername(username);
//        return user.map(CustomUserDetails::new).
//        orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
//    }
//
//    public void createAdmin(String name, int pin, String password){
//        User user = new User();
//        user.setUsername(name);
//        user.setCard(new Card());
//        user.getCard().setPin(pin);
//        user.setRole(Role.ROLE_ADMIN);
//        user.setPassword(password);
//        userRepository.save(user);
//    }
//
//}
