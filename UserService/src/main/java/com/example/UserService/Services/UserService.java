package com.example.UserService.Services;

import com.example.UserService.DTO.ResponseDto;
import com.example.UserService.Entity.User;

public interface UserService {

    User saveUser(User user);

    ResponseDto getUser(Long userId);
}
