package com.example.demo;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TodoControllerTest extends DemoApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoService todoService;

    @Test
    public void testFindByIdOk() throws Exception {

        mockMvc.perform(get("/v1/todo/find/1")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data.title").value("Tarea de progra"))
                .andExpect(jsonPath("$.data.id").value(1));


    }

    @Test
    public void testFindByIdError() throws  Exception{
        mockMvc.perform(get("/v1/todo/find/18")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.codigo").value("500"));
    }

    @Test
    public void insertTodo() throws Exception{
        Todo todo=new Todo();
        todo.setTitle("Insert de prueba");
        todo.setFinished(false);
        todo.setComments("Comments todo de prueba");
        ObjectMapper mapper= new ObjectMapper();
        ObjectWriter ow= mapper.writer().withDefaultPrettyPrinter();
        String jsonBody=ow.writeValueAsString(todo);
        mockMvc.perform(post("/v1/todo/create").contentType(MediaType.APPLICATION_JSON).content(jsonBody)).andExpect(status().isOk());
        todo=todoService.getByTitle("Insert de prueba");
        todoService.deleteTodo(todo.getId());
    }
}