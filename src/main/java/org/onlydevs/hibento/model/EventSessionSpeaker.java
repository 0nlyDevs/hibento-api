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
@Table(name = "event_session_speaker")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventSessionSpeaker {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "created_at")
  @CreationTimestamp
  private Instant createdAt;

  @ManyToOne
  @JoinColumn(name = "speaker_id", nullable = false)
  private Speaker speaker;

  @ManyToOne
  @JoinColumn(name = "event_session_id", nullable = false)
  private EventSession eventSession;
}
