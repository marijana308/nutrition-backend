package diplomski.nutrition.dto;

import diplomski.nutrition.entity.RecipeNutritionixFood;

public class RecipeNutritionixFoodDTO {

	private Long id;
	private Float quantity;
	private String servingSize;
	private String name;
	
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
	
	public RecipeNutritionixFoodDTO() {
		super();
	}

	public RecipeNutritionixFoodDTO(Long id, Float quantity, String servingSize, String name, Float servingWeight,
			Integer calories, Float carbs, Float sugars, Float totalFat, Float saturatedFat, Float cholesterol,
			Float protein, Float sodium, Float potasium, Float fiber) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.servingSize = servingSize;
		this.name = name;
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
	
	public RecipeNutritionixFoodDTO(RecipeNutritionixFood rnf) {
		this(rnf.getId(), rnf.getQuantity(), rnf.getServingSize(), rnf.getQuery(), rnf.getServingWeight(), rnf.getCalories(), 
				rnf.getCarbs(), rnf.getSugars(), rnf.getTotalFat(), rnf.getSaturatedFat(), rnf.getCholesterol(), rnf.getProtein(),
				rnf.getSodium(), rnf.getPotasium(), rnf.getFiber());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public String getServingSize() {
		return servingSize;
	}

	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}

	public String getName() {
		return name;
	}

	public void setQuery(String name) {
		this.name = name;
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

	public void setName(String name) {
		this.name = name;
	}
	
}
