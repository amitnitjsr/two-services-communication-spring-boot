package com.example.UserService.Services;

import com.example.UserService.DTO.DepartmentDto;
import com.example.UserService.DTO.ResponseDto;
import com.example.UserService.DTO.UserDto;
import com.example.UserService.Entity.User;
import com.example.UserService.Repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService{

    @Lazy
    private UserRepository userRepository;
    @Lazy
    private RestTemplate restTemplate;

    @Override
    public User saveUser( User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

        ResponseEntity<DepartmentDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/departments/" + user.getDepartmentId(),
                        DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();

        System.out.println(responseEntity.getStatusCode());

        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(@Lazy User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
