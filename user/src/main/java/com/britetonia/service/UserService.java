package com.britetonia.service;

import com.britetonia.Exceptions.UserAlreadyExistsException;
import com.britetonia.Exceptions.UserNotFoundException;
import com.britetonia.dto.UserRequest;
import com.britetonia.dto.UserResponse;
import com.britetonia.model.Address;
import com.britetonia.model.User0model;
import com.britetonia.repository.AddressRepository;
import com.britetonia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public User0model registerUser(UserRequest userRequest) {
        Address address = userRequest.getAddress();
        addressRepository.save(address);


        User0model user = User0model.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .address(address)
                .role(userRequest.getRole())
                .build();

        if(userRepository.existsByName(user.getName())){
            throw new UserAlreadyExistsException("User with name " + user.getName() + " already exists");
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
        User0model user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        Address address = userRequest.getAddress();
        address.setId(user.getId());
        addressRepository.save(address);

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setAddress(address);
        user.setRole(userRequest.getRole());

        userRepository.save(user);

        return mapDataToUserResponse(user);
    }

        //todo: an admin user perfrom CRUD on product
        //todo: so a connection is needed to the product microservice

    public UserResponse mapDataToUserResponse(User0model user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .build();
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}


