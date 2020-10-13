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

import diplomski.nutrition.dto.FoodDTO;
import diplomski.nutrition.dto.FoodMealDTO;
import diplomski.nutrition.dto.NutritionixFoodMealDTO;
import diplomski.nutrition.entity.Food;
import diplomski.nutrition.entity.FoodMeal;
import diplomski.nutrition.entity.NutritionixFoodMeal;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.FoodMealService;
import diplomski.nutrition.service.impl.FoodService;
import diplomski.nutrition.service.impl.MealService;
import diplomski.nutrition.service.impl.NutritionixFoodMealService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/foods")
public class FoodController {

	@Autowired
	FoodService foodService;
	
	@Autowired
	FoodMealService foodMealService;
	
	@Autowired
	NutritionixFoodMealService nutritionixFoodMealService;
	
	@Autowired
	MealService mealService;
	
	@Autowired
	UserRepository userRepository;
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/allAdmin", method = RequestMethod.GET)
	public ResponseEntity<List<FoodDTO>> getAllAdminFoods(){
		List<Food> foods = foodService.findFoodsCreatedByAdmins();
		List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
		for(Food f: foods) {
			foodDTOs.add(new FoodDTO(f));
		}
		return new ResponseEntity<List<FoodDTO>>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/all/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<FoodDTO>> getAllFoodsByUsername(@PathVariable String username){
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Food> foods = foodService.findFoodsByUsername(username);
		List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
		for(Food f: foods) {
			foodDTOs.add(new FoodDTO(f));
			
		}
		return new ResponseEntity<List<FoodDTO>>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<FoodDTO>> searchFoodsByUsername(@PathVariable String username, @RequestParam String query){
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Food> foods = foodService.searchFoodsByUsername(username, query);
		List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
		for(Food f: foods) {
			foodDTOs.add(new FoodDTO(f));
			
		}
		return new ResponseEntity<>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ResponseEntity<List<FoodDTO>> searchFoodsCreatedByAdmins(@RequestParam String query){
		List<Food> foods = foodService.searchFoodsCreatedByAdmins(query);
		List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
		for(Food f: foods) {
			foodDTOs.add(new FoodDTO(f));
			
		}
		return new ResponseEntity<>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<FoodDTO> createFood(@RequestBody FoodDTO foodDTO){
		Food food = new Food();
		User u = userRepository.findByUsername(foodDTO.getUsername());
		food.setUser(u);
		food.setName(foodDTO.getName());
		food.setTotalCalories(foodDTO.getCalories());
		food.setServingSize("100g");
		food.setTotalFat(foodDTO.getTotalFat());
		food.setSaturatedFat(foodDTO.getSaturatedFat());
		food.setCholesterol(foodDTO.getCholesterol());
		food.setSodium(foodDTO.getSodium());
		food.setTotalCarbs(foodDTO.getTotalCarbs());
		food.setFiber(foodDTO.getFiber());
		food.setSugars(foodDTO.getSugars());
		food.setProtein(foodDTO.getProtein());
		food.setPotasium(foodDTO.getPotasium());
		
		foodService.save(food);
		
		return new ResponseEntity<>(new FoodDTO(food), HttpStatus.CREATED);
	}
	

	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<FoodDTO> updateFood(@RequestBody FoodDTO foodDTO){
		Food food = foodService.findById(foodDTO.getId());
		if(food == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		food.setName(foodDTO.getName());
		food.setTotalCalories(foodDTO.getCalories());
		food.setTotalFat(foodDTO.getTotalFat());
		food.setSaturatedFat(foodDTO.getSaturatedFat());
		food.setCholesterol(foodDTO.getCholesterol());
		food.setSodium(foodDTO.getSodium());
		food.setTotalCarbs(foodDTO.getTotalCarbs());
		food.setFiber(foodDTO.getFiber());
		food.setSugars(foodDTO.getSugars());
		food.setProtein(foodDTO.getProtein());
		food.setPotasium(foodDTO.getPotasium());
		
		foodService.save(food);
		
		return new ResponseEntity<>(new FoodDTO(food), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteFoodById(@PathVariable Long id){
		Food food = foodService.findById(id);
		if(food == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		foodService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/meal/{mealid}",method = RequestMethod.GET)
	public ResponseEntity<List<FoodMealDTO>> getFoodsByMealId(@PathVariable Long mealid){
		Set<FoodMeal> foods = foodMealService.findFoodsByMealId(mealid);
		List<FoodMealDTO> foodDTOs = new ArrayList<FoodMealDTO>();
		for(FoodMeal fm: foods) {
			foodDTOs.add(new FoodMealDTO(fm));
		}
		return new ResponseEntity<List<FoodMealDTO>>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/nutritionix/meal/{mealid}",method = RequestMethod.GET)
	public ResponseEntity<List<NutritionixFoodMealDTO>> getNutritionixFoodsByMealId(@PathVariable Long mealid){
		Set<NutritionixFoodMeal> foods = nutritionixFoodMealService.findFoodsByMealId(mealid);
		List<NutritionixFoodMealDTO> foodDTOs = new ArrayList<NutritionixFoodMealDTO>();
		for(NutritionixFoodMeal nfm: foods) {
			foodDTOs.add(new NutritionixFoodMealDTO(nfm));
		}
		return new ResponseEntity<List<NutritionixFoodMealDTO>>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(value = "/deleteNutritionix/{foodid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteNutritionixFoodFromMeal(@PathVariable Long foodid){
		NutritionixFoodMeal food = nutritionixFoodMealService.findById(foodid);
		if(food == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		nutritionixFoodMealService.deleteById(foodid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(value = "/delete/{foodid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteFoodFromMeal(@PathVariable Long foodid){
		FoodMeal food = foodMealService.findById(foodid);
		if(food == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		foodMealService.deleteById(foodid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
