package diplomski.nutrition.dto;

import java.util.Set;

import diplomski.nutrition.entity.Recipe;

public class RecipeDTO {

	private Long id;
	private String username;
	private String name;
	private String directions;
	private Float numberOfServings;
	private Set<RecipeFoodDTO> foods;
	private Set<RecipeNutritionixFoodDTO> nutritionixFoods;//queries for nutritionix API
	
	private Float servingWeight;
	private Integer calories;
	private Float carbs;
	private Float sugars;
	private Float totalFat;
	private Float saturatedFat;
	private Float cholesterol;
	private Float protein;
	private Float sodium;
	private Float potasium;
	private Float fiber;
	
	public RecipeDTO() {
		super();
	}
	
	public RecipeDTO(Long id, String username, String name, String directions, Float numberOfServings) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.directions = directions;
		this.numberOfServings = numberOfServings;
	}
	
	public RecipeDTO(Long id, String username, String name, String directions, Float numberOfServings, Float servingWeight,
			Integer calories, Float carbs, Float sugars, Float totalFat, Float saturatedFat, Float cholesterol,
			Float protein, Float sodium, Float potasium, Float fiber) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.directions = directions;
		this.numberOfServings = numberOfServings;
		this.servingWeight = servingWeight;
		this.calories = calories;
		this.carbs = carbs;
		this.sugars = sugars;
		this.totalFat = totalFat;
		this.saturatedFat = saturatedFat;
		this.cholesterol = cholesterol;
		this.protein = protein;
		this.sodium = sodium;
		this.potasium = potasium;
		this.fiber = fiber;
	}

	public RecipeDTO(Recipe r) {
		this(r.getId(), r.getUser().getUsername(), r.getName(), r.getDirections(), r.getNumberOfServings(), r.getServingWeight(),
			r.getCalories(), r.getCarbs(), r.getSugars(), r.getTotalFat(), r.getSaturatedFat(), r.getCholesterol(), r.getProtein(),
			r.getSodium(), r.getPotasium(), r.getFiber());
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

	public Float getNumberOfServings() {
		return numberOfServings;
	}

	public void setNumberOfServings(Float numberOfServings) {
		this.numberOfServings = numberOfServings;
	}

	public Float getServingWeight() {
		return servingWeight;
	}

	public void setServingWeight(Float servingWeight) {
		this.servingWeight = servingWeight;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Float getCarbs() {
		return carbs;
	}

	public void setCarbs(Float carbs) {
		this.carbs = carbs;
	}

	public Float getSugars() {
		return sugars;
	}

	public void setSugars(Float sugars) {
		this.sugars = sugars;
	}

	public Float getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(Float totalFat) {
		this.totalFat = totalFat;
	}

	public Float getSaturatedFat() {
		return saturatedFat;
	}

	public void setSaturatedFat(Float saturatedFat) {
		this.saturatedFat = saturatedFat;
	}

	public Float getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Float cholesterol) {
		this.cholesterol = cholesterol;
	}

	public Float getProtein() {
		return protein;
	}

	public void setProtein(Float protein) {
		this.protein = protein;
	}

	public Float getSodium() {
		return sodium;
	}

	public void setSodium(Float sodium) {
		this.sodium = sodium;
	}

	public Float getPotasium() {
		return potasium;
	}

	public void setPotasium(Float potasium) {
		this.potasium = potasium;
	}

	public Float getFiber() {
		return fiber;
	}

	public void setFiber(Float fiber) {
		this.fiber = fiber;
	}
	
}
