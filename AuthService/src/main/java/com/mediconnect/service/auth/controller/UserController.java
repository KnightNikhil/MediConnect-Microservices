package com.mediconnect.service.auth.controller;

import com.mediconnect.service.common_entities.service.UserService;
import com.mediconnect.service.common_entities.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/register/{role}")
    public ResponseEntity<Object> createNewUser(@PathVariable String role, @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createNewUser(role, userDto), HttpStatus.CREATED) ;
    }

    @PostMapping("/login/{role}")
    public ResponseEntity<Object> loginUser(@PathVariable String role, HttpServletRequest request){
        return new ResponseEntity<>(userService.loginUser(role , request), HttpStatus.OK) ;
    }



}
