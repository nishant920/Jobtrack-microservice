package com.jobtrack.auth_api.controller;

import com.jobtrack.auth_api.dto.AppUserDto;
import com.jobtrack.auth_api.dto.UserResponseDto;
import com.jobtrack.auth_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/User")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("save")
    public ResponseEntity<?> createUser(@RequestBody AppUserDto appUserDto){
        UserResponseDto appUser = userService.saveUser(appUserDto);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @PutMapping("verify")
    public ResponseEntity<?> verifyEmail(@RequestParam String token){
        try {
            String verify = userService.verifyToken(token);
            return new ResponseEntity<>(verify, HttpStatus.OK);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
