package org.onlydevs.hibento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "venue")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Venue {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 255)
  @NotBlank
  private String name;

  @Column(length = 255)
  @NotBlank
  private String city;

  @Column(length = 255)
  @NotBlank
  private String neighborhood;

  @Column(name = "total_rooms")
  @Min(value = 1)
  private int totalRooms;

  @Column(name = "created_at")
  @CreationTimestamp
  private Instant createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Instant updatedAt;

  @OneToMany(mappedBy = "venue")
  @ToString.Exclude
  List<Room> rooms = new ArrayList<>();

  @OneToMany(mappedBy = "venue")
  @ToString.Exclude
  List<Event> events = new ArrayList<>();
}
