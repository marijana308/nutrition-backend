package diplomski.nutrition.service;

import java.util.Set;

import diplomski.nutrition.entity.RecipeFood;

public interface RecipeFoodServiceInterface {
	
	RecipeFood save(RecipeFood recipeFood);
	
	RecipeFood findById(Long id);

	void deleteById(Long id);
	
	Set<RecipeFood> findFoodsByRecipeId(Long recipeid);
}
