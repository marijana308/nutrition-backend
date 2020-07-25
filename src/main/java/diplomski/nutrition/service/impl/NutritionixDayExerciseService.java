package diplomski.nutrition.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Override
	public Set<NutritionixExerciseDay> findExercisesByDayId(Long dayid) {
		Set<NutritionixExerciseDay> exercises = new HashSet<NutritionixExerciseDay>();
		for(NutritionixExerciseDay e: nutritionixDayExerciseRepository.findAll()) {
			if(e.getDay().getId() == dayid) {
				exercises.add(e);
			}
		}
		return exercises;
	}

	@Override
	public NutritionixExerciseDay findById(Long id) {
		return nutritionixDayExerciseRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		nutritionixDayExerciseRepository.deleteById(id);
	}
}
