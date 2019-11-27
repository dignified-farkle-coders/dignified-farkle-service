package io.farkle.dignifiedfarkleservice.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.farkle.dignifiedfarkleservice.view.FlatPlayer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
        @Index(columnList = "diceUpgrade"),
        @Index(columnList = "winRate"),
        @Index(columnList = "victoryPoints"),
        @Index(columnList = "highestScore")
    }
)
public class Player implements FlatPlayer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "player_id", updatable = false, nullable = false)
  private long id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @Column(nullable = false, updatable = false, unique = true)
  @JsonIgnore
  private String oauthKey;

  @NonNull
  @Column(nullable = false)
  private String displayName;

  @NonNull
  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("created ASC")
  private List<GamePlayer> gamePlayers = new LinkedList<>();

  @NonNull
  @OneToMany(mappedBy = "winner",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @OrderBy("created DESC")
  private List<Game> gamesWon = new LinkedList<>();

  // TODO Make these fields and add Column
  // TODO Make Column for User

  private String diceUpgrade;

  private double winRate;

  private int victoryPoints;

  private int highestScore;

  @Override
  @Column(nullable = false)
  public long getId() {
    return id;
  }

  @Override
  @NonNull
  public Date getCreated() {
    return created;
  }

  @NonNull
  public List<GamePlayer> getGamePlayers() {
    return gamePlayers;
  }

  @NonNull
  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(@NonNull String oauthKey) {
    this.oauthKey = oauthKey;
  }

  @Override
  @NonNull
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(@NonNull String displayName) {
    this.displayName = displayName;
  }

  @NonNull
  public List<Game> getGamesWon() {
    return gamesWon;
  }

  @Override
  @NonNull
  public int getWinCount() {
    return gamesWon.size();
  }

  @Override
  public String getDiceUpgrade() {
    return diceUpgrade;
  }

  public void setDiceUpgrade(String diceUpgrade) {
    this.diceUpgrade = diceUpgrade;
  }

  @Override
  @NonNull
  public double getWinRate() {
    return winRate;
  }

  public void setWinRate(double winRate) {
    this.winRate = winRate;
  }

  @Override
  @NonNull
  public int getVictoryPoints() {
    return victoryPoints;
  }

  public void setVictoryPoints(int victoryPoints) {
    this.victoryPoints = victoryPoints;
  }

  @Override
  @NonNull
  public int getHighestScore() {
    return highestScore;
  }

  public void setHighestScore(int highestScore) {
    this.highestScore = highestScore;
  }
}