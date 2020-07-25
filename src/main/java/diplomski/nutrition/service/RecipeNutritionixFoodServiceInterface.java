package diplomski.nutrition.service;

import diplomski.nutrition.entity.RecipeNutritionixFood;

public interface RecipeNutritionixFoodServiceInterface {

	RecipeNutritionixFood save(RecipeNutritionixFood rnf);
	
	RecipeNutritionixFood findById(Long id);
	
	void deleteById(Long id);
}
