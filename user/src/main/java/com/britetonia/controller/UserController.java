package com.britetonia.controller;

import com.britetonia.dto.UserRequest;
import com.britetonia.dto.UserResponse;
import com.britetonia.model.User0model;
import com.britetonia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User0model> registerUserController(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>( userService.registerUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsersController(){
        return new ResponseEntity<>( userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserController(@PathVariable("id") long id){
        return new ResponseEntity<>( userService.getUser(id), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUserController(@PathVariable("id") long id, UserRequest userRequest){
        return new ResponseEntity<>( userService.updateUser(id, userRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserController(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<>( "User deleted", HttpStatus.NO_CONTENT);
    }

}
