package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//트랜잭션 관리
//IOException Checked Exception 은 롤벡이 발생하지 않는다.
//영속성 컨텍스트
//1. 변경감지
//2. 쓰기지연
//3. 1차 캐싱
@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));

        //userRepository.save(new User("A", 1));
        //userRepository.save(new User("B", 2));
        //userRepository.save(new User("C", 3));

    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUser() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        //select * from user = id = ?;
        //영속성 컨텍스트
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        //
        user.updateName(request.getName());
        //트랜젝션이 끝날때 자동으로 업데이트 해줌
        //userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name) {
//        List<User> users = userRepository.findByName(name);
//        if(users.isEmpty()){
//            throw new IllegalArgumentException("User not found");
//        }
//        userRepository.findByName(name).orElseThrow(
//            IllegalArgumentException::new
//        );

        if(!userRepository.existsByName(name)){
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteByName(name);
    }
}
