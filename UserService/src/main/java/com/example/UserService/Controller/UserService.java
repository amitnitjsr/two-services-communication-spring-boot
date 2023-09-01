package com.example.UserService.Controller;

import com.example.UserService.DTO.ResponseDto;
import com.example.UserService.Entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserService {


    @Autowired
    @Lazy
    private UserService userService;
//    https://www.javaguides.net/2022/10/spring-boot-microservices-communication-using-resttemplate.html
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        ResponseEntity<User> savedUser = userService.saveUser(user);
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        return savedUser;
    }


    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId){
        ResponseEntity<ResponseDto> responseDto = userService.getUser(userId);
//        return ResponseEntity.ok(responseDto);
        return responseDto;
    }
}
