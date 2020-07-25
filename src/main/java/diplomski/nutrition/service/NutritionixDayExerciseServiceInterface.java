package diplomski.nutrition.service;

import java.util.List;
import java.util.Set;

import diplomski.nutrition.entity.NutritionixExerciseDay;

public interface NutritionixDayExerciseServiceInterface {

	NutritionixExerciseDay save(NutritionixExerciseDay n);
	
	Set<NutritionixExerciseDay> findExercisesByDayId(Long dayid);
	
	NutritionixExerciseDay findById(Long id);
	
	void deleteById(Long id);
}
