package diplomski.nutrition.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import diplomski.nutrition.dto.FoodMealDTO;

@Entity
public class FoodMeal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Float quantity;
	
	private String servingSize;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Food food;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Meal meal;

	public FoodMeal() {
		super();
	}

	public FoodMeal(Long id, Float quantity, String servingSize, Food food, Meal meal) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.servingSize = servingSize;
		this.food = food;
		this.meal = meal;
	}
	
	public FoodMealDTO toFoodMealDTO() {
		return new FoodMealDTO(this.quantity, this.servingSize, this.food.getName());
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

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}
}
