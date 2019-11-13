package io.farkle.dignifiedfarkleservice.controller;

import io.farkle.dignifiedfarkleservice.model.dao.ActionRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Action;
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
@RequestMapping("actions")
public class ActionController {

  private final ActionRepository repository;

  public ActionController(ActionRepository repository) {
    this.repository = repository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Action> get(){
    return repository.getAllBy();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Action post(@RequestBody Action action) {
    // TODO Validation
    // TODO Execute game logic
    return repository.save(action);
  }

  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Action get(@PathVariable long id) {
    return repository.findById(id).get();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

}
