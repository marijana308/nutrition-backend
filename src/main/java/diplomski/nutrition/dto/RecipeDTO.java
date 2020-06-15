package diplomski.nutrition.dto;

import java.util.Set;

import diplomski.nutrition.entity.Recipe;

public class RecipeDTO {

	private String username;
	private String name;
	private String directions;
	private Set<RecipeFoodDTO> foods;
	private Set<String> nutritionixFoods;//queries for nutritionix API
	
	public RecipeDTO(String username, String name, String directions, Set<RecipeFoodDTO> foods,
			Set<String> nutritionixFoods) {
		super();
		this.username = username;
		this.name = name;
		this.directions = directions;
		this.foods = foods;
		this.nutritionixFoods = nutritionixFoods;
	}
	
	public RecipeDTO(String username, String name, String directions) {
		super();
		this.username = username;
		this.name = name;
		this.directions = directions;
	}
	
	public RecipeDTO(Recipe recipe) {
		this(recipe.getUser().getUsername(), recipe.getName(), recipe.getDirections());
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public Set<RecipeFoodDTO> getFoods() {
		return foods;
	}
	public void setFoods(Set<RecipeFoodDTO> foods) {
		this.foods = foods;
	}
	public Set<String> getNutritionixFoods() {
		return nutritionixFoods;
	}
	public void setNutritionixFoods(Set<String> nutritionixFoods) {
		this.nutritionixFoods = nutritionixFoods;
	}
	
}
