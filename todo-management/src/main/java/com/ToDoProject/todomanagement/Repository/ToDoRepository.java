package com.ToDoProject.todomanagement.Repository;

import com.ToDoProject.todomanagement.Entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
