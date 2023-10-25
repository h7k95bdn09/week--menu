package com.example.weekmenu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.weekmenu.entity.RecipeFood;
import com.example.weekmenu.form.RecipeFoodForm;
import com.example.weekmenu.repository.RecipeFoodRepository;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	private final RecipeFoodRepository recipefoodRepository;

	public RecipeController(RecipeFoodRepository recipefoodRepository) {
		this.recipefoodRepository = recipefoodRepository;
	}
	
	@GetMapping    
 	public String index(Model model) {
		List<RecipeFood> list = recipefoodRepository.findAll();
		
		RecipeFoodForm form = new RecipeFoodForm();
		form.setRecipeFoodList(list);
		
		model.addAttribute("recipeFoodForm", form);
		
		return "recipe/index";
	}	
}

