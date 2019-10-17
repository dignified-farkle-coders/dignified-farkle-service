package io.farkle.dignifiedfarkleservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.lang.NonNull;

@Entity
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "game_id", updatable = false, nullable = false)
  private long id;

  @NonNull
  @Column(nullable = false)
  private String playerId;

  private String winnerName;

  private int turnsPlayed;


}
