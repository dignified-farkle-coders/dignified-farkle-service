package io.farkle.dignifiedfarkleservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.farkle.dignifiedfarkleservice.view.FlatAction;
import io.farkle.dignifiedfarkleservice.view.FlatGame;
import io.farkle.dignifiedfarkleservice.view.FlatGamePlayer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Table(
    indexes = {
        @Index(columnList = "numberOfRounds")
    }
)
public class Game implements FlatGame {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "game_id", updatable = false, nullable = false)
  private long id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  private int preferredNumPlayers;

  @NonNull
  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("created ASC")
  @JsonSerialize(contentAs = FlatGamePlayer.class)
  private List<GamePlayer> gamePlayers = new LinkedList<>();

  @NonNull
  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("created ASC")
  @JsonIgnore
  private List<Action> actions = new LinkedList<>();

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "winner_id")
  private Player winner;

  @NonNull
  @Column(nullable = false)
  private int numberOfRounds;

  private State state = State.PENDING;

  @Override
  public long getId() {
    return id;
  }

  @Override
  @NonNull
  public Date getCreated() {
    return created;
  }

  @Override
  public int getPreferredNumPlayers() {
    return preferredNumPlayers;
  }

  public void setPreferredNumPlayers(int preferredNumPlayers) {
    this.preferredNumPlayers = preferredNumPlayers;
  }

  public List<GamePlayer> getGamePlayers() {
    return gamePlayers;
  }

  @NonNull
  public List<Action> getActions() {
    return actions;
  }

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }

  @Override
  public int getNumberOfRounds() {
    return numberOfRounds;
  }

  public void setNumberOfRounds(int numberOfRounds) {
    this.numberOfRounds = numberOfRounds;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public FlatAction getLastAction(){
    return (actions.size() > 0) ? actions.get(actions.size() - 1) : null;
  }

  public enum State {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    ABANDONED;
  }


}
