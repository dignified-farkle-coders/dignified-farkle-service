package io.farkle.dignifiedfarkleservice.service;

import io.farkle.dignifiedfarkleservice.model.dao.PlayerRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Player;
import io.farkle.dignifiedfarkleservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private final PlayerRepository repository;

  @Autowired
  private PlayerService(PlayerRepository repository) {
    this.repository = repository;
  }

  public Player getOrCreatePlayer(String oauthKey) {
    return repository.getPlayerByOauthKey(oauthKey)
        .orElseGet(() -> {
          Player player= new Player();
          player.setOauthKey(oauthKey);
          return repository.save(player);
        });
  }
}