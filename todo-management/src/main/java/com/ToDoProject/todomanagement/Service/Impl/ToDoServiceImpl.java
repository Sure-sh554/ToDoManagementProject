package com.ToDoProject.todomanagement.Service.Impl;

import com.ToDoProject.todomanagement.Dto.ToDoDto;
import com.ToDoProject.todomanagement.Entity.ToDo;
import com.ToDoProject.todomanagement.Exception.ResourceNotFoundException;
import com.ToDoProject.todomanagement.Repository.ToDoRepository;
import com.ToDoProject.todomanagement.Service.ToDoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {
    private ToDoRepository toDoRepository;
    private ModelMapper modelMapper;

    @Override
    public ToDoDto addToDo(ToDoDto toDoDto) {
        //Convert toto DTO into ToDo JPA entity
//        ToDo toDo=new ToDo();
//        toDo.setTitle(toDoDto.getTitle());
//        toDo.setDescription(toDoDto.getDescription());
//        toDo.setCompleted(toDoDto.isCompleted());

        ToDo toDo =modelMapper.map(toDoDto,ToDo.class);
        //ToDo Jpa entity
       ToDo savedToDo= toDoRepository.save(toDo);
       //Convert savedToDo Jpa entity object into ToDo Object

//        ToDoDto savedToDoDto=new ToDoDto();
//        savedToDoDto.setId(savedToDo.getId());
//        savedToDoDto.setDescription(savedToDo.getDescription());
//        savedToDoDto.setId(savedToDoDto.getId());
//        savedToDoDto.setCompleted(savedToDo.isCompleted());

        ToDoDto savedToDoDto=modelMapper.map(savedToDo,ToDoDto.class);

        return savedToDoDto;
    }

    @Override
    public void deleteToDo(Long id) {
        toDoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("To DO not found with the id "+id));
        toDoRepository.deleteById(id);

    }

    @Override
    public ToDoDto getToDo(Long id) {
       ToDo toDo= toDoRepository.findById(id).orElseThrow((
               ()->new ResourceNotFoundException("To Do not found with id:"+id)
               ));
        return modelMapper.map(toDo,ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getAllToDos() {
        List<ToDo>toDos=toDoRepository.findAll();
        return toDos.stream().map((todo)->modelMapper.map(todo,ToDoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto, Long id) {
       ToDo toDo= toDoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("To Do not found with id"+id));
       toDo.setTitle(toDoDto.getTitle());
       toDo.setDescription(toDoDto.getDescription());
       toDo.setCompleted(toDoDto.isCompleted());
       ToDo updatedToDo=toDoRepository.save(toDo);
        return modelMapper.map(updatedToDo,ToDoDto.class);
    }

    @Override
    public ToDoDto completeToDo(Long id) {
       ToDo toDo= toDoRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("To Do not found with id"+id));
        toDo.setCompleted(Boolean.TRUE);
       ToDo updateToDo= toDoRepository.save(toDo);

        return modelMapper.map(updateToDo,ToDoDto.class);
    }

    @Override
    public ToDoDto inCompleteToDo(Long id) {
        ToDo toDo= toDoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("To Do not found with id"+id));
        toDo.setCompleted(Boolean.FALSE);

       ToDo updatedToDo= toDoRepository.save(toDo);
        return modelMapper.map(updatedToDo,ToDoDto.class);
    }


}
