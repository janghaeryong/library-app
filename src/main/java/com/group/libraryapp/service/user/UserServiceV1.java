package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


//분기처리 , 로직 처리를 담당
@Service
public class UserServiceV1 {
    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserCreateRequest request) {
        String name = request.getName();
        int age = request.getAge();
        userJdbcRepository.saveUser(name, age);
    }

    public List<UserResponse> getUser(){
        return userJdbcRepository.getUserList();
    }


    public void updateUser(UserUpdateRequest request) {
        long id = request.getId();
        String name = request.getName();

        if(userJdbcRepository.isUserNotExist(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        userJdbcRepository.updateUserName(name, id);
    }

    public void deleteUser(String name){
        System.out.println("삭제 : " + name);
        if(userJdbcRepository.isUserNotExist(name)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userJdbcRepository.deleteUserName(name);
    }

}
