package com.example.weekmenu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.weekmenu.entity.RecipeSummary;
import com.example.weekmenu.repository.RecipeSummaryRepository;

@Service
public class RecipeService {

	private final RecipeSummaryRepository recipeSummaryRepository;

	public static final double RATE = 0.8;
	public static final int RECIPE_CNT = 5;
	public static final int ROOP_LIMIT = 10000;

	public RecipeService(RecipeSummaryRepository recipeSummaryRepository) {
		this.recipeSummaryRepository = recipeSummaryRepository;
	}

	public List<RecipeSummary> searchRecipeSummary(int searchValue) {

		List<RecipeSummary> recipes = recipeSummaryRepository.findAll();
		List<RecipeSummary> result = new ArrayList<RecipeSummary>();

		Integer min = (int) (searchValue * RATE);
		int roopCnt = 0;

		while (roopCnt < ROOP_LIMIT) {
			Collections.shuffle(recipes);

			List<RecipeSummary> target = recipes.subList(0, RECIPE_CNT);

			int sum = target.stream().map((recipe) -> recipe.getTotal()).reduce(0, (accum, val) -> accum + val);

			if (sum >= min && sum <= searchValue) {
				result = target;
				break;
			}

			roopCnt++;

		}
		return result;

	}

}
