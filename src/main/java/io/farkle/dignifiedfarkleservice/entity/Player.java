package io.farkle.dignifiedfarkleservice.entity;


import java.util.Date;
import java.util.LinkedList;
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
import javax.validation.constraints.NegativeOrZero.List;
import org.springframework.lang.NonNull;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "date_created"),@UniqueConstraint(columnNames = "user_name"),
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
  @OrderBy("join_time, duration ASC")
  private List<GamePlayer> order = new LinkedList<>();

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

  public void setId(long id) {
    this.id = id;
  }
}