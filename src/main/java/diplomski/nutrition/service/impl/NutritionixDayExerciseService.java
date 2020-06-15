package diplomski.nutrition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.NutritionixExerciseDay;
import diplomski.nutrition.repository.NutritionixDayExerciseRepository;
import diplomski.nutrition.service.NutritionixDayExerciseServiceInterface;

@Service
public class NutritionixDayExerciseService implements NutritionixDayExerciseServiceInterface{

	@Autowired
	NutritionixDayExerciseRepository nutritionixDayExerciseRepository;
	
	@Override
	public NutritionixExerciseDay save(NutritionixExerciseDay n) {
		return nutritionixDayExerciseRepository.save(n);
	}

}
