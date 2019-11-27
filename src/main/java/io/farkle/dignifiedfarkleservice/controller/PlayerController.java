package io.farkle.dignifiedfarkleservice.controller;

import io.farkle.dignifiedfarkleservice.model.dao.ActionRepository;
import io.farkle.dignifiedfarkleservice.model.dao.PlayerRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.farkle.dignifiedfarkleservice.model.entity.Players;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("players")
public class PlayerController {

  private final PlayerRepository repository;

  public PlayerController(PlayerRepository repository) {
    this.repository = repository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Players> get(){
    return repository.getAllBy();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Players post(@RequestBody Players players) {
    // TODO Validation
    // TODO Execute game logic
    return repository.save(players);
  }

  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Players get(@PathVariable long id) {
    return repository.findById(id).get();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

}
