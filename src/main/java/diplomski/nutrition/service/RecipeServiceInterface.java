package diplomski.nutrition.service;

import java.util.List;

import diplomski.nutrition.entity.Recipe;

public interface RecipeServiceInterface {
	
	Recipe save(Recipe recipe);
	
	List<Recipe> findRecipesByUsername(String username);

}
