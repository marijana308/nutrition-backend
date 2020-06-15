package diplomski.nutrition.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.Recipe;
import diplomski.nutrition.repository.RecipeRepository;
import diplomski.nutrition.service.RecipeServiceInterface;

@Service
public class RecipeService implements RecipeServiceInterface{

	@Autowired
	RecipeRepository recipeRepository;

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public List<Recipe> findRecipesByUsername(String username) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		for(Recipe r : recipeRepository.findAll()) {
			if(r.getUser().getUsername().equals(username)) {
				recipes.add(r);
			}
		}
		return recipes;
	}
}
