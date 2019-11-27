package io.farkle.dignifiedfarkleservice.model.entity;

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
        @Index(columnList = "number_of_rounds")
    }
)
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "game_id", updatable = false, nullable = false)
  private long id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("created ASC")
  private List<GamePlayer> gamePlayers = new LinkedList<>();

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "winner_id")
  private Players winner;

  @NonNull
  @Column(nullable = false)
  private int number_of_rounds;

  public long getId() {
    return id;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  public List<GamePlayer> getGamePlayers() {
    return gamePlayers;
  }

  public Players getWinner() {
    return winner;
  }

  public void setWinner(Players winner) {
    this.winner = winner;
  }

  public int getNumberOfRounds() {
    return number_of_rounds;
  }

  public void setNumberOfRounds(int numberOfRounds) {
    this.number_of_rounds = numberOfRounds;
  }
}
