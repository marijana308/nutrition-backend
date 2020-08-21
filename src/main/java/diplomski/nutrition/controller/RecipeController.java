package diplomski.nutrition.controller;

import java.util.ArrayList;
import java.util.HashSet;
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
import diplomski.nutrition.dto.RecipeDTO;
import diplomski.nutrition.dto.RecipeFoodDTO;
import diplomski.nutrition.dto.RecipeMealDTO;
import diplomski.nutrition.dto.RecipeNutritionixFoodDTO;
import diplomski.nutrition.entity.Food;
import diplomski.nutrition.entity.FoodMeal;
import diplomski.nutrition.entity.Recipe;
import diplomski.nutrition.entity.RecipeFood;
import diplomski.nutrition.entity.RecipeMeal;
import diplomski.nutrition.entity.RecipeNutritionixFood;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.FoodService;
import diplomski.nutrition.service.impl.RecipeFoodService;
import diplomski.nutrition.service.impl.RecipeMealService;
import diplomski.nutrition.service.impl.RecipeNutritionixFoodService;
import diplomski.nutrition.service.impl.RecipeService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/recipes")
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	FoodService foodService;
	
	@Autowired
	RecipeFoodService recipeFoodService;
	
	@Autowired
	RecipeNutritionixFoodService recipeNutritionixFoodService;
	
	@Autowired
	RecipeMealService recipeMealService;
	
	@Autowired
	UserRepository userRepository;
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<RecipeDTO>> getRecipesByUsername(@PathVariable String username){
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Recipe> recipes = recipeService.findRecipesByUsername(username);
		List<RecipeDTO> recipeDTOs = new ArrayList<RecipeDTO>();
		for(Recipe r: recipes) {
			recipeDTOs.add(new RecipeDTO(r));
		}
		return new ResponseEntity<List<RecipeDTO>>(recipeDTOs, HttpStatus.OK);
	}
	

	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/search/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<RecipeDTO>> searchRecipes(@PathVariable String username, @RequestParam String query){
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Recipe> recipes = recipeService.searchRecipes(username, query);
		List<RecipeDTO> recipeDTOs = new ArrayList<RecipeDTO>();
		for(Recipe r: recipes) {
			recipeDTOs.add(new RecipeDTO(r));
		}
		return new ResponseEntity<List<RecipeDTO>>(recipeDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/foods/{recipeid}",method = RequestMethod.GET)
	public ResponseEntity<List<RecipeFoodDTO>> getFoodsByRecipeId(@PathVariable Long recipeid){
		Set<RecipeFood> foods = recipeFoodService.findFoodsByRecipeId(recipeid);
		List<RecipeFoodDTO> foodDTOs = new ArrayList<RecipeFoodDTO>();
		for(RecipeFood rf: foods) {
			foodDTOs.add(new RecipeFoodDTO(rf));
		}
		return new ResponseEntity<List<RecipeFoodDTO>>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/nutritionixFoods/{recipeid}",method = RequestMethod.GET)
	public ResponseEntity<List<RecipeNutritionixFoodDTO>> getNutritionixFoodsByRecipeId(@PathVariable Long recipeid){
		Set<RecipeNutritionixFood> foods = recipeNutritionixFoodService.findFoodsByRecipeId(recipeid);
		List<RecipeNutritionixFoodDTO> foodDTOs = new ArrayList<RecipeNutritionixFoodDTO>();
		for(RecipeNutritionixFood rnf: foods) {
			foodDTOs.add(new RecipeNutritionixFoodDTO(rnf));
		}
		return new ResponseEntity<List<RecipeNutritionixFoodDTO>>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO){
		Recipe recipe = new Recipe();
		User u = userRepository.findByUsername(recipeDTO.getUsername());
		recipe.setUser(u);
		recipe.setName(recipeDTO.getName());
		recipe.setDirections(recipeDTO.getDirections());
		recipe.setNumberOfServings(recipeDTO.getNumberOfServings());
		Set<RecipeFood> recipeFoods = new HashSet<RecipeFood>();
		for(RecipeFoodDTO rfDTO: recipeDTO.getFoods()) {
			RecipeFood rf = new RecipeFood();
			rf.setQuantity(rfDTO.getQuantity());
			rf.setServingSize(rfDTO.getServingSize());
			rf.setFood(foodService.findById(rfDTO.getFoodId()));//error
			rf.setRecipe(recipe);
			rf.setServingWeight(rfDTO.getServingWeight());
			rf.setCalories(rfDTO.getCalories());
			rf.setCarbs(rfDTO.getCarbs());
			rf.setSugars(rfDTO.getSugars());
			rf.setTotalFat(rfDTO.getTotalFat());
			rf.setSaturatedFat(rfDTO.getSaturatedFat());
			rf.setCholesterol(rfDTO.getCholesterol());
			rf.setProtein(rfDTO.getProtein());
			rf.setSodium(rfDTO.getSodium());
			rf.setPotasium(rfDTO.getPotasium());
			rf.setFiber(rfDTO.getFiber());
			
			recipeFoods.add(rf);
		}
		Set<RecipeNutritionixFood> recipeNutritionixFoods = new HashSet<RecipeNutritionixFood>();
		for(RecipeNutritionixFoodDTO rnfDTO : recipeDTO.getNutritionixFoods()) {
			RecipeNutritionixFood rnf = new RecipeNutritionixFood();
			rnf.setQuantity(rnfDTO.getQuantity());
			rnf.setServingSize(rnfDTO.getServingSize());
			rnf.setQuery(rnfDTO.getName());
			rnf.setRecipe(recipe);
			rnf.setServingWeight(rnfDTO.getServingWeight());
			rnf.setCalories(rnfDTO.getCalories());
			rnf.setCarbs(rnfDTO.getCarbs());
			rnf.setSugars(rnfDTO.getSugars());
			rnf.setTotalFat(rnfDTO.getTotalFat());
			rnf.setSaturatedFat(rnfDTO.getSaturatedFat());
			rnf.setCholesterol(rnfDTO.getCholesterol());
			rnf.setProtein(rnfDTO.getProtein());
			rnf.setSodium(rnfDTO.getSodium());
			rnf.setPotasium(rnfDTO.getPotasium());
			rnf.setFiber(rnfDTO.getFiber());
			recipeNutritionixFoods.add(rnf);
		}
		recipe.setFoods(recipeFoods);
		recipe.setNutritionixFoods(recipeNutritionixFoods);
		
		recipe.setServingWeight(recipeDTO.getServingWeight());
		recipe.setCalories(recipeDTO.getCalories());
		recipe.setSugars(recipeDTO.getSugars());
		recipe.setCarbs(recipeDTO.getCarbs());
		recipe.setTotalFat(recipeDTO.getTotalFat());
		recipe.setSaturatedFat(recipeDTO.getSaturatedFat());
		recipe.setCholesterol(recipeDTO.getCholesterol());
		recipe.setProtein(recipeDTO.getProtein());
		recipe.setSodium(recipeDTO.getSodium());
		recipe.setPotasium(recipeDTO.getPotasium());
		recipe.setFiber(recipeDTO.getFiber());
		
		recipeService.save(recipe);
		
		for(RecipeFood rf: recipeFoods) {
			recipeFoodService.save(rf);
		}
		for(RecipeNutritionixFood rnf : recipeNutritionixFoods) {
			recipeNutritionixFoodService.save(rnf);
		}
		
		return new ResponseEntity<>(new RecipeDTO(recipe), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RecipeDTO> update(@RequestBody RecipeDTO recipeDTO){
		Recipe recipe = recipeService.findById(recipeDTO.getId());
		if(recipe == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		recipe.setName(recipeDTO.getName());
		recipe.setDirections(recipeDTO.getDirections());
		recipe.setNumberOfServings(recipeDTO.getNumberOfServings());
		//Set<RecipeFood> recipeFoods = new HashSet<RecipeFood>();
		for(RecipeFoodDTO rfDTO: recipeDTO.getFoods()) {
			if(rfDTO.getId() == null || rfDTO.getId().equals("")) {
				RecipeFood rf = new RecipeFood();
				rf.setQuantity(rfDTO.getQuantity());
				rf.setServingSize(rfDTO.getServingSize());
				rf.setFood(foodService.findById(rfDTO.getFoodId()));//error
				rf.setRecipe(recipe);
				rf.setServingWeight(rfDTO.getServingWeight());
				rf.setCalories(rfDTO.getCalories());
				rf.setCarbs(rfDTO.getCarbs());
				rf.setSugars(rfDTO.getSugars());
				rf.setTotalFat(rfDTO.getTotalFat());
				rf.setSaturatedFat(rfDTO.getSaturatedFat());
				rf.setCholesterol(rfDTO.getCholesterol());
				rf.setProtein(rfDTO.getProtein());
				rf.setSodium(rfDTO.getSodium());
				rf.setPotasium(rfDTO.getPotasium());
				rf.setFiber(rfDTO.getFiber());
				recipeFoodService.save(rf);
//				recipeFoods.add(rf);
			}
		}
		//Set<RecipeNutritionixFood> recipeNutritionixFoods = new HashSet<RecipeNutritionixFood>();
		for(RecipeNutritionixFoodDTO rnfDTO : recipeDTO.getNutritionixFoods()) {
			if(rnfDTO.getId() == null || rnfDTO.getId().equals("")) {
				RecipeNutritionixFood rnf = new RecipeNutritionixFood();
				rnf.setQuantity(rnfDTO.getQuantity());
				rnf.setServingSize(rnfDTO.getServingSize());
				rnf.setQuery(rnfDTO.getName());
				rnf.setRecipe(recipe);
				rnf.setServingWeight(rnfDTO.getServingWeight());
				rnf.setCalories(rnfDTO.getCalories());
				rnf.setCarbs(rnfDTO.getCarbs());
				rnf.setSugars(rnfDTO.getSugars());
				rnf.setTotalFat(rnfDTO.getTotalFat());
				rnf.setSaturatedFat(rnfDTO.getSaturatedFat());
				rnf.setCholesterol(rnfDTO.getCholesterol());
				rnf.setProtein(rnfDTO.getProtein());
				rnf.setSodium(rnfDTO.getSodium());
				rnf.setPotasium(rnfDTO.getPotasium());
				rnf.setFiber(rnfDTO.getFiber());
				recipeNutritionixFoodService.save(rnf);
				//recipeNutritionixFoods.add(rnf);
			}
		}
//		recipe.setFoods(recipeFoods);
//		recipe.setNutritionixFoods(recipeNutritionixFoods);
		
		recipe.setServingWeight(recipeDTO.getServingWeight());
		recipe.setCalories(recipeDTO.getCalories());
		recipe.setSugars(recipeDTO.getSugars());
		recipe.setCarbs(recipeDTO.getCarbs());
		recipe.setTotalFat(recipeDTO.getTotalFat());
		recipe.setSaturatedFat(recipeDTO.getSaturatedFat());
		recipe.setCholesterol(recipeDTO.getCholesterol());
		recipe.setProtein(recipeDTO.getProtein());
		recipe.setSodium(recipeDTO.getSodium());
		recipe.setPotasium(recipeDTO.getPotasium());
		recipe.setFiber(recipeDTO.getFiber());
		
		recipeService.save(recipe);
		
//		for(RecipeFood rf: recipeFoods) {
//			recipeFoodService.save(rf);
//		}
//		for(RecipeNutritionixFood rnf : recipeNutritionixFoods) {
//			recipeNutritionixFoodService.save(rnf);
//		}
		
		return new ResponseEntity<>(new RecipeDTO(recipe), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/meal/{mealid}",method = RequestMethod.GET)
	public ResponseEntity<List<RecipeMealDTO>> getRecipesByMealId(@PathVariable Long mealid){
		Set<RecipeMeal> recipes = recipeMealService.findRecipesByMealId(mealid);
		List<RecipeMealDTO> recipeDTOs = new ArrayList<RecipeMealDTO>();
		for(RecipeMeal rm: recipes) {
			recipeDTOs.add(new RecipeMealDTO(rm));
		}
		return new ResponseEntity<List<RecipeMealDTO>>(recipeDTOs, HttpStatus.OK);
	}
	
//	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
//	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
//	public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipeDTO recipeDTO){
//		Recipe recipe = recipeService.findById(recipeDTO.getId());
//		if(recipe == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		recipe.setName(recipeDTO.getName());
//		recipe.setDirections(recipeDTO.getDirections());
//		Set<RecipeFood> recipeFoods = new HashSet<RecipeFood>();
//		for(RecipeFoodDTO rfDTO: recipeDTO.getFoods()) {
//			if(rfDTO.getId() == null) {
//				RecipeFood newrf = new RecipeFood();
//				newrf.setFood(foodService.findById(rfDTO.getFoodId()));
//				newrf.setQuantity(rfDTO.getQuantity());
//				newrf.setRecipe(recipe);
//				recipeFoods.add(newrf);
//			}else {
//				RecipeFood rf = recipeFoodService.findById(rfDTO.getId());
//				rf.setFood(foodService.findById(rfDTO.getFoodId()));
//				rf.setQuantity(rfDTO.getQuantity());
//				recipeFoods.add(rf);
//			}
//		}
//		Set<RecipeNutritionixFood> recipeNutritionixFoods = new HashSet<RecipeNutritionixFood>();
//		for(RecipeNutritionixFoodDTO rnfDTO : recipeDTO.getNutritionixFoods()) {
//			if(rnfDTO.getId() == null) {
//				RecipeNutritionixFood newrnf = new RecipeNutritionixFood();
//				newrnf.setQuantity(rnfDTO.getQuantity());
//				newrnf.setServingSize(rnfDTO.getServingSize());
//				newrnf.setQuery(rnfDTO.getName());
//				newrnf.setRecipe(recipe);
//				recipeNutritionixFoods.add(newrnf);
//			}else {
//				RecipeNutritionixFood rnf = recipeNutritionixFoodService.findById(rnfDTO.getId());
//				rnf.setQuantity(rnfDTO.getQuantity());
//				rnf.setServingSize(rnfDTO.getServingSize());
//				rnf.setQuery(rnfDTO.getName());
//				recipeNutritionixFoods.add(rnf);
//			}
//		}
//		
////		recipe.setFoods(recipeFoods);
////		recipe.setNutritionixFoods(recipeNutritionixFoods); 
//		
//		recipeService.save(recipe);
//		
//		for(RecipeFood rf: recipeFoods) {
//			recipeFoodService.save(rf);
//		}
//		for(RecipeNutritionixFood rnf : recipeNutritionixFoods) {
//			recipeNutritionixFoodService.save(rnf);
//		}
//		
//		return new ResponseEntity<>(new RecipeDTO(recipe), HttpStatus.CREATED);
//	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRecipeById(@PathVariable Long id){
		Recipe recipe = recipeService.findById(id);
		if(recipe == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		for(RecipeFood rf: recipe.getFoods()) {
			recipeFoodService.deleteById(rf.getId());
		}
		for(RecipeNutritionixFood rnf : recipe.getNutritionixFoods()) {
			recipeNutritionixFoodService.deleteById(rnf.getId());
		}
		recipeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/deleteFood/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteFoodInRecipeById(@PathVariable Long id){
		RecipeFood recipeFood = recipeFoodService.findById(id);
		if(recipeFood == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		recipeFoodService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/deleteNutritionixFood/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteNutritionixFoodInRecipeById(@PathVariable Long id){
		RecipeNutritionixFood rnf = recipeNutritionixFoodService.findById(id);
		//System.out.println("deleteNutritionixFoodInRecipeById");
		if(rnf == null) {
			//System.out.println("deleteNutritionixFoodInRecipeById, rnf is null");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		//System.out.println("deleteNutritionixFoodInRecipeById, query = " + rnf.getQuery());
		recipeNutritionixFoodService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
