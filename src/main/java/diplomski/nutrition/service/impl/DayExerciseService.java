package diplomski.nutrition.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.DayExercise;
import diplomski.nutrition.repository.DayExerciseRepository;
import diplomski.nutrition.service.DayExerciseServiceInterface;

@Service
public class DayExerciseService implements DayExerciseServiceInterface{
	
	@Autowired
	DayExerciseRepository dayExerciseRepository;

	@Override
	public DayExercise save(DayExercise dayExercise) {
		return dayExerciseRepository.save(dayExercise);
	}

	@Override
	public Set<DayExercise> findExercisesByDayId(Long dayid) {
		Set<DayExercise> exercises = new HashSet<DayExercise>();
		for(DayExercise e: dayExerciseRepository.findAll()) {
			if(e.getDay().getId() == dayid) {
				exercises.add(e);
			}
		}
		return exercises;
	}

	@Override
	public DayExercise findById(Long id) {
		return dayExerciseRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		dayExerciseRepository.deleteById(id);
	}
}
