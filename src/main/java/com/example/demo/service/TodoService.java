package com.example.demo.service;


import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> findAll(){
        return todoRepository.findAll();

    }

    public Todo getById(int id){
        return todoRepository.findById(id).get();
    }

    public void insert(Todo todo){
        todoRepository.save(todo);

    }

    public void update(Todo todo){
        todoRepository.save(todo);
    }
}
