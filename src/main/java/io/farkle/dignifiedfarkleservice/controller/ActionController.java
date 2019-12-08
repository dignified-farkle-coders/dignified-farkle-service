package io.farkle.dignifiedfarkleservice.controller;

import io.farkle.dignifiedfarkleservice.model.dao.ActionRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/actions")
@Api(value = "ActionsControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActionController {

  private final ActionRepository repository;

  public ActionController(ActionRepository repository) {
    this.repository = repository;
  }

  @ApiOperation("actions")
  @ApiResponses( value =  {@ApiResponse(code = 200, message = "Ok" , response = Action.class)})
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Action> get(){
    return repository.getAllBy();
  }


  @ApiOperation(" ")
  @ApiResponses( value =  {@ApiResponse(code = 200, message = "Ok" , response = Action.class)})
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Action post(@RequestBody Action action) {
    // TODO Validation
    // TODO Execute game logic
    return repository.save(action);
  }

  @ApiOperation("Gets frozen")
  @ApiResponses( value =  {@ApiResponse(code = 200, message = "Ok" , response = Action.class)})
  @GetMapping(value = "{id:\\d+}/frozen", produces = MediaType.APPLICATION_JSON_VALUE)
  public int[] getFrozen(@PathVariable long id) {
    return repository.findById(id).get().getFrozenDice();
  }

  @ApiOperation("id")
  @ApiResponses( value =  {@ApiResponse(code = 200, message = "Ok" , response = Action.class)})
  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Action get(@PathVariable long id) {
    return repository.findById(id).get();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

}
