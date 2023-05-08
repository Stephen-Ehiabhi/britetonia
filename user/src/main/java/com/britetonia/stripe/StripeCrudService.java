//package com.britetonia.stripe;
//
//import com.britetonia.Exceptions.UserNotFoundException;
//import com.britetonia.model.User;
//import com.britetonia.repository.UserRepository;
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.Customer;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@AllArgsConstructor
//public class StripeCrudService {
//
//    private final UserRepository userRepository;
//
////    @Value( "${stripe.api.key}" )
//    private String stripeKey = "";
//
//    public String addCustomer(User user) throws StripeException {
//
//        Stripe.apiKey = stripeKey;
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("email", user.getEmail());
//        params.put("name", user.getName());
//        params.put("phone", user.getPhone());
//
//        Customer customer = Customer.create(params);
//
//        return customer.getId();
//    }
//
//    public void update(long id, User user) throws StripeException {
//        String customerID = getCustomerId(id);
//        Customer customer = Customer.retrieve(customerID);
//
//        Map<String, Object> metadata = new HashMap<>();
//        metadata.put("email", user.getEmail());
//        metadata.put("phone", user.getPhone());
//        metadata.put("name", user.getName());
//
//        customer.update(metadata);
//    }
//
//    public void delete(long id) throws StripeException {
//        String customerID = getCustomerId(id);
//        Stripe.apiKey = stripeKey;
//
//        Customer customer = Customer.retrieve(customerID);
//        customer.delete();
//    }
//
//    private String getCustomerId(long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
//
//        return user.getCustomerId();
//    }
//}