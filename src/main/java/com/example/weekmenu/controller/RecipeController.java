package com.example.weekmenu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.weekmenu.entity.RecipeFood;
import com.example.weekmenu.entity.RecipeSummary;
import com.example.weekmenu.form.RecipeFoodForm;
import com.example.weekmenu.repository.RecipeFoodRepository;
import com.example.weekmenu.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	private final RecipeService recipeService;
	private final RecipeFoodRepository recipeFoodRepository;

	public RecipeController(RecipeService recipeService, RecipeFoodRepository recipeFoodRepository) {
		this.recipeService = recipeService;
		this.recipeFoodRepository = recipeFoodRepository;
	}

	@GetMapping
	public String index(Model model) {
		RecipeFoodForm form = new RecipeFoodForm();
		model.addAttribute("recipeFoodForm", form);

		return "recipe/index";
	}

	@PostMapping("/search")
	public String search(Model model, @ModelAttribute @Validated RecipeFoodForm form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "recipe/index";
		}
		List<RecipeSummary> summary = recipeService.searchRecipeSummary(form.getSearchValue());

		int sum = summary.stream().map((recipe) -> recipe.getTotal()).reduce(0, (accum, val) -> accum + val);
		form.setSum(sum);
		form.setRecipeSummaryList(summary);

		model.addAttribute("recipeFoodForm", form);

		return "recipe/index";
	}
	
	@GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        List<RecipeFood> recipeFood = recipeFoodRepository.findAll();
        List<RecipeFood> recipeFoodList = recipeFood.stream().filter(v -> v.getRecipe().getId() == id).toList();
        
        model.addAttribute("recipeFood", recipeFoodList);
        
        return "recipe/detail";
    }   

}
