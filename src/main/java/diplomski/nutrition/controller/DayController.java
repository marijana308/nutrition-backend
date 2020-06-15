package diplomski.nutrition.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diplomski.nutrition.dto.DayDTO;
import diplomski.nutrition.dto.DayExerciseDTO;
import diplomski.nutrition.dto.ExerciseDTO;
import diplomski.nutrition.dto.FoodMealDTO;
import diplomski.nutrition.dto.MealDTO;
import diplomski.nutrition.dto.NutritionixFoodMealDTO;
import diplomski.nutrition.dto.UserDTO;
import diplomski.nutrition.entity.Day;
import diplomski.nutrition.entity.DayExercise;
import diplomski.nutrition.entity.FoodMeal;
import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.entity.NutritionixExerciseDay;
import diplomski.nutrition.entity.NutritionixFoodMeal;
import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.enumeration.Role;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.DayExerciseService;
import diplomski.nutrition.service.impl.DayService;
import diplomski.nutrition.service.impl.ExerciseService;
import diplomski.nutrition.service.impl.FoodMealService;
import diplomski.nutrition.service.impl.FoodService;
import diplomski.nutrition.service.impl.MealService;
import diplomski.nutrition.service.impl.NutritionixDayExerciseService;
import diplomski.nutrition.service.impl.NutritionixFoodMealService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/days")
public class DayController {
	
	@Autowired
	private DayService dayService;
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private FoodMealService foodMealService;
	
	@Autowired
	private MealService mealService;
	
	@Autowired
	private NutritionixFoodMealService nutritionixFoodMealService;
	
	@Autowired
	private DayExerciseService dayExerciseService;
	
	@Autowired
	private NutritionixDayExerciseService nutritionixDayExerciseService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> addDay(@RequestBody DayDTO dayDTO){
//		System.out.println("addDay called, dayDTO = " + dayDTO.toString());
//		System.out.println("addDay called, dayDTO username = " + dayDTO.getUsername());
//		System.out.println("addDay called, dayDTO water intake = " + dayDTO.getTotalWaterIntake());
//		System.out.println("addDay called, dayDTO date = " + dayDTO.getDate());
//		System.out.println("addDay called, dayDTO exercises = " + dayDTO.getNutritionixExercises());
//		for(MealDTO mD: dayDTO.getMeals()) {
//			System.out.println("addDay called, dayDTO mealtype = " + mD.getMealType());
//			for(NutritionixFoodMealDTO nfmd: mD.getNutritionixFoods()) {
//				System.out.println("addDay called, dayDTO nutritionix food name = " + nfmd.getFoodNameQuery());
//				System.out.println("addDay called, dayDTO nutritionix quantity = " + nfmd.getQuantity());
//				System.out.println("addDay called, dayDTO nutritionix servingSize = " + nfmd.getServingSize());
//			}
//		}
		Day day = new Day();
		User user = userRepository.findByUsername(dayDTO.getUsername());
		Set<Meal> meals= new HashSet<Meal>();
		for(MealDTO mealDTO : dayDTO.getMeals()) {
			Meal meal = new Meal();
			meal.setMealType(mealDTO.getMealType());
			Set<FoodMeal> foods = new HashSet<FoodMeal>();
			for(FoodMealDTO foodMealDTO: mealDTO.getFoods()) {
				FoodMeal foodMeal= new FoodMeal();
				foodMeal.setQuantity(foodMealDTO.getQuantity());
				foodMeal.setServingSize(foodMealDTO.getServingSize());
				foodMeal.setFood(foodService.findByName(foodMealDTO.getFood()));
				foodMeal.setMeal(meal); 
				foods.add(foodMeal);
			}
			meal.setFoods(foods); 
			Set<NutritionixFoodMeal> nutritionixFoods = new HashSet<NutritionixFoodMeal>();
			for(NutritionixFoodMealDTO nutritionixFoodMealDTO : mealDTO.getNutritionixFoods()) {
				NutritionixFoodMeal nutritionixFoodMeal = new NutritionixFoodMeal();
				nutritionixFoodMeal.setQuantity(nutritionixFoodMealDTO.getQuantity());
				nutritionixFoodMeal.setServingSize(nutritionixFoodMealDTO.getServingSize());
				nutritionixFoodMeal.setFoodNameQuery(nutritionixFoodMealDTO.getFoodNameQuery());
				nutritionixFoodMeal.setMeal(meal); 
				nutritionixFoods.add(nutritionixFoodMeal);
			}
			meal.setNutritionixFoods(nutritionixFoods); 
			meal.setDay(day); 
			meals.add(meal);
		}
		Set<DayExercise> exercises = new HashSet<DayExercise>();
		for(DayExerciseDTO exerciseDTO : dayDTO.getExercises()) {
			DayExercise dayExercise = new DayExercise();
			dayExercise.setExercise(exerciseService.findById(exerciseDTO.getExerciseID()));
			dayExercise.setTime(exerciseDTO.getTime());
			dayExercise.setDay(day);
			exercises.add(dayExercise);
		}
		Set<NutritionixExerciseDay> nutritionixExercises = new HashSet<NutritionixExerciseDay>();
		for(String nutritionixExerciseQuery : dayDTO.getNutritionixExercises()) {
			NutritionixExerciseDay nutritionixExerciseDay = new NutritionixExerciseDay();
			nutritionixExerciseDay.setExerciseQuery(nutritionixExerciseQuery);
			nutritionixExerciseDay.setDay(day); 
			nutritionixExercises.add(nutritionixExerciseDay);
		}
		day.setDate(dayDTO.getDate());
		day.setUser(user);
		day.setTotalWaterIntake(dayDTO.getTotalWaterIntake());
		day.setMeals(meals);
		day.setExercises(exercises); 
		day.setNutritionixExercises(nutritionixExercises); 
		
		dayService.save(day);
		for(Meal m: day.getMeals()) {
			mealService.save(m);
			for(FoodMeal fm: m.getFoods()) {
				foodMealService.save(fm);
			}
			for(NutritionixFoodMeal nfm: m.getNutritionixFoods()) {
				nutritionixFoodMealService.save(nfm);
			}
		}
		for(DayExercise e: day.getExercises()) {
			dayExerciseService.save(e);
		}
		for(NutritionixExerciseDay nde: day.getNutritionixExercises()) {
			nutritionixDayExerciseService.save(nde);
		}
		
		return new ResponseEntity<>("day saved", HttpStatus.CREATED);
	}

}
