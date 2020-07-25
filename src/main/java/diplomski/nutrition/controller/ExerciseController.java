package diplomski.nutrition.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import diplomski.nutrition.dto.DayExerciseDTO;
import diplomski.nutrition.dto.DayNutritionixExerciseDTO;
import diplomski.nutrition.dto.ExerciseDTO;
import diplomski.nutrition.dto.FoodMealDTO;
import diplomski.nutrition.entity.DayExercise;
import diplomski.nutrition.entity.Exercise;
import diplomski.nutrition.entity.FoodMeal;
import diplomski.nutrition.entity.NutritionixExerciseDay;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.DayExerciseService;
import diplomski.nutrition.service.impl.ExerciseService;
import diplomski.nutrition.service.impl.NutritionixDayExerciseService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/exercises")
public class ExerciseController {
	
	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	DayExerciseService dayExerciseService;
	
	@Autowired
	NutritionixDayExerciseService nutritionixDayExerciseService;
	
	@Autowired
	UserRepository userRepository;
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable Long id){
		Exercise exercise = exerciseService.findById(id);
		if(exercise == null) {
			return new ResponseEntity<ExerciseDTO>(new ExerciseDTO(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ExerciseDTO>(new ExerciseDTO(exercise), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<ExerciseDTO>> getExercisesByUsername(@PathVariable String username, @RequestParam String query){
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Exercise> exercises = exerciseService.searchExercisesByUsername(username, query);
		List<ExerciseDTO> exerciseDTOs = new ArrayList<ExerciseDTO>();
		for(Exercise e: exercises) {
			exerciseDTOs.add(new ExerciseDTO(e));
		}
		return new ResponseEntity<>(exerciseDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ResponseEntity<List<ExerciseDTO>> getExercisesCreatedByAdmins(@RequestParam String query){
		List<Exercise> exercises = exerciseService.searchExercisesCreatedByAdmins(query);
		List<ExerciseDTO> exerciseDTOs = new ArrayList<ExerciseDTO>();
		for(Exercise e: exercises) {
			exerciseDTOs.add(new ExerciseDTO(e));
		}
		return new ResponseEntity<>(exerciseDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ExerciseDTO> createExercise(@RequestBody ExerciseDTO exerciseDTO){
		Exercise exercise = new Exercise();
		User u = userRepository.findByUsername(exerciseDTO.getUsername());
		exercise.setCreatedByUser(u);
		exercise.setName(exerciseDTO.getName());
		exercise.setCaloriesBurned(exerciseDTO.getCaloriesBurned());
		exercise.setTime(exerciseDTO.getTime());
		
		exerciseService.save(exercise);
		
		return new ResponseEntity<>(new ExerciseDTO(exercise), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ExerciseDTO> updateExercise(@RequestBody ExerciseDTO exerciseDTO){
		Exercise exercise = exerciseService.findById(exerciseDTO.getId());
		if(exercise == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		exercise.setName(exerciseDTO.getName());
		exercise.setCaloriesBurned(exerciseDTO.getCaloriesBurned());
		exercise.setTime(exerciseDTO.getTime());
		
		exerciseService.save(exercise);
		
		return new ResponseEntity<>(new ExerciseDTO(exercise), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteExerciseById(@PathVariable Long id){
		Exercise exercise = exerciseService.findById(id);
		if(exercise == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		exerciseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/day/{dayid}",method = RequestMethod.GET)
	public ResponseEntity<List<DayExerciseDTO>> getExercisesByDayId(@PathVariable Long dayid){
		Set<DayExercise> exercises = dayExerciseService.findExercisesByDayId(dayid);
		List<DayExerciseDTO> exerciseDTOs = new ArrayList<DayExerciseDTO>();
		for(DayExercise e: exercises) {
			exerciseDTOs.add(new DayExerciseDTO(e));
		}
		return new ResponseEntity<List<DayExerciseDTO>>(exerciseDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/nutritionix/day/{dayid}",method = RequestMethod.GET)
	public ResponseEntity<List<DayNutritionixExerciseDTO>> getNutritionixExercisesByDayId(@PathVariable Long dayid){
		Set<NutritionixExerciseDay> exercises = nutritionixDayExerciseService.findExercisesByDayId(dayid);
		List<DayNutritionixExerciseDTO> exerciseDTOs = new ArrayList<DayNutritionixExerciseDTO>();
		for(NutritionixExerciseDay e: exercises) {
			exerciseDTOs.add(new DayNutritionixExerciseDTO(e));
		}
		return new ResponseEntity<List<DayNutritionixExerciseDTO>>(exerciseDTOs, HttpStatus.OK);
	}
}
