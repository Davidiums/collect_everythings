package com.davidius.shared.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResultEvent {
    private Long userId;
    private Long subscriptionId;
    private String status;
    private String message; // message d'erreur ou de succès
    private double amount; // montant du paiement

}
