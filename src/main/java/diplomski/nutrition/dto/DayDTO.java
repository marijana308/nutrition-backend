package diplomski.nutrition.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import diplomski.nutrition.entity.Day;
import diplomski.nutrition.entity.DayExercise;
import diplomski.nutrition.entity.Exercise;
import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.entity.NutritionixExerciseDay;

public class DayDTO {

	private Date date;
	private String username;
	private Float totalWaterIntake;
	private Set<MealDTO> meals;
	private Set<DayExerciseDTO> exercises;
	private Set<String> nutritionixExercises;//query for nutritionixAPI
	
	public DayDTO() {
		super();
	}

	public DayDTO(Date date, String username, Float totalWaterIntake, Set<MealDTO> meals, Set<DayExerciseDTO> exercises,
			Set<String> nutritionixExercises) {
		super();
		this.date = date;
		this.username = username;
		this.totalWaterIntake = totalWaterIntake;
		this.meals = meals;
		this.exercises = exercises;
		this.nutritionixExercises = nutritionixExercises;
	}
	
//	public DayDTO getDayDTO(Day day) {
//		Set<MealDTO> mealDTOs = new HashSet<MealDTO>();
//		for(Meal meal : day.getMeals()) {
//			mealDTOs.add(meal.toMealDTO());
//		}
//		Set<ExerciseDTO> exerciseDTOs = new HashSet<ExerciseDTO>();
//		for(DayExercise exercise: day.getExercises()) {
//			exerciseDTOs.add(exercise.toDayExerciseDTO());
//		}
//		Set<String> nutritionExercisesDTO = new HashSet<String>();
//		for(NutritionixExerciseDay nutritionixExerciseDay : day.getNutritionixExercises()) {
//			nutritionExercisesDTO.add(nutritionixExerciseDay.getExerciseQuery());
//		}
//		return new DayDTO(day.getDate(), day.getUser().getUsername(), day.getTotalWaterIntake(), mealDTOs, exerciseDTOs, nutritionExercisesDTO);
//	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Float getTotalWaterIntake() {
		return totalWaterIntake;
	}

	public void setTotalWaterIntake(Float totalWaterIntake) {
		this.totalWaterIntake = totalWaterIntake;
	}

	public Set<MealDTO> getMeals() {
		return meals;
	}

	public void setMeals(Set<MealDTO> meals) {
		this.meals = meals;
	}

	public Set<DayExerciseDTO> getExercises() {
		return exercises;
	}

	public void setExercises(Set<DayExerciseDTO> exercises) {
		this.exercises = exercises;
	}

	public Set<String> getNutritionixExercises() {
		return nutritionixExercises;
	}

	public void setNutritionixExercises(Set<String> nutritionixExercises) {
		this.nutritionixExercises = nutritionixExercises;
	}
}
