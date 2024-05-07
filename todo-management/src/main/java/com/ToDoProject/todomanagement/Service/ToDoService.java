package com.ToDoProject.todomanagement.Service;

import com.ToDoProject.todomanagement.Dto.ToDoDto;
import com.ToDoProject.todomanagement.Entity.ToDo;

import java.util.List;

public interface ToDoService {

    ToDoDto addToDo(ToDoDto toDoDto);

    void deleteToDo(Long id);

    ToDoDto getToDo(Long id);

    List<ToDoDto> getAllToDos();

    ToDoDto updateToDo(ToDoDto toDoDto,Long id);

    ToDoDto completeToDo(Long id);

    ToDoDto inCompleteToDo(Long id);
}
