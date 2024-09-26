package com.group.libraryapp.controller.user;


import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//API, Http관련 역할

//레이어 아키텍쳐
//콘트롤러, 서비스, 레포지터리 3단 분리

@RestController
public class UserController {

    //필드에 바로 사용 ( Test 코드를 사용시 테스트를 어렵게 만든다. )
    //@Autowired
    //private UserService userService;

    //setter 사용 ( 누군가 setter 사용시 오작동 가능성 있음 )
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    //생성자 사용( 권장 됨 )
    private final UserServiceV2 userService;

    @Autowired
    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUser() {
        return userService.getUser();
    }


    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        //조회용
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam("name") String name) {
        userService.deleteUser(name);
    }


    @GetMapping("/user/error-test")
    public void errorTest1() {
        throw new IllegalArgumentException("Invalid data for errorTest1");
    }
    @GetMapping("/user/error-test2")
    public void errorTest2() {
        throw new IllegalArgumentException();
    }

//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
//        // 호출된 메서드와 예외 메시지를 포함한 상세 오류 응답 생성
//        String method = request.getMethod() + " " + request.getRequestURI();  // 메서드와 URI 정보
//        String details = ex.getMessage();  // 예외의 메시지
//        ErrorResponse errorResponse = new ErrorResponse(
//                "An error occurred", method, details, 400);
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
}
