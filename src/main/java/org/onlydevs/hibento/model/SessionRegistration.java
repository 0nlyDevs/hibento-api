package org.onlydevs.hibento.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "session_registration")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionRegistration {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 255)
  private String name;

  @Column(length = 255, unique = true)
  private String email;

  @Column(name = "created_at")
  @CreationTimestamp
  private Instant createdAt;

  @ManyToOne
  @JoinColumn(name = "event_session_id", nullable = false, unique = true)
  private EventSession eventSession;
}
