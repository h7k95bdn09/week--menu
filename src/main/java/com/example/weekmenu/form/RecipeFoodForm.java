package com.example.weekmenu.form;

import java.util.List;

import com.example.weekmenu.entity.RecipeSummary;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecipeFoodForm {
	private List<RecipeSummary> recipeSummaryList;
	
	@NotNull(message = "食費を入力してください。")
	@Min(value = 1, message = "食費は1円以上を入力してください。")
	@Max(value = 100000, message = "食費は100000円を入力してください。")
	private Integer searchValue;
	
	private Integer sum;
}