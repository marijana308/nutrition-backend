package diplomski.nutrition.dto;

import java.util.Set;

import diplomski.nutrition.entity.Recipe;

public class RecipeDTO {

	private Long id;
	private String username;
	private String name;
	private String directions;
	private Set<RecipeFoodDTO> foods;
	private Set<RecipeNutritionixFoodDTO> nutritionixFoods;//queries for nutritionix API
	
	public RecipeDTO() {
		super();
	}

	public RecipeDTO(String username, String name, String directions, Set<RecipeFoodDTO> foods,
			Set<RecipeNutritionixFoodDTO> nutritionixFoods) {
		super();
		this.username = username;
		this.name = name;
		this.directions = directions;
		this.foods = foods;
		this.nutritionixFoods = nutritionixFoods;
	}
	
	public RecipeDTO(Long id, String username, String name, String directions) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.directions = directions;
	}
	
	public RecipeDTO(Recipe recipe) {
		this(recipe.getId(), recipe.getUser().getUsername(), recipe.getName(), recipe.getDirections());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public Set<RecipeNutritionixFoodDTO> getNutritionixFoods() {
		return nutritionixFoods;
	}
	public void setNutritionixFoods(Set<RecipeNutritionixFoodDTO> nutritionixFoods) {
		this.nutritionixFoods = nutritionixFoods;
	}
	
}
