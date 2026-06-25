package org.onlydevs.hibento.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
    name = "event_session",
    uniqueConstraints = @UniqueConstraint(columnNames = {"room_id", "start_time"}),
    indexes = {
      @Index(columnList = "event_id"),
      @Index(columnList = "start_time"),
      @Index(columnList = "room_id")
    })
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventSession {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column private String title;

  @Column(nullable = true)
  private String description;

  @Column(name = "start_time")
  private Instant startTime;

  @Column(name = "end_time")
  private Instant endTime;

  @Column(nullable = true)
  private Integer capacity;

  @Column(name = "created_at")
  @CreationTimestamp
  private Instant createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Instant updatedAt;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event event;

  @ManyToOne
  @JoinColumn(name = "room_id")
  private Room room;

  @OneToMany(mappedBy = "eventSession", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EventSessionSpeaker> eventSessionSpeakers = new ArrayList<>();

  @OneToMany(mappedBy = "eventSession", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Question> questions = new ArrayList<>();

  @OneToMany(mappedBy = "eventSession", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SessionRegistration> registrations;
}
