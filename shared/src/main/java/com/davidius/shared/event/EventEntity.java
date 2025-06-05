package com.davidius.shared.event;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventType;   // "PaymentRequestedEvent" ou "PaymentResultEvent"
    @Lob
    private String payload;     // JSON complet de l’event
    private LocalDateTime createdAt;
}