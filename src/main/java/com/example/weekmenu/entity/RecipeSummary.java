package com.example.weekmenu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "recipe_summary")
@Data
public class RecipeSummary {

	@Id
	@Column(name = "recipe_id")
	private Integer recipeId;

	@Column(name = "name")
	private String name;

	@Column(name = "image_name")
	private String imageName;

	@Column(name = "total")
	private Integer total;

}
