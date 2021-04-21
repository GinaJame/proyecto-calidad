package com.example.demo;


import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void findAllRepositoryTest(){

        List<Todo> todoList=todoRepository.findAll();
        assertEquals(2,todoRepository.findAll().size());
        assertEquals(1,todoList.get(0).getId());
    }
}