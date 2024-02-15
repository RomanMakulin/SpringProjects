//package org.example.service;
//
//import org.example.models.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomUserService implements UserDetailsService {
//
//    // TO DO: с помощью OpenFeign импортировать из БД всех Users
//    // ... и продолжить настройку security
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.getByUsername(username);
//        return user.map(CustomUserDetails::new).
//                orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
//    }
//}
