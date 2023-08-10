package com.example.SpringMVC_Project.controller.rest;

import com.example.SpringMVC_Project.model.dto.UserDTO;
import com.example.SpringMVC_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody UserDTO userDTO) {
        return ResponseEntity
                .ok()
                .body(userService.saveUser(userDTO));
    }
}
