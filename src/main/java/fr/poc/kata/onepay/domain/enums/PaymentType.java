package fr.poc.kata.onepay.domain.enums;

import lombok.Getter;

@Getter
public enum PaymentType {

    CREDIT_CARD("CREDIT_CARD"),
    GIFT_CARD("GIFT_CARD"),
    PAYPAL("PAYPAL");

    private final String libellePaymentType;

    PaymentType(String libelle) {
        this.libellePaymentType = libelle;
    }

}