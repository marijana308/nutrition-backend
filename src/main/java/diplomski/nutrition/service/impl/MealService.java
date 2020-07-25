package diplomski.nutrition.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.enumeration.MealType;
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

	@Override
	public List<Meal> findMealsByDayId(Long dayid) {
		List<Meal> meals = new ArrayList<Meal>();
		for(Meal m : mealRepository.findAll()) {
			if(m.getDay().getId() == dayid) {
				meals.add(m);
			}
		}
		return meals;
	}

	@Override
	public Meal findMealById(Long mealid) {
		return mealRepository.findById(mealid).orElse(null);
	}
	
	@Override
	public Meal findMealByDayIdAndMealType(Long dayid, MealType mealType) {
		for(Meal m : mealRepository.findAll()) {
			if(m.getDay().getId() == dayid && m.getMealType().equals(mealType)) {
				return m;
			}
		}
		return null;
	}
}
