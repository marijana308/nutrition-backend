package diplomski.nutrition.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import diplomski.nutrition.dto.DayDTO;
import diplomski.nutrition.dto.ExerciseDTO;
import diplomski.nutrition.dto.MealDTO;

@Entity
public class Day {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;
	
	@OneToMany(mappedBy="day", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Meal> meals = new HashSet<Meal>();
	
	@OneToMany(mappedBy="day", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<DayExercise> exercises = new HashSet<DayExercise>();
	
	@OneToMany(mappedBy = "day", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<NutritionixExerciseDay> nutritionixExercises = new HashSet<NutritionixExerciseDay>();
	
	private Float totalWaterIntake;
	
	public Day() {
		super();
	}
	
//	public DayDTO toDayDTO(Day day) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}

	public Set<DayExercise> getExercises() {
		return exercises;
	}

	public void setExercises(Set<DayExercise> exercises) {
		this.exercises = exercises;
	}

	public Float getTotalWaterIntake() {
		return totalWaterIntake;
	}

	public void setTotalWaterIntake(Float totalWaterIntake) {
		this.totalWaterIntake = totalWaterIntake;
	}

	public Set<NutritionixExerciseDay> getNutritionixExercises() {
		return nutritionixExercises;
	}

	public void setNutritionixExercises(Set<NutritionixExerciseDay> nutritionixExercises) {
		this.nutritionixExercises = nutritionixExercises;
	}
}
