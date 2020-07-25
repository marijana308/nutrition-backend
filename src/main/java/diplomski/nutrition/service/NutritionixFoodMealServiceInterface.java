package diplomski.nutrition.service;

import java.util.List;
import java.util.Set;

import diplomski.nutrition.entity.NutritionixFoodMeal;

public interface NutritionixFoodMealServiceInterface {

	NutritionixFoodMeal save(NutritionixFoodMeal nutritionixFoodMeal);
	
	Set<NutritionixFoodMeal> findFoodsByMealId(Long mealid);
	
	NutritionixFoodMeal findById(Long foodid);
	
	void deleteById(Long foodid);
}
