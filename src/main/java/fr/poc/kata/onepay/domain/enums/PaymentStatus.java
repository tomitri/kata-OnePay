package fr.poc.kata.onepay.domain.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    NEW("NEW"),
    AUTHORIZED("AUTHORIZED"),
    CAPTURED("CAPTURED"),
    CANCELED("CANCELED");

    private final String libellePaymentStatus;

    PaymentStatus(String libelle) {
        this.libellePaymentStatus = libelle;
    }

}
