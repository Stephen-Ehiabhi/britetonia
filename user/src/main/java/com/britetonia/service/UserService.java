package com.britetonia.service;

import com.britetonia.Exceptions.UserAlreadyExistsException;
import com.britetonia.Exceptions.UserNotFoundException;
import com.britetonia.dto.UserRequest;
import com.britetonia.dto.UserResponse;
import com.britetonia.model.Address;
import com.britetonia.model.Role;
import com.britetonia.model.User;
import com.britetonia.repository.AddressRepository;
import com.britetonia.repository.UserRepository;
import com.britetonia.stripe.StripeCrudService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final StripeCrudService stripeCRUD;

    public User registerUser(UserRequest userRequest) throws StripeException {
        Address address = userRequest.getAddress();
        addressRepository.save(address);

        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .address(address)
                .role(Role.valueOf(userRequest.getRole()))
                .build();

        String customerID = stripeCRUD.addCustomer(user);

        user.setCustomerId(customerID);

        if (userRepository.existsByName(user.getName())) {
            throw new UserAlreadyExistsException("User with name " + user.getName() + " already exists");
        }

        return userRepository.save(user);
    }


    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapDataToUserResponse).toList();
    }

    public UserResponse getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return mapDataToUserResponse(user.get());
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }

    public UserResponse updateUser(long id, UserRequest userRequest) throws StripeException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        Address address = userRequest.getAddress();
        address.setId(user.getId());
        addressRepository.save(address);

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setAddress(address);
        user.setRole(Role.valueOf(userRequest.getRole()));

        // update in stripe
        stripeCRUD.update(id, user);

        userRepository.save(user);

        return mapDataToUserResponse(user);
    }

    //todo: an admin user perform CRUD on product
    //todo: so a connection is needed to the product microservice

    public void deleteUser(long id) throws StripeException {
        // delete in stripe
        stripeCRUD.delete(id);
        userRepository.deleteById(id);
    }


    //  reusable methods
    public UserResponse mapDataToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(String.valueOf(user.getRole()))
                .build();
    }

}
