package diplomski.nutrition.service.impl;

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

}
