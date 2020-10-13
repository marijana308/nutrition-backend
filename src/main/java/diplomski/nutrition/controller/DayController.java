package diplomski.nutrition.controller;

import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import diplomski.nutrition.dto.DayDTO;
import diplomski.nutrition.dto.DayExerciseDTO;
import diplomski.nutrition.dto.DayNutritionixExerciseDTO;
import diplomski.nutrition.dto.FoodMealDTO;
import diplomski.nutrition.dto.MealDTO;
import diplomski.nutrition.dto.NutritionixFoodMealDTO;
import diplomski.nutrition.dto.RecipeMealDTO;
import diplomski.nutrition.entity.Day;
import diplomski.nutrition.entity.DayExercise;
import diplomski.nutrition.entity.FoodMeal;
import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.entity.NutritionixExerciseDay;
import diplomski.nutrition.entity.NutritionixFoodMeal;
import diplomski.nutrition.entity.RecipeMeal;
import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.enumeration.MealType;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.DayExerciseService;
import diplomski.nutrition.service.impl.DayService;
import diplomski.nutrition.service.impl.ExerciseService;
import diplomski.nutrition.service.impl.FoodMealService;
import diplomski.nutrition.service.impl.FoodService;
import diplomski.nutrition.service.impl.MealService;
import diplomski.nutrition.service.impl.NutritionixDayExerciseService;
import diplomski.nutrition.service.impl.NutritionixFoodMealService;
import diplomski.nutrition.service.impl.RecipeMealService;
import diplomski.nutrition.service.impl.RecipeService;
import diplomski.nutrition.service.impl.RegularUserService;

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
	private RecipeMealService recipeMealService;
	
	@Autowired
	private MealService mealService;
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private NutritionixFoodMealService nutritionixFoodMealService;
	
	@Autowired
	private DayExerciseService dayExerciseService;
	
	@Autowired
	private NutritionixDayExerciseService nutritionixDayExerciseService;
	
	@Autowired
	private RegularUserService regularUserService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/date",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<DayDTO> getDayByDateAndUsername(@RequestBody DayDTO dayDTO){
		Day day = dayService.findDayByDateAndUsername(dayDTO.getDate(), dayDTO.getUsername());
		if(day == null) {
			return new ResponseEntity<DayDTO>(new DayDTO(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DayDTO>(new DayDTO(day), HttpStatus.OK);
	}
	
	private Date pastDate(Integer number) {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, number);
	    return cal.getTime();
	}
	
	private Day createNewDay(Date date, String username) {
		Day day = new Day();
		User user = userRepository.findByUsername(username);
		day.setUser(user);
		day.setDate(date);
		dayService.save(day);
		Meal breakfast = new Meal();
		breakfast.setMealType(MealType.BREAKFAST);
		breakfast.setDay(day);
		mealService.save(breakfast);
		Meal lunch = new Meal();
		lunch.setMealType(MealType.LUNCH);
		lunch.setDay(day);
		mealService.save(lunch);
		Meal dinner = new Meal();
		dinner.setMealType(MealType.DINNER);
		dinner.setDay(day);
		mealService.save(dinner);
		Meal snack = new Meal();
		snack.setMealType(MealType.SNACK);
		snack.setDay(day);
		mealService.save(snack);
		
		Day today = dayService.findDayByDateAndUsername(new Date(), username);
		Day yesterday = dayService.findDayByDateAndUsername(pastDate(-1), username);
		RegularUser regularUser = regularUserService.findById(user.getId());
		
		if (yesterday == null) {
			regularUser.setStreak(0);
		}
		if(yesterday != null && today != null) {
			regularUser.setStreak(regularUser.getStreak() + 1);
		}
		
		if(regularUser.getStreak() % 7 == 0 && regularUser.getStreak() != 0) {
			regularUser.setPoints(regularUser.getPoints() + 10);
		}
		
		regularUserService.update(regularUser);
		
		return day;
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/addFood",method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<FoodMealDTO> addFoodToDay(@RequestBody DayDTO dayDTO){
		Day day = dayService.findDayByDateAndUsername(dayDTO.getDate(), dayDTO.getUsername());
		if(day == null) {
			day = createNewDay(dayDTO.getDate(), dayDTO.getUsername());
		}
		FoodMeal foodMeal= new FoodMeal();
		for(MealDTO mealDTO : dayDTO.getMeals()) {
			Meal meal = mealService.findMealByDayIdAndMealType(day.getId(), mealDTO.getMealType());
			Set<FoodMeal> foods = foodMealService.findFoodsByMealId(mealDTO.getId());
			for(FoodMealDTO foodMealDTO: mealDTO.getFoods()) {
				if(foodMealDTO.getId() == null) {
					foodMeal.setQuantity(foodMealDTO.getQuantity());
					foodMeal.setServingSize(foodMealDTO.getServingSize());
					foodMeal.setFood(foodService.findById(foodMealDTO.getFoodId()));
					foodMeal.setMeal(meal); 
					foodMeal.setServingWeight(foodMealDTO.getServingWeight());
					foodMeal.setCalories(foodMealDTO.getCalories());
					foodMeal.setCarbs(foodMealDTO.getCarbs());
					foodMeal.setSugars(foodMealDTO.getSugars());
					foodMeal.setTotalFat(foodMealDTO.getTotalFat());
					foodMeal.setSaturatedFat(foodMealDTO.getSaturatedFat());
					foodMeal.setCholesterol(foodMealDTO.getCholesterol());
					foodMeal.setProtein(foodMealDTO.getProtein());
					foodMeal.setSodium(foodMealDTO.getSodium());
					foodMeal.setPotasium(foodMealDTO.getPotasium());
					foodMeal.setFiber(foodMealDTO.getFiber());
					foods.add(foodMeal);
					foodMealService.save(foodMeal);
				}
			}
		}
		return new ResponseEntity<FoodMealDTO>(new FoodMealDTO(foodMeal), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/addNutritionixFood",method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<NutritionixFoodMealDTO> addNutritionixFoodToDay(@RequestBody DayDTO dayDTO){
		Day day = dayService.findDayByDateAndUsername(dayDTO.getDate(), dayDTO.getUsername());
		if(day == null) {
			day = createNewDay(dayDTO.getDate(), dayDTO.getUsername());
		}
		NutritionixFoodMeal nutritionixFoodMeal = new NutritionixFoodMeal();
		for(MealDTO mealDTO : dayDTO.getMeals()) {
			Meal meal = mealService.findMealByDayIdAndMealType(day.getId(), mealDTO.getMealType());
			Set<NutritionixFoodMeal> nutritionixFoods = nutritionixFoodMealService.findFoodsByMealId(mealDTO.getId());
			for(NutritionixFoodMealDTO nutritionixFoodMealDTO : mealDTO.getNutritionixFoods()) {
				if(nutritionixFoodMealDTO.getId() == null) {
					nutritionixFoodMeal.setQuantity(nutritionixFoodMealDTO.getQuantity());
					nutritionixFoodMeal.setServingSize(nutritionixFoodMealDTO.getServingSize());
					nutritionixFoodMeal.setFoodNameQuery(nutritionixFoodMealDTO.getName());
					nutritionixFoodMeal.setMeal(meal); 
					nutritionixFoodMeal.setServingWeight(nutritionixFoodMealDTO.getServingWeight());
					nutritionixFoodMeal.setCalories(nutritionixFoodMealDTO.getCalories());
					nutritionixFoodMeal.setCarbs(nutritionixFoodMealDTO.getCarbs());
					nutritionixFoodMeal.setSugars(nutritionixFoodMealDTO.getSugars());
					nutritionixFoodMeal.setTotalFat(nutritionixFoodMealDTO.getTotalFat());
					nutritionixFoodMeal.setSaturatedFat(nutritionixFoodMealDTO.getSaturatedFat());
					nutritionixFoodMeal.setCholesterol(nutritionixFoodMealDTO.getCholesterol());
					nutritionixFoodMeal.setProtein(nutritionixFoodMealDTO.getProtein());
					nutritionixFoodMeal.setSodium(nutritionixFoodMealDTO.getSodium());
					nutritionixFoodMeal.setPotasium(nutritionixFoodMealDTO.getPotasium());
					nutritionixFoodMeal.setFiber(nutritionixFoodMealDTO.getFiber());
					nutritionixFoods.add(nutritionixFoodMeal);
					nutritionixFoodMealService.save(nutritionixFoodMeal);
				}
			}
		}
		
		return new ResponseEntity<NutritionixFoodMealDTO>(new NutritionixFoodMealDTO(nutritionixFoodMeal), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/addExercise", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<DayExerciseDTO> addExerciseToDay(@RequestBody DayDTO dayDTO){
		Day day = dayService.findDayByDateAndUsername(dayDTO.getDate(), dayDTO.getUsername());
		if(day == null) {
			day = createNewDay(dayDTO.getDate(), dayDTO.getUsername());
		}
		DayExercise dayExercise = new DayExercise();
		for(DayExerciseDTO exerciseDTO : dayDTO.getExercises()) {
			if(exerciseDTO.getId() == null) {
				dayExercise.setExercise(exerciseService.findById(exerciseDTO.getExerciseId()));
				dayExercise.setTime(exerciseDTO.getTime());
				dayExercise.setDay(day);
				dayExercise.setCaloriesBurned(exerciseDTO.getCaloriesBurned());
				dayExerciseService.save(dayExercise);
			}
		}
		return new ResponseEntity<DayExerciseDTO>(new DayExerciseDTO(dayExercise), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/addNutritionixExercise", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<DayNutritionixExerciseDTO> addNutritionixExerciseToDay(@RequestBody DayDTO dayDTO){
		Day day = dayService.findDayByDateAndUsername(dayDTO.getDate(), dayDTO.getUsername());
		if(day == null) {
			day = createNewDay(dayDTO.getDate(), dayDTO.getUsername());
		}
		NutritionixExerciseDay nutritionixExerciseDay = new NutritionixExerciseDay();
		for(DayNutritionixExerciseDTO nutritionixExerciseDTO : dayDTO.getNutritionixExercises()) {
			if(nutritionixExerciseDTO.getId() == null) {
				nutritionixExerciseDay.setExerciseQuery(nutritionixExerciseDTO.getExerciseQuery());
				nutritionixExerciseDay.setDay(day);
				nutritionixDayExerciseService.save(nutritionixExerciseDay);
			}
		}
		return new ResponseEntity<DayNutritionixExerciseDTO>(new DayNutritionixExerciseDTO(nutritionixExerciseDay), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/addRecipe",method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RecipeMealDTO> addRecipeToDay(@RequestBody DayDTO dayDTO){
		Day day = dayService.findDayByDateAndUsername(dayDTO.getDate(), dayDTO.getUsername());
		if(day == null) {
			day = createNewDay(dayDTO.getDate(), dayDTO.getUsername());
		}
		RecipeMeal recipeMeal = new RecipeMeal();
		for(MealDTO mealDTO : dayDTO.getMeals()) {
			Meal meal = mealService.findMealByDayIdAndMealType(day.getId(), mealDTO.getMealType());
			Set<RecipeMeal> recipes = recipeMealService.findRecipesByMealId(mealDTO.getId());
			for(RecipeMealDTO recipeMealDTO: mealDTO.getRecipes()) {
				if(recipeMealDTO.getId() == null) {
					recipeMeal.setQuantity(recipeMealDTO.getQuantity());
					recipeMeal.setServingSize(recipeMealDTO.getServingSize());
					recipeMeal.setRecipe(recipeService.findById(recipeMealDTO.getRecipeId()));
					recipeMeal.setMeal(meal); 
					recipeMeal.setServingWeight(recipeMealDTO.getServingWeight());
					recipeMeal.setCalories(recipeMealDTO.getCalories());
					recipeMeal.setCarbs(recipeMealDTO.getCarbs());
					recipeMeal.setSugars(recipeMealDTO.getSugars());
					recipeMeal.setTotalFat(recipeMealDTO.getTotalFat());
					recipeMeal.setSaturatedFat(recipeMealDTO.getSaturatedFat());
					recipeMeal.setCholesterol(recipeMealDTO.getCholesterol());
					recipeMeal.setProtein(recipeMealDTO.getProtein());
					recipeMeal.setSodium(recipeMealDTO.getSodium());
					recipeMeal.setPotasium(recipeMealDTO.getPotasium());
					recipeMeal.setFiber(recipeMealDTO.getFiber());
					recipes.add(recipeMeal);
					recipeMealService.save(recipeMeal);
				}
			}
		}
		return new ResponseEntity<RecipeMealDTO>(new RecipeMealDTO(recipeMeal), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/changeWaterIntake", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<DayDTO> changeWaterIntake(@RequestBody DayDTO dayDTO){
		Day day = dayService.findDayByDateAndUsername(dayDTO.getDate(), dayDTO.getUsername());
		if(day == null) {
			day = createNewDay(dayDTO.getDate(), dayDTO.getUsername());
		}
		day.setTotalWaterIntake(dayDTO.getTotalWaterIntake());
		dayService.save(day);
		
		return new ResponseEntity<DayDTO>(new DayDTO(day), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/deleteNutritionixFood/{foodid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteNutritionixFoodFromDay(@PathVariable Long foodid){
		NutritionixFoodMeal nfm = nutritionixFoodMealService.findById(foodid);
		if(nfm == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		nutritionixFoodMealService.deleteById(foodid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/deleteFood/{foodid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteFoodFromDay(@PathVariable Long foodid){
		FoodMeal nfm = foodMealService.findById(foodid);
		if(nfm == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		foodMealService.deleteById(foodid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/deleteExercise/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteExerciseFromDay(@PathVariable Long id){
		DayExercise e = dayExerciseService.findById(id);
		if(e == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		dayExerciseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/deleteNutritionixExercise/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteNutritionixExerciseFromDay(@PathVariable Long id){
		NutritionixExerciseDay e = nutritionixDayExerciseService.findById(id);
		if(e == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		nutritionixDayExerciseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/deleteRecipe/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRecipeFromDay(@PathVariable Long id){
		RecipeMeal rm = recipeMealService.findById(id);
		if(rm == null) {
			System.out.println("Recipe meal is null");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		recipeMealService.deleteById(id);
		System.out.println("Recipe meal is deleted");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
