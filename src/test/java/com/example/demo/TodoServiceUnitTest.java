
package com.example.demo;

import com.example.demo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoServiceUnitTest extends DemoApplicationTests{

    @Autowired
    TodoService todoService;

    @Test
    public void debeDecirHello(){
        assertEquals("hello",todoService.sayHello());
    }

    @Test
    public void pruebaSuma(){
        assertEquals(2,todoService.findAll().size());
    }
}
