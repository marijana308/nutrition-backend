package diplomski.nutrition.service;

import java.util.List;

import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.enumeration.MealType;

public interface MealServiceInterface {
	
	Meal save(Meal meal);

	List<Meal> findMealsByDayId(Long dayid);
	
	Meal findMealById(Long mealid);
	
	Meal findMealByDayIdAndMealType(Long dayid, MealType mealType);
}
