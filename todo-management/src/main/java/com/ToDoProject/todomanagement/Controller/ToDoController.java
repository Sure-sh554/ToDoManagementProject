package com.ToDoProject.todomanagement.Controller;

import com.ToDoProject.todomanagement.Dto.ToDoDto;
import com.ToDoProject.todomanagement.Entity.ToDo;
import com.ToDoProject.todomanagement.Service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class ToDoController {
   private ToDoService toDoService;

   //Build Add ToDo Rest API
@PostMapping
    public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto toDoDto){
       ToDoDto savedToDo= toDoService.addToDo(toDoDto);
       return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);

    }
@DeleteMapping("{id}")
    public  ResponseEntity<String> deleteToDo(@PathVariable("id")Long toDoId){
        toDoService.deleteToDo(toDoId);
        return ResponseEntity.ok("To Do deleted Successfully");
    }
@GetMapping("{id}")
    public ResponseEntity<ToDoDto> getToDo(@PathVariable("id") Long todoId){
    ToDoDto toDoDto=toDoService.getToDo(todoId);
    return new ResponseEntity<>(toDoDto,HttpStatus.OK);

    }
@GetMapping
    public ResponseEntity<List<ToDoDto>> getAllToDos(){
    List<ToDoDto>toDoDtos= toDoService.getAllToDos();
   return new ResponseEntity<>(toDoDtos,HttpStatus.OK);
    }
@PutMapping("{id}")
    public ResponseEntity<ToDoDto> updateToDo(@RequestBody ToDoDto toDoDto,@PathVariable("id") Long todoId){
    ToDoDto updatedToDo=toDoService.updateToDo(toDoDto,todoId);
    return ResponseEntity.ok(updatedToDo);
    }

@PatchMapping("{id}/complete")
    public ResponseEntity<ToDoDto> completeToDo(@PathVariable("id") Long todoId){
    ToDoDto updatedToDo=toDoService.completeToDo(todoId);
    return ResponseEntity.ok(updatedToDo);
    }
@PatchMapping("{id}/in-complete")
    public ResponseEntity<ToDoDto> inCompleteToDo(@PathVariable("id") Long todoId){
    ToDoDto updatedToDo=toDoService.inCompleteToDo(todoId);
    return ResponseEntity.ok(updatedToDo);

    }

}
