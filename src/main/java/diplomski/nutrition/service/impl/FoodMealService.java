package diplomski.nutrition.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.FoodMeal;
import diplomski.nutrition.repository.FoodMealRepository;
import diplomski.nutrition.service.FoodMealServiceInterface;

@Service
public class FoodMealService implements FoodMealServiceInterface{
	
	@Autowired
	FoodMealRepository foodMealRepository;

	@Override
	public FoodMeal save(FoodMeal foodMeal) {
		return foodMealRepository.save(foodMeal);
	}

	@Override
	public Set<FoodMeal> findFoodsByMealId(Long mealid) {
		Set<FoodMeal> foods = new HashSet<FoodMeal>();
		for(FoodMeal fm: foodMealRepository.findAll()) {
			if(fm.getMeal().getId() == mealid) {
				foods.add(fm);
			}
		}
		return foods;
	}

	@Override
	public FoodMeal findById(Long foodid) {
		return foodMealRepository.findById(foodid).orElse(null);
	}

	@Override
	public void deleteById(Long foodid) {
		foodMealRepository.deleteById(foodid);
	}

}
