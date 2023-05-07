package com.britetonia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String name;
    private String cardNumber;
    private int expMonth;
    private int expYear;
    private String cvc;
    private String currency;
}
