package diplomski.nutrition.service;

import java.util.Set;

import diplomski.nutrition.entity.RecipeMeal;

public interface RecipeMealServiceInterface {

	Set<RecipeMeal> findRecipesByMealId(Long id);
	
	RecipeMeal save(RecipeMeal recipeMeal);
	
	RecipeMeal findById(Long id);
	
	void deleteById(Long id);
}
