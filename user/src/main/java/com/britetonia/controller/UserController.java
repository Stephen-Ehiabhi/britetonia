package com.britetonia.controller;

import com.britetonia.dto.UserRequest;
import com.britetonia.dto.UserResponse;
import com.britetonia.model.User;
import com.britetonia.service.UserService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> registerUserController(@RequestBody UserRequest userRequest) throws StripeException {
        return new ResponseEntity<>(userService.registerUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserResponse> loginUserController(@RequestBody UserRequest UserRequest) throws StripeException {
        return new ResponseEntity<>(userService.loginUser(UserRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsersController() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserController(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUserController(@PathVariable("id") long id, @RequestBody UserRequest userRequest) throws StripeException {
        return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserController(@PathVariable("id") long id) throws StripeException {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted", HttpStatus.NO_CONTENT);
    }

}
