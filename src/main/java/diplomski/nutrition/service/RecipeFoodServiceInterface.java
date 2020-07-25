package diplomski.nutrition.service;

import diplomski.nutrition.entity.RecipeFood;

public interface RecipeFoodServiceInterface {
	
	RecipeFood save(RecipeFood recipeFood);
	
	RecipeFood findById(Long id);

	void deleteById(Long id);
}
