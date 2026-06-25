package org.onlydevs.hibento.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.onlydevs.hibento.model.enums.LinkType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "speaker_external_link")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpeakerExternalLink {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "link_type")
  @Enumerated(EnumType.STRING)
  private LinkType linkType;

  @Column
  private String url;

  @Column(name = "created_at")
  @CreationTimestamp
  private Instant createdAt;

  @ManyToOne
  @JoinColumn(name = "speaker_id", nullable = false)
  private Speaker speaker;
}
