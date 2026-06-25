package org.onlydevs.hibento.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "speaker")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Speaker {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 255)
  private String name;

  @Column(name = "avatar_url", nullable = true)
  private String avatarUrl;

  @Column(nullable = true)
  private String bio;

  @Column(name = "created_at")
  @CreationTimestamp
  private Instant createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Instant updatedAt;

  @OneToMany(mappedBy = "speaker", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  List<SpeakerExternalLink> speakerExternalLinks = new ArrayList<>();

  @OneToMany(mappedBy = "speaker", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  List<EventSessionSpeaker> eventSessionSpeakers = new ArrayList<>();
}
