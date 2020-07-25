package diplomski.nutrition.service;

import java.util.List;

import diplomski.nutrition.entity.DayExercise;
import diplomski.nutrition.entity.Exercise;

public interface ExerciseServiceInterface {
	
	Exercise findByName(String name);
	
	Exercise save(Exercise exercise);
	
	Exercise findById(Long id);
	
	void deleteById(Long id);
	
	List<Exercise> findExercisesCreatedByAdmins();
	
	List<Exercise> findExercisesByUsername(String username);
	
	List<Exercise> searchExercisesCreatedByAdmins(String query);
	
	List<Exercise> searchExercisesByUsername(String username, String query);

}
