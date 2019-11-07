package io.farkle.dignifiedfarkleservice.model.entity;


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
import javax.persistence.UniqueConstraint;
import org.springframework.lang.NonNull;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"date_created", "user_name"}),
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
  @Column(nullable = false)
  private Date date_created = new Date();

  @NonNull
  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("join_time ASC")
  private List<GamePlayer> order = new LinkedList<>();

  // TODO Make these fields and add Column
  // TODO Make Column for User

  @NonNull
  @Column(nullable = false)
  public User user_name;

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
  @Column(nullable = false)
  public Date getDateCreated() {
    return date_created;
  }

  @NonNull
  @Column(nullable = false)
  public void setDateCreated(Date dateCreated) {
    this.date_created = dateCreated;
  }

  @NonNull
  @Column(nullable = false)
  public List<GamePlayer> getOrder() {
    return order;
  }

  public void setOrder(
      List<GamePlayer> order) {
    this.order = order;
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