package com.example.demo.controller;


import com.example.demo.model.JsonResponse;
import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/all")
    List<Todo> list(){
        return todoService.findAll();
    }

    @PostMapping("/create")
    Todo createTodo(@RequestBody Todo todo){
        todoService.insert(todo);
        return todo;
    }

    @PostMapping("/update")
    Todo updateTodo(@RequestBody Todo todo){
        todoService.update(todo);
        return todo;
    }

    @GetMapping("/find/{id}")
    ResponseEntity<JsonResponse<?>> findById(@PathVariable("id") int id){
        try {
            Todo todo = todoService.getById(id);
            JsonResponse<Todo> response= new JsonResponse<>(200,todo);
            return new ResponseEntity<JsonResponse<?>>(response,HttpStatus.OK);
        }catch (Exception e){
            JsonResponse<String> response= new JsonResponse<>(500,"No encontre el elemento con el id "+id);
            return new ResponseEntity<JsonResponse<?>>(response,HttpStatus.OK);
        }

    }
    @GetMapping("/delete/{id}")
    ResponseEntity<JsonResponse<?>> deleteTodo(@PathVariable("id") int id){
        try {
            todoService.deleteTodo(id);
            JsonResponse<String> response= new JsonResponse<>(200,"Todo "+id+" eliminado");
            return new ResponseEntity<JsonResponse<?>>(response,HttpStatus.OK);
        }catch (Exception e){
            JsonResponse<String> response= new JsonResponse<>(500,"No encontre el elemento con el id "+id);
            return new ResponseEntity<JsonResponse<?>>(response,HttpStatus.OK);
        }

    }
    @PostMapping("/find/title")
    ResponseEntity<JsonResponse<?>> findByTitle(@RequestBody Todo todoRequest){
        try {
            Todo todo = todoService.getByTitle(todoRequest.getTitle());
            JsonResponse<Todo> response= new JsonResponse<>(200,todo);
            return new ResponseEntity<JsonResponse<?>>(response,HttpStatus.OK);
        }catch (Exception e){
            JsonResponse<String> response= new JsonResponse<>(500,"No encontre el elemento con el título "+todoRequest.getTitle());
            return new ResponseEntity<JsonResponse<?>>(response,HttpStatus.OK);
        }

    }
    @GetMapping("/error")
    String generateError(){
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
        return "Error enviado";
    }
}