package diplomski.nutrition.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diplomski.nutrition.dto.FoodDTO;
import diplomski.nutrition.dto.RecipeDTO;
import diplomski.nutrition.dto.RecipeFoodDTO;
import diplomski.nutrition.entity.Food;
import diplomski.nutrition.entity.Recipe;
import diplomski.nutrition.entity.RecipeFood;
import diplomski.nutrition.entity.RecipeNutritionixFood;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.FoodService;
import diplomski.nutrition.service.impl.RecipeFoodService;
import diplomski.nutrition.service.impl.RecipeNutritionixFoodService;
import diplomski.nutrition.service.impl.RecipeService;

@RestController
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
		return new ResponseEntity<>(recipeDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO){
		Recipe recipe = new Recipe();
		User u = userRepository.findByUsername(recipeDTO.getUsername());
		recipe.setUser(u);
		recipe.setName(recipeDTO.getName());
		recipe.setDirections(recipeDTO.getDirections());
		Set<RecipeFood> recipeFoods = new HashSet<RecipeFood>();
		for(RecipeFoodDTO rfDTO: recipeDTO.getFoods()) {
			RecipeFood rf = new RecipeFood();
			rf.setFood(foodService.findById(rfDTO.getFoodID()));
			rf.setAmount(rfDTO.getAmount());
			rf.setRecipe(recipe);
			recipeFoods.add(rf);
		}
		Set<RecipeNutritionixFood> recipeNutritionixFoods = new HashSet<RecipeNutritionixFood>();
		for(String rnfDTO : recipeDTO.getNutritionixFoods()) {
			RecipeNutritionixFood rnf = new RecipeNutritionixFood();
			rnf.setQuery(rnfDTO);
			rnf.setRecipe(recipe);
			recipeNutritionixFoods.add(rnf);
		}
		recipe.setFoods(recipeFoods);
		recipe.setNutritionixFoods(recipeNutritionixFoods);
		
		recipeService.save(recipe);
		
		for(RecipeFood rf: recipeFoods) {
			recipeFoodService.save(rf);
		}
		for(RecipeNutritionixFood rnf : recipeNutritionixFoods) {
			recipeNutritionixFoodService.save(rnf);
		}
		
		return new ResponseEntity<>(new RecipeDTO(recipe), HttpStatus.CREATED);
	}

}
