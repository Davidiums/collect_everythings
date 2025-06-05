package com.davidius.shared.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestedEvent {
    private Long userId;
    private Long planId;
    private Long subscriptionId;
    private double amount; // montant du paiement
}