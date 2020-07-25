package diplomski.nutrition.controller;

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
import diplomski.nutrition.entity.Day;
import diplomski.nutrition.entity.DayExercise;
import diplomski.nutrition.entity.FoodMeal;
import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.entity.NutritionixExerciseDay;
import diplomski.nutrition.entity.NutritionixFoodMeal;
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
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value="/date",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<DayDTO> getDayByDateAndUsername(@RequestBody DayDTO dayDTO){
		Day day = dayService.findDayByDateAndUsername(dayDTO.getDate(), dayDTO.getUsername());
		if(day == null) {
			return new ResponseEntity<DayDTO>(new DayDTO(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DayDTO>(new DayDTO(day), HttpStatus.OK);
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
		//Set<DayExercise> exercises = dayExerciseService.findExercisesByDayId(day.getId());
		DayExercise dayExercise = new DayExercise();
		for(DayExerciseDTO exerciseDTO : dayDTO.getExercises()) {
			if(exerciseDTO.getId() == null) {
				dayExercise.setExercise(exerciseService.findById(exerciseDTO.getExerciseId()));
				dayExercise.setTime(exerciseDTO.getTime());
				dayExercise.setDay(day);
				dayExercise.setCaloriesBurned(exerciseDTO.getCaloriesBurned());
				//exercises.add(dayExercise);
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
		//Set<NutritionixExerciseDay> nutritionixExercises = nutritionixDayExerciseService.findExercisesByDayId(day.getId());
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
}

//@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
//@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
//public ResponseEntity<Void> addDay(@RequestBody DayDTO dayDTO){
//	Day day = new Day();
//	User user = userRepository.findByUsername(dayDTO.getUsername());
//	Set<Meal> meals= new HashSet<Meal>();
//	for(MealDTO mealDTO : dayDTO.getMeals()) {
//		Meal meal = new Meal();
//		meal.setMealType(mealDTO.getMealType());
//		Set<FoodMeal> foods = new HashSet<FoodMeal>();
//		for(FoodMealDTO foodMealDTO: mealDTO.getFoods()) {
//			FoodMeal foodMeal= new FoodMeal();
//			foodMeal.setQuantity(foodMealDTO.getQuantity());
//			foodMeal.setServingSize(foodMealDTO.getServingSize());
//			foodMeal.setFood(foodService.findById(foodMealDTO.getFoodId()));
//			foodMeal.setMeal(meal); 
//			foodMeal.setServingWeight(foodMealDTO.getServingWeight());
//			foodMeal.setCalories(foodMealDTO.getCalories());
//			foodMeal.setCarbs(foodMealDTO.getCarbs());
//			foodMeal.setSugars(foodMealDTO.getSugars());
//			foodMeal.setTotalFat(foodMealDTO.getTotalFat());
//			foodMeal.setSaturatedFat(foodMealDTO.getSaturatedFat());
//			foodMeal.setCholesterol(foodMealDTO.getCholesterol());
//			foodMeal.setProtein(foodMealDTO.getProtein());
//			foodMeal.setSodium(foodMealDTO.getSodium());
//			foodMeal.setPotasium(foodMealDTO.getPotasium());
//			foodMeal.setFiber(foodMealDTO.getFiber());
//			foods.add(foodMeal);
//		}
//		meal.setFoods(foods); 
//		Set<NutritionixFoodMeal> nutritionixFoods = new HashSet<NutritionixFoodMeal>();
//		for(NutritionixFoodMealDTO nutritionixFoodMealDTO : mealDTO.getNutritionixFoods()) {
//			NutritionixFoodMeal nutritionixFoodMeal = new NutritionixFoodMeal();
//			nutritionixFoodMeal.setQuantity(nutritionixFoodMealDTO.getQuantity());
//			nutritionixFoodMeal.setServingSize(nutritionixFoodMealDTO.getServingSize());
//			nutritionixFoodMeal.setFoodNameQuery(nutritionixFoodMealDTO.getName());
//			nutritionixFoodMeal.setMeal(meal); 
//			nutritionixFoodMeal.setServingWeight(nutritionixFoodMealDTO.getServingWeight());
//			nutritionixFoodMeal.setCalories(nutritionixFoodMealDTO.getCalories());
//			nutritionixFoodMeal.setCarbs(nutritionixFoodMealDTO.getCarbs());
//			nutritionixFoodMeal.setSugars(nutritionixFoodMealDTO.getSugars());
//			nutritionixFoodMeal.setTotalFat(nutritionixFoodMealDTO.getTotalFat());
//			nutritionixFoodMeal.setSaturatedFat(nutritionixFoodMealDTO.getSaturatedFat());
//			nutritionixFoodMeal.setCholesterol(nutritionixFoodMealDTO.getCholesterol());
//			nutritionixFoodMeal.setProtein(nutritionixFoodMealDTO.getProtein());
//			nutritionixFoodMeal.setSodium(nutritionixFoodMealDTO.getSodium());
//			nutritionixFoodMeal.setPotasium(nutritionixFoodMealDTO.getPotasium());
//			nutritionixFoodMeal.setFiber(nutritionixFoodMealDTO.getFiber());
//			nutritionixFoods.add(nutritionixFoodMeal);
//		}
//		meal.setNutritionixFoods(nutritionixFoods); 
//		meal.setDay(day); 
//		meals.add(meal);
//	}
//	Set<DayExercise> exercises = new HashSet<DayExercise>();
//	for(DayExerciseDTO exerciseDTO : dayDTO.getExercises()) {
//		DayExercise dayExercise = new DayExercise();
//		dayExercise.setExercise(exerciseService.findById(exerciseDTO.getExerciseId()));
//		dayExercise.setTime(exerciseDTO.getTime());
//		dayExercise.setDay(day);
//		exercises.add(dayExercise);
//	}
//	Set<NutritionixExerciseDay> nutritionixExercises = new HashSet<NutritionixExerciseDay>();
//	for(String nutritionixExerciseQuery : dayDTO.getNutritionixExercises()) {
//		NutritionixExerciseDay nutritionixExerciseDay = new NutritionixExerciseDay();
//		nutritionixExerciseDay.setExerciseQuery(nutritionixExerciseQuery);
//		nutritionixExerciseDay.setDay(day); 
//		nutritionixExercises.add(nutritionixExerciseDay);
//	}
//	day.setDate(dayDTO.getDate());
//	day.setUser(user);
//	day.setTotalWaterIntake(dayDTO.getTotalWaterIntake());
//	day.setMeals(meals);
//	day.setExercises(exercises); 
//	day.setNutritionixExercises(nutritionixExercises); 
//	
//	dayService.save(day);
//	for(Meal m: day.getMeals()) {
//		mealService.save(m);
//		for(FoodMeal fm: m.getFoods()) {
//			foodMealService.save(fm);
//		}
//		for(NutritionixFoodMeal nfm: m.getNutritionixFoods()) {
//			nutritionixFoodMealService.save(nfm);
//		}
//	}
//	for(DayExercise e: day.getExercises()) {
//		dayExerciseService.save(e);
//	}
//	for(NutritionixExerciseDay nde: day.getNutritionixExercises()) {
//		nutritionixDayExerciseService.save(nde);
//	}
//	
//	return new ResponseEntity<>(HttpStatus.CREATED);
//}

//@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
//@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
//public ResponseEntity<DayDTO> updateDay(@RequestBody DayDTO dayDTO){
//	Day day = dayService.findDayByDateAndUsername(dayDTO.getDate(), dayDTO.getUsername());
//	if(day == null) {
//		return new ResponseEntity<DayDTO>(new DayDTO(), HttpStatus.NOT_FOUND);
//	}
//	day.setTotalWaterIntake(dayDTO.getTotalWaterIntake());
//	dayService.save(day);
//	//List<Meal> meals = mealService.findMealsByDayId(day.getId());
//	for(MealDTO mealDTO : dayDTO.getMeals()) {
//		Meal meal = mealService.findMealById(mealDTO.getId());
//		Set<FoodMeal> foods = foodMealService.findFoodsByMealId(mealDTO.getId());
//		for(FoodMealDTO foodMealDTO: mealDTO.getFoods()) {
//			if(foodMealDTO.getId() == null) {
//				FoodMeal foodMeal= new FoodMeal();
//				foodMeal.setQuantity(foodMealDTO.getQuantity());
//				foodMeal.setServingSize(foodMealDTO.getServingSize());
//				foodMeal.setFood(foodService.findById(foodMealDTO.getFoodId()));
////				foodMeal.setMeal(meal); 
//				foodMeal.setServingWeight(foodMealDTO.getServingWeight());
//				foodMeal.setCalories(foodMealDTO.getCalories());
//				foodMeal.setCarbs(foodMealDTO.getCarbs());
//				foodMeal.setSugars(foodMealDTO.getSugars());
//				foodMeal.setTotalFat(foodMealDTO.getTotalFat());
//				foodMeal.setSaturatedFat(foodMealDTO.getSaturatedFat());
//				foodMeal.setCholesterol(foodMealDTO.getCholesterol());
//				foodMeal.setProtein(foodMealDTO.getProtein());
//				foodMeal.setSodium(foodMealDTO.getSodium());
//				foodMeal.setPotasium(foodMealDTO.getPotasium());
//				foodMeal.setFiber(foodMealDTO.getFiber());
//				foods.add(foodMeal);
//				foodMealService.save(foodMeal);
//			}
//		}
//		meal.setFoods(foods); 
//		Set<NutritionixFoodMeal> nutritionixFoods = nutritionixFoodMealService.findFoodsByMealId(mealDTO.getId());
//		for(NutritionixFoodMealDTO nutritionixFoodMealDTO : mealDTO.getNutritionixFoods()) {
//			if(nutritionixFoodMealDTO.getId() == null) {
//				NutritionixFoodMeal nutritionixFoodMeal = new NutritionixFoodMeal();
//				nutritionixFoodMeal.setQuantity(nutritionixFoodMealDTO.getQuantity());
//				nutritionixFoodMeal.setServingSize(nutritionixFoodMealDTO.getServingSize());
//				nutritionixFoodMeal.setFoodNameQuery(nutritionixFoodMealDTO.getName());
//				nutritionixFoodMeal.setMeal(meal); 
//				nutritionixFoodMeal.setServingWeight(nutritionixFoodMealDTO.getServingWeight());
//				nutritionixFoodMeal.setCalories(nutritionixFoodMealDTO.getCalories());
//				nutritionixFoodMeal.setCarbs(nutritionixFoodMealDTO.getCarbs());
//				nutritionixFoodMeal.setSugars(nutritionixFoodMealDTO.getSugars());
//				nutritionixFoodMeal.setTotalFat(nutritionixFoodMealDTO.getTotalFat());
//				nutritionixFoodMeal.setSaturatedFat(nutritionixFoodMealDTO.getSaturatedFat());
//				nutritionixFoodMeal.setCholesterol(nutritionixFoodMealDTO.getCholesterol());
//				nutritionixFoodMeal.setProtein(nutritionixFoodMealDTO.getProtein());
//				nutritionixFoodMeal.setSodium(nutritionixFoodMealDTO.getSodium());
//				nutritionixFoodMeal.setPotasium(nutritionixFoodMealDTO.getPotasium());
//				nutritionixFoodMeal.setFiber(nutritionixFoodMealDTO.getFiber());
//				nutritionixFoods.add(nutritionixFoodMeal);
//				nutritionixFoodMealService.save(nutritionixFoodMeal);
//			}
//		}
//		meal.setNutritionixFoods(nutritionixFoods); 
//		meal.setDay(day); 
//		//meals.add(meal);
//	}
//	Set<DayExercise> exercises = dayExerciseService.findExercisesByDayId(day.getId());
//	for(DayExerciseDTO exerciseDTO : dayDTO.getExercises()) {
//		if(exerciseDTO.getId() == null) {
//			DayExercise dayExercise = new DayExercise();
//			dayExercise.setExercise(exerciseService.findById(exerciseDTO.getExerciseId()));
//			dayExercise.setTime(exerciseDTO.getTime());
//			dayExercise.setDay(day);
//			exercises.add(dayExercise);
//			dayExerciseService.save(dayExercise);
//		}
//	}
//	Set<NutritionixExerciseDay> nutritionixExercises = nutritionixDayExerciseService.findExercisesByDayId(day.getId());
//	for(String nutritionixExerciseQuery : dayDTO.getNutritionixExercises()) {
//		NutritionixExerciseDay nutritionixExerciseDay = new NutritionixExerciseDay();
//		nutritionixExerciseDay.setExerciseQuery(nutritionixExerciseQuery);
//		nutritionixExerciseDay.setDay(day); 
//		nutritionixExercises.add(nutritionixExerciseDay);//send only new exercises from the frontend!!!!!
//		nutritionixDayExerciseService.save(nutritionixExerciseDay);
//	}
//	
//	return new ResponseEntity<DayDTO>(new DayDTO(), HttpStatus.OK);
//}

//@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
//@RequestMapping(value="/create",method = RequestMethod.POST, consumes = "application/json")
//public ResponseEntity<DayDTO> addNewDay(@RequestBody DayDTO dayDTO){
//	Day day = new Day();
//	User user = userRepository.findByUsername(dayDTO.getUsername());
//	day.setUser(user);
//	day.setDate(dayDTO.getDate());
//	dayService.save(day);
//	Meal breakfast = new Meal();
//	breakfast.setMealType(MealType.BREAKFAST);
//	breakfast.setDay(day);
//	mealService.save(breakfast);
//	Meal lunch = new Meal();
//	lunch.setMealType(MealType.LUNCH);
//	lunch.setDay(day);
//	mealService.save(lunch);
//	Meal dinner = new Meal();
//	dinner.setMealType(MealType.DINNER);
//	dinner.setDay(day);
//	mealService.save(dinner);
//	Meal snack = new Meal();
//	snack.setMealType(MealType.SNACK);
//	snack.setDay(day);
//	mealService.save(snack);
//	
//	return new ResponseEntity<DayDTO>(new DayDTO(day),HttpStatus.CREATED);
//}

//System.out.println("addDay called, dayDTO = " + dayDTO.toString());
//System.out.println("addDay called, dayDTO username = " + dayDTO.getUsername());
//System.out.println("addDay called, dayDTO water intake = " + dayDTO.getTotalWaterIntake());
//System.out.println("addDay called, dayDTO date = " + dayDTO.getDate());
//System.out.println("addDay called, dayDTO exercises = " + dayDTO.getNutritionixExercises());
//for(MealDTO mD: dayDTO.getMeals()) {
//	System.out.println("addDay called, dayDTO mealtype = " + mD.getMealType());
//	for(NutritionixFoodMealDTO nfmd: mD.getNutritionixFoods()) {
//		System.out.println("addDay called, dayDTO nutritionix food name = " + nfmd.getName());
//		System.out.println("addDay called, dayDTO nutritionix quantity = " + nfmd.getQuantity());
//		System.out.println("addDay called, dayDTO nutritionix servingSize = " + nfmd.getServingSize());
//	}
//	for(FoodMealDTO fmd: mD.getFoods()) {
//		System.out.println("addDay called, dayDTO food ID = " + fmd.getFoodId());
//		System.out.println("addDay called, dayDTO nutritionix quantity = " + fmd.getQuantity());
//	}
//}