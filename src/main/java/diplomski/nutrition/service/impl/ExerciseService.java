package diplomski.nutrition.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.Exercise;
import diplomski.nutrition.enumeration.Role;
import diplomski.nutrition.repository.ExerciseRepository;
import diplomski.nutrition.service.ExerciseServiceInterface;

@Service
public class ExerciseService implements ExerciseServiceInterface{

	@Autowired
	ExerciseRepository exerciseRepository;

	@Override
	public Exercise findByName(String name) {
		for(Exercise exercise : exerciseRepository.findAll()) {
			if(exercise.getName().equals(name)) {
				return exercise;
			}
		}
		return null;
	}

	@Override
	public Exercise save(Exercise exercise) {
		return exerciseRepository.save(exercise);
	}

	@Override
	public Exercise findById(Long id) {
		return exerciseRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		exerciseRepository.deleteById(id);
	}

	@Override
	public List<Exercise> findExercisesCreatedByAdmins() {
		List<Exercise> exercises = new ArrayList<Exercise>();
		for(Exercise exercise : exerciseRepository.findAll()) {
			if(exercise.getCreatedByUser().getRole().equals(Role.ADMIN)) {
				exercises.add(exercise);
			}
		}
		return exercises;
	}

	@Override
	public List<Exercise> findExercisesByUsername(String username) {
		List<Exercise> exercises = new ArrayList<Exercise>();
		for(Exercise exercise : exerciseRepository.findAll()) {
			if(exercise.getCreatedByUser().getUsername().equals(username)) {
				exercises.add(exercise);
			}
		}
		return exercises;
	}
}
