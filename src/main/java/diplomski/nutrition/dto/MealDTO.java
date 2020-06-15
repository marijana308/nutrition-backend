package diplomski.nutrition.dto;

import java.util.Set;

import diplomski.nutrition.enumeration.MealType;

public class MealDTO {
	
	private MealType mealType;
	private Set<FoodMealDTO> foods;
	private Set<NutritionixFoodMealDTO> nutritionixFoods;

	public MealType getMealType() {
		return mealType;
	}
	
	public MealDTO(MealType mealType, Set<FoodMealDTO> foods, Set<NutritionixFoodMealDTO> nutritionixFoods) {
		super();
		this.mealType = mealType;
		this.foods = foods;
		this.nutritionixFoods = nutritionixFoods;
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
}
