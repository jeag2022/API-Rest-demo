package com.example.SpringMVC_Project.service;

import com.example.SpringMVC_Project.model.dto.UserDTO;
import com.example.SpringMVC_Project.model.security.Authority;
import com.example.SpringMVC_Project.model.security.User;
import com.example.SpringMVC_Project.repository.AuthorityRepository;
import com.example.SpringMVC_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Map<String, String> saveUser(UserDTO userDTO) {
        userRepository.save(DTOToUser(userDTO));
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("message", "User has been registered");
        return responseMessage;
    }

    public User DTOToUser (UserDTO userDTO) {
        Authority userAuthority = authorityRepository.findByAuthority("USER").get();
        Set<Authority> authoritySet = new HashSet<>();
        authoritySet.add(userAuthority);
        User user = new User(null, userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), authoritySet, true, true, true, true);
        return user;
    }
}
