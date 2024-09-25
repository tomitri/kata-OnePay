package fr.poc.kata.onepay.domain;

import fr.poc.kata.onepay.domain.enums.PaymentStatus;
import fr.poc.kata.onepay.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalAmount;

    //@Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    //@Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToMany(cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "Transaction")
    private List<Item> items;

    public Transaction() {

    }

}

/*
enum PaymentType {
    CREDIT_CARD, GIFT_CARD, PAYPAL
}

enum PaymentStatus {
    NEW, AUTHORIZED, CAPTURED, CANCELED
}
*/