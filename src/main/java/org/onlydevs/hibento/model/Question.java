package org.onlydevs.hibento.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table
@AllArgsConstructor
@Data
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column
  private String content;

  @Column(nullable = true, name = "author_name")
  private String authorName;

  @Column
  private Integer upvotes;

  @Column(name = "created_at")
  @CreationTimestamp
  private Instant createdAt;

}
