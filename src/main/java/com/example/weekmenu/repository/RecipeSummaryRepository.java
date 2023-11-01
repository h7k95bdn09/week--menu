package com.example.weekmenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weekmenu.entity.RecipeSummary;

public interface RecipeSummaryRepository extends JpaRepository<RecipeSummary, Integer> {

}
