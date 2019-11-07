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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Table(
    indexes = {
        @Index(columnList = "dice_upgrade"),
        @Index(columnList = "win_rate"),
        @Index(columnList = "victory_points"),
        @Index(columnList = "highest_score")
    }
)
public class Player {

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
  private String oauthKey;

  @NonNull
  @Column(nullable = false)
  private String displayName;

  @NonNull
  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("join_time ASC")
  private List<GamePlayer> gamePlayers = new LinkedList<>();

  @NonNull
  @OneToMany(mappedBy = "winner",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @OrderBy("created DESC")
  private List<Game> gamesWon = new LinkedList<>();

  // TODO Make these fields and add Column
  // TODO Make Column for User

  @NonNull
  @Column(nullable = false)
  public String dice_upgrade;

  @NonNull
  @Column(nullable = false)
  public double win_rate;

  @NonNull
  @Column(nullable = false)
  public int victory_points;

  @NonNull
  @Column(nullable = false)
  public int highest_score;

  @NonNull
  @Column(nullable = false)
  public long getId() {
    return id;
  }

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

  public int getWinCount() {
    return gamesWon.size();
  }

  public String getDice_upgrade() {
    return dice_upgrade;
  }

  public void setDice_upgrade(String dice_upgrade) {
    this.dice_upgrade = dice_upgrade;
  }

  public double getWin_rate() {
    return win_rate;
  }

  public void setWin_rate(double win_rate) {
    this.win_rate = win_rate;
  }

  public int getVictory_points() {
    return victory_points;
  }

  public void setVictory_points(int victory_points) {
    this.victory_points = victory_points;
  }

  public int getHighest_score() {
    return highest_score;
  }

  public void setHighest_score(int highest_score) {
    this.highest_score = highest_score;
  }
}