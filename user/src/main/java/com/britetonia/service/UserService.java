package com.britetonia.service;

import com.britetonia.Exceptions.UserAlreadyExistsException;
import com.britetonia.Exceptions.UserNotFoundException;
import com.britetonia.dto.UserRequest;
import com.britetonia.dto.UserResponse;
import com.britetonia.model.Role;
import com.britetonia.model.User0model;
import com.britetonia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User0model registerUser(UserRequest userRequest) {
        User0model user = User0model.builder()
                .id(userRequest.getId())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
//                .address(userRequest.getAddress())
//                .role(Role.CUSTOMER)
                .build();

        if(userRepository.existsById(user.getId())){
            throw new UserAlreadyExistsException("User with ID " + user.getId() + " already exists");
        }

        return userRepository.save(user);
    }

    public List<UserResponse> getUsers() {
        List<User0model> users = userRepository.findAll();
        return users.stream().map(this::mapDataToUserResponse).toList();
    }

    public UserResponse getUser(long id) {
        Optional<User0model> user = userRepository.findById(id);
        if (user.isPresent()) {
            return mapDataToUserResponse(user.get());
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }

    public UserResponse updateUser(long id, UserRequest userRequest) {
        Optional<User0model> optionalUser = userRepository.findById(id);

//        if (optionalUser.isPresent()) {
            User0model user = User0model.builder()
                    .id(userRequest.getId())
                    .name(userRequest.getName())
                    .email(userRequest.getEmail())
                    .phone(userRequest.getPhone())
//                .address(userRequest.getAddress())
//                .role(Role.CUSTOMER)
                    .build();
//            user.setAddress(userRequest.getAddress());
//            user.setRole(userRequest.getRole());

            return mapDataToUserResponse(userRepository.save(user));
    }


    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

   //todo: an admin user perfrom CRUD on product
  //todo: so a connection is needed to the product microservice

    private UserResponse mapDataToUserResponse(User0model user) {
       return UserResponse.builder()
               .id(user.getId())
               .name(user.getName())
               .email(user.getEmail())
               .phone(user.getPhone())
//               .address(user.getAddress())
//               .role(user.getRole())
               .build();
    }

}
