package io.farkle.dignifiedfarkleservice.service;

import io.farkle.dignifiedfarkleservice.model.dao.PlayerRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private final PlayerRepository repository;

  @Autowired
  private PlayerService(PlayerRepository repository) {
    this.repository = repository;
  }

  public Players getOrCreatePlayer(String oauthKey, String displayName) {
    return repository.getPlayerByOauthKey(oauthKey)
        .map(
            player -> {
              player.setDisplayName(displayName);
              return repository.save(player);
            }
        )
        .orElseGet(() -> {
          Players player= new Players();
          player.setOauthKey(oauthKey);
          player.setDisplayName(displayName);
          return repository.save(player);
        });
  }
}