package diplomski.nutrition.dto;

import java.util.Set;

import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.enumeration.MealType;

public class MealDTO {
	
	private Long id;
	private MealType mealType;
	private Set<FoodMealDTO> foods;
	private Set<NutritionixFoodMealDTO> nutritionixFoods;
	private Set<RecipeMealDTO> recipes;
	
	public MealDTO() {
		super();
	}
	
	public MealDTO(MealType mealType, Set<FoodMealDTO> foods, Set<NutritionixFoodMealDTO> nutritionixFoods) {
		super();
		this.mealType = mealType;
		this.foods = foods;
		this.nutritionixFoods = nutritionixFoods;
	}
	
	public MealDTO(Long id, MealType mealType) {
		super();
		this.id = id;
		this.mealType = mealType;
	}
	
	public MealDTO(Meal meal) {
		this(meal.getId(), meal.getMealType());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public Set<FoodMealDTO> getFoods() {
		return foods;
	}
	public void setFoods(Set<FoodMealDTO> foods) {
		this.foods = foods;
	}
	public Set<NutritionixFoodMealDTO> getNutritionixFoods() {
		return nutritionixFoods;
	}
	public void setNutritionixFoods(Set<NutritionixFoodMealDTO> nutritionixFoods) {
		this.nutritionixFoods = nutritionixFoods;
	}

	public Set<RecipeMealDTO> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<RecipeMealDTO> recipes) {
		this.recipes = recipes;
	}
}
