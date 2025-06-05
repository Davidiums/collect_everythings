package com.davidius.shared.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventEntityRepository extends JpaRepository<EventEntity, Long> {

    // Méthode pour trouver les événements par type
    List<EventEntity> findByEventType(String eventType);

    // Méthode pour trouver les événements créés après une certaine date
    List<EventEntity> findByCreatedAtAfter(LocalDateTime dateTime);

    // Méthode pour compter les événements par type
    long countByEventType(String eventType);
}
