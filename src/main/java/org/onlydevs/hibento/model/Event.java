package org.onlydevs.hibento.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 255)
  @NotBlank
  private String title;

  @Column
  private String description;

  @Column(name = "is_online")
  private boolean isOnline;

  @Column(name = "start_date")
  @NotNull
  private Instant startDate;

  @Column(name = "end_date")
  @NotNull
  @FutureOrPresent
  private Instant endDate;

  @Column(name = "created_at")
  @CreationTimestamp
  @NotNull
  private Instant createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  @NotNull
  private Instant updatedAt;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<EventSession> eventSessions;

  @ManyToOne
  @JoinColumn(name = "venue_id")
  @ToString.Exclude
  private Venue venue;
}
