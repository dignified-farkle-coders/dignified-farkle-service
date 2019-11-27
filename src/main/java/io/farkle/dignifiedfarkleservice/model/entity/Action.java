package io.farkle.dignifiedfarkleservice.model.entity;

import io.farkle.dignifiedfarkleservice.model.convert.DiceArrayConverter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
public class Action {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "action_id", updatable = false, nullable = false)
  private long actionId;

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
  private Players player;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "next_player_id")
  private Players nextPlayer;

  @Column(name = "available")
  @Convert(converter = DiceArrayConverter.class)
  private int[] availableDice;

  @Column(name = "frozen")
  @Convert(converter = DiceArrayConverter.class)
  private int[] frozenDice;

  private boolean stay;

  public long getActionId() {
    return actionId;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public Players getPlayer() {
    return player;
  }

  public void setPlayer(Players player) {
    this.player = player;
  }

  public Players getNextPlayer() {
    return nextPlayer;
  }

  public int[] getAvailableDice() {
    return availableDice;
  }

  public void setAvailableDice(int[] availableDice) {
    this.availableDice = availableDice;
  }

  public int[] getFrozenDice() {
    return frozenDice;
  }

  public void setFrozenDice(int[] frozenDice) {
    this.frozenDice = frozenDice;
  }

  public boolean isStay() {
    return stay;
  }

  public void setStay(boolean stay) {
    this.stay = stay;
  }
}
