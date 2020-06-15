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
	
	public NutritionixFoodMealDTO toNutritionixFoodMealDTO() {
		return new NutritionixFoodMealDTO(this.quantity, this.servingSize, this.foodNameQuery);
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

}
