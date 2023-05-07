package com.britetonia;

import com.britetonia.Exceptions.InternalServerErrorException;
import com.britetonia.dto.OrderRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class StripeCRUD {
    public void chargeCard(OrderRequest orderRequest) {
        try {
            Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

            Map<String, Object> retrieveParams =
                    new HashMap<>();

            List<String> expandList = new ArrayList<>();
            expandList.add("sources");

            retrieveParams.put("expand", expandList);

            Customer customer = Customer
                    .retrieve(orderRequest.getCustomerId(), retrieveParams, null);

            // add card details
            Map<String, Object> cardDetails = new HashMap<>();

            cardDetails.put("object", "card");
            cardDetails.put("name", orderRequest.getCard().getName());
            cardDetails.put("number", orderRequest.getCard().getCardNumber());
            cardDetails.put("exp_month", orderRequest.getCard().getExpMonth());
            cardDetails.put("exp_year", orderRequest.getCard().getExpYear());
            cardDetails.put("cvc", orderRequest.getCard().getCvc());
            cardDetails.put("currency", orderRequest.getCard().getCurrency());

            Map<String, Object> sourceParams = new HashMap<>();
            sourceParams.put("source", cardDetails);

            Card card = (Card) customer.getSources().create(sourceParams);

            //initiate a payment intent with the card id
            Map<String, Object> automaticPaymentMethods =
                    new HashMap<>();
            automaticPaymentMethods.put("enabled", false);

            Map<String, Object> params = new HashMap<>();
            params.put("amount", orderRequest.getPrice());
            params.put("currency", "PLN");
            params.put("confirm", true);
            params.put("payment_method", card.getId());
            params.put("receipt_email", orderRequest.getCustomerEmail());

            PaymentIntent.create(params);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error: " + e.getMessage());
        }
    }
}