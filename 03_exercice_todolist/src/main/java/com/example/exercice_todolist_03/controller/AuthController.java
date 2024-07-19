package com.example.exercice_todolist_03.controller;

import com.example.exercice_todolist_03.dto.BaseResponsedto;
import com.example.exercice_todolist_03.dto.user.UserDtoPost;
import com.example.exercice_todolist_03.dto.user.UserLoginDto;
import com.example.exercice_todolist_03.entity.User;
import com.example.exercice_todolist_03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/user")
    public ResponseEntity<BaseResponsedto> registerUser(@RequestBody UserDtoPost userDtoPost) {
        if (userService.createUser(userDtoPost)){
            return ResponseEntity.ok(new BaseResponsedto("User registered successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponsedto("User could not be registered"));
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<BaseResponsedto> registerAdmin(@RequestBody UserDtoPost userDtoPost) {
        if (userService.createAdmin(userDtoPost)){
            return ResponseEntity.ok(new BaseResponsedto("User registered successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponsedto("User could not be registered"));
        }
    }

    @PostMapping("/login")
    public BaseResponsedto login(@RequestBody UserLoginDto userLoginDto) {
        if (userService.checkUserNameExists(userLoginDto.getEmail())){

            if (userService.verifyUser(userLoginDto.getEmail(), userLoginDto.getPassword())){
                Map<String, Object> data = new HashMap<>();
                int id = userService.getIdByEmail(userLoginDto.getEmail());
                data.put("token", userService.generateToken(userLoginDto.getEmail(), userLoginDto.getPassword(), id));
                return new BaseResponsedto("Success",data);
            }
        }
        return new BaseResponsedto("Connection Failed");
    }

}
