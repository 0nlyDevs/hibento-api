package org.onlydevs.hibento.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "event")
@Data
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 255)
  private String title;

  @Column
  private String description;

  @Column(name = "is_online")
  private boolean isOnline;

  @Column(name = "start_date")
  private Instant startDate;

  @Column(name = "end_date")
  private Instant endDate;

  @Column(name = "created_at")
  @CreationTimestamp
  private Instant createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Instant updatedAt;
}
