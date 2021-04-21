package com.example.demo.repository;


import com.example.demo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {

    @Query("SELECT t from Todo t where t.title=?1")
    Todo findByTitle(String title);
}