package diplomski.nutrition.service;

import java.util.List;
import java.util.Set;

import diplomski.nutrition.entity.FoodMeal;

public interface FoodMealServiceInterface {

	FoodMeal save(FoodMeal foodMeal);
	
	Set<FoodMeal> findFoodsByMealId(Long mealid);
	
	FoodMeal findById(Long foodid);
	
	void deleteById(Long foodid);
}
