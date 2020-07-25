package diplomski.nutrition.service;

import java.util.List;
import java.util.Set;

import diplomski.nutrition.entity.DayExercise;

public interface DayExerciseServiceInterface {
	
	DayExercise save(DayExercise dayExercise);

	Set<DayExercise> findExercisesByDayId(Long dayid);
	
	DayExercise findById(Long id);
	
	void deleteById(Long id);
}
