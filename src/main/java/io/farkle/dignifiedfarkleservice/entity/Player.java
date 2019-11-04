package io.farkle.dignifiedfarkleservice.entity;


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
  private Date dateCreated = new Date();

  @NonNull
  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("join_time ASC")
  private List<GamePlayer> order = new LinkedList<>();

  // TODO Make these fields and add Column
  // TODO Make Column for User
  public String dice_upgrade;

  public double win_rate;

  public int victory_points;

  public int highest_score;

  public long getId() {
    return id;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

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