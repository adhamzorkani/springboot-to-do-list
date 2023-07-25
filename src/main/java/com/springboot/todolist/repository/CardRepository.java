package com.springboot.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.todolist.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
