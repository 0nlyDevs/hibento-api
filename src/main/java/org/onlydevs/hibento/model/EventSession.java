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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EventSession {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column @NotBlank private String title;

  @Column(nullable = true)
  private String description;

  @Column(name = "start_time")
  @NotNull
  private Instant startTime;

  @Column(name = "end_time")
  @NotNull
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
  @ToString.Exclude
  private Event event;

  @ManyToOne
  @JoinColumn(name = "room_id")
  @ToString.Exclude
  private Room room;

  @OneToMany(mappedBy = "eventSession", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<EventSessionSpeaker> eventSessionSpeakers = new ArrayList<>();

  @OneToMany(mappedBy = "eventSession", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Question> questions = new ArrayList<>();

}
