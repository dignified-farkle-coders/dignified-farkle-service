package io.farkle.dignifiedfarkleservice.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
public class GamePlayer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "game_player_id", updatable = false, nullable = false)
  private long gamePlayerId;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "game_id")
  private Game game;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "player_id")
  private Player player;

  @Column(name = "order_of_play")
  private int order;

  private int points;

  public Long getId() {
    return gamePlayerId;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }
}
