package io.farkle.dignifiedfarkleservice.controller;

import io.farkle.dignifiedfarkleservice.model.dao.ActionRepository;
import io.farkle.dignifiedfarkleservice.model.dao.GamePlayerRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.farkle.dignifiedfarkleservice.model.entity.GamePlayer;
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
@RequestMapping("/gameplayer")
public class GamePlayerController {

  private final GamePlayerRepository repository;

  public GamePlayerController(GamePlayerRepository repository) {
    this.repository = repository;
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<GamePlayer> get(){
    return repository.getAllBy();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public GamePlayer post(@RequestBody GamePlayer gameplayer) {
    // TODO Validation
    // TODO Execute game logic
    return repository.save(gameplayer);
  }


  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GamePlayer get(@PathVariable long id) {
    return repository.findById(id).get();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

}
