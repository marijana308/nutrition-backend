package diplomski.nutrition.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import diplomski.nutrition.dto.NutritionixFoodMealDTO;

@Entity
public class NutritionixFoodMeal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Float quantity;
	
	private String servingSize;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Meal meal;
	
	//query for the nutritionix API that is sent in body 
	//https://trackapi.nutritionix.com/v2/natural/nutrients
	private String foodNameQuery;
	
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

	public NutritionixFoodMeal() {
		super();
	}

	public NutritionixFoodMeal(Long id, Float quantity, String servingSize, Meal meal, String foodNameQuery) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.servingSize = servingSize;
		this.meal = meal;
		this.foodNameQuery = foodNameQuery;
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

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public String getFoodNameQuery() {
		return foodNameQuery;
	}

	public void setFoodNameQuery(String foodNameQuery) {
		this.foodNameQuery = foodNameQuery;
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
