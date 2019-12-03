package io.farkle.dignifiedfarkleservice.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.farkle.dignifiedfarkleservice.model.convert.DiceArrayConverter;
import io.farkle.dignifiedfarkleservice.view.FlatAction;
import io.farkle.dignifiedfarkleservice.view.FlatGame;
import io.farkle.dignifiedfarkleservice.view.FlatPlayer;
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
public class Action implements FlatAction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "action_id", updatable = false, nullable = false)
  private long actionId;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @JsonSerialize(as = FlatGame.class)
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  private Game game;

  @JsonSerialize(as = FlatPlayer.class)
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "player_id")
  private Player player;

  @JsonSerialize(as = FlatPlayer.class)
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "next_player_id")
  private Player nextPlayer;


  @Column(name = "available")
  @Convert(converter = DiceArrayConverter.class)
  private int[] availableDice;

  @Column(name = "frozen")
  @Convert(converter = DiceArrayConverter.class)
  private int[] frozenDice;

  private boolean stay;

  private int turn;

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

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Player getNextPlayer() {
    return nextPlayer;
  }

  public void setNextPlayer(Player nextPlayer) {
    this.nextPlayer = nextPlayer;
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

  public boolean getStay() {
    return stay;
  }

  public void setStay(boolean stay) {
    this.stay = stay;
  }

  public int getTurn() {
    return turn;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }
}
