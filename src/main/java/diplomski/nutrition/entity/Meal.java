package diplomski.nutrition.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import diplomski.nutrition.dto.FoodMealDTO;
import diplomski.nutrition.dto.MealDTO;
import diplomski.nutrition.dto.NutritionixFoodMealDTO;
import diplomski.nutrition.enumeration.MealType;

@Entity
public class Meal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Day day;
	
	private MealType mealType;
	
	@OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<FoodMeal> foods = new HashSet<FoodMeal>();
	
	@OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<NutritionixFoodMeal> nutritionixFoods = new HashSet<NutritionixFoodMeal>();
	
	public Meal() {
		super();
	}

	public Meal(Long id, Day day, MealType mealType, Set<FoodMeal> foods, Set<NutritionixFoodMeal> nutritionixFoods) {
		super();
		this.id = id;
		this.day = day;
		this.mealType = mealType;
		this.foods = foods;
		this.nutritionixFoods = nutritionixFoods;
	}
	
	public MealDTO toMealDTO() {
		Set<FoodMealDTO> foodMealDTOs = new HashSet<FoodMealDTO>();
		for(FoodMeal foodMeal : this.foods) {
			foodMealDTOs.add(foodMeal.toFoodMealDTO());
		}
		Set<NutritionixFoodMealDTO> nutritionixFoodMealDTOs = new HashSet<NutritionixFoodMealDTO>();
		for(NutritionixFoodMeal nutritionixFoodMeal : this.nutritionixFoods) {
			nutritionixFoodMealDTOs.add(nutritionixFoodMeal.toNutritionixFoodMealDTO());
		}
		return new MealDTO(this.mealType, foodMealDTOs , nutritionixFoodMealDTOs);
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

	public Set<FoodMeal> getFoods() {
		return foods;
	}

	public void setFoods(Set<FoodMeal> foods) {
		this.foods = foods;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Set<NutritionixFoodMeal> getNutritionixFoods() {
		return nutritionixFoods;
	}

	public void setNutritionixFoods(Set<NutritionixFoodMeal> nutritionixFoods) {
		this.nutritionixFoods = nutritionixFoods;
	}
}
