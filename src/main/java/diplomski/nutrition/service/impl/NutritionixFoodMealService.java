package diplomski.nutrition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.NutritionixFoodMeal;
import diplomski.nutrition.repository.NutritionixFoodMealRepository;
import diplomski.nutrition.service.NutritionixFoodMealServiceInterface;

@Service
public class NutritionixFoodMealService implements NutritionixFoodMealServiceInterface{
	
	@Autowired
	NutritionixFoodMealRepository nutritionixFoodMealRepository;

	@Override
	public NutritionixFoodMeal save(NutritionixFoodMeal nutritionixFoodMeal) {
		return nutritionixFoodMealRepository.save(nutritionixFoodMeal);
	}

}
