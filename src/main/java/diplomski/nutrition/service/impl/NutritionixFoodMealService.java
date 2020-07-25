package diplomski.nutrition.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.FoodMeal;
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

	@Override
	public Set<NutritionixFoodMeal> findFoodsByMealId(Long mealid) {
		Set<NutritionixFoodMeal> foods = new HashSet<NutritionixFoodMeal>();
		for(NutritionixFoodMeal fm: nutritionixFoodMealRepository.findAll()) {
			if(fm.getMeal().getId() == mealid) {
				foods.add(fm);
			}
		}
		return foods;
	}

	@Override
	public NutritionixFoodMeal findById(Long foodid) {
		return nutritionixFoodMealRepository.findById(foodid).orElse(null);
	}

	@Override
	public void deleteById(Long foodid) {
		nutritionixFoodMealRepository.deleteById(foodid);
	}

}
