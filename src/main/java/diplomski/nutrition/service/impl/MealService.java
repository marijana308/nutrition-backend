package diplomski.nutrition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.repository.MealRepository;
import diplomski.nutrition.service.MealServiceInterface;

@Service
public class MealService implements MealServiceInterface{

	@Autowired
	MealRepository mealRepository;

	@Override
	public Meal save(Meal meal) {
		return mealRepository.save(meal);
	}
}
