package io.farkle.dignifiedfarkleservice.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.farkle.dignifiedfarkleservice.view.FlatGame;
import io.farkle.dignifiedfarkleservice.view.FlatGamePlayer;
import io.farkle.dignifiedfarkleservice.view.FlatPlayer;
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
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"game_id", "player_id"}),
        @UniqueConstraint(columnNames = {"game_id", "order_of_play"})
    },
    indexes = @Index(columnList = "created")
)
public class GamePlayer implements FlatGamePlayer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "game_player_id", updatable = false, nullable = false)
  private Long gamePlayerId;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @JsonSerialize(as = FlatGame.class)
  private Game game;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "player_id", nullable = false, updatable = false)
  @JsonSerialize(as = FlatPlayer.class)
  private Player player;

  @Column(name = "order_of_play", nullable = false, updatable = false)
  private int order;

  private int points;

  @Override
  public Long getId() {
    return gamePlayerId;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  @Override
  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  @Override
  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }
}
