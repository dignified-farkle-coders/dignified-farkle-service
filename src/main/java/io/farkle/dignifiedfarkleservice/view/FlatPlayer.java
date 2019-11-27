package io.farkle.dignifiedfarkleservice.view;

import io.farkle.dignifiedfarkleservice.model.entity.Game;
import io.farkle.dignifiedfarkleservice.model.entity.GamePlayer;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import org.springframework.lang.NonNull;

public interface FlatPlayer {

  long getId();

  Date getCreated();

  String getDisplayName();

  int getWinCount();

  String getDiceUpgrade();

  double getWinRate();

  int getVictoryPoints();

  int getHighestScore();


}