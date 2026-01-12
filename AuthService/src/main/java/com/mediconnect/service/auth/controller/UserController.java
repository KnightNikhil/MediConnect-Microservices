package com.mediconnect.service.auth.controller;

import com.mediconnect.service.auth.security.JWTService;
import com.mediconnect.service.auth.service.UserService;
import com.mediconnect.service.common_entities.dto.LoginResponseDto;
import com.mediconnect.service.common_entities.dto.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;

    @PostMapping("/register/{role}")
    public ResponseEntity<Object> createNewUser(@PathVariable String role, @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createNewUser(role, userDto), HttpStatus.CREATED) ;
    }

    @PostMapping("/login/{role}")
    public ResponseEntity<Object> loginUser(@PathVariable String role, HttpServletRequest request){
        return new ResponseEntity<>(userService.loginUser(role , request), HttpStatus.OK) ;
    }

    @PostMapping("/refreshToken")
    public LoginResponseDto refreshToken(@RequestParam String token){
        LoginResponseDto loginResponseDto = jwtService.getAccessTokenFromRefresh(token);
        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
//        cookie.setSecure(true); -> when in production

        return loginResponseDto;
    }

}
