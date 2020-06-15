package diplomski.nutrition.service.impl;

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

}
