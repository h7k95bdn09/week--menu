package com.example.weekmenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weekmenu.entity.RecipeFood;

public interface RecipeFoodRepository extends JpaRepository<RecipeFood, Integer> {

}
