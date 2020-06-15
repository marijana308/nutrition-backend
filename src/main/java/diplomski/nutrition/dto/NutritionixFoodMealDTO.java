package diplomski.nutrition.dto;

public class NutritionixFoodMealDTO {
	
	private Float quantity;
	private String servingSize;
	private String foodNameQuery;
	
	public NutritionixFoodMealDTO() {
		super();
	}
	
	public NutritionixFoodMealDTO(Float quantity, String servingSize, String foodNameQuery) {
		super();
		this.quantity = quantity;
		this.servingSize = servingSize;
		this.foodNameQuery = foodNameQuery;
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
	public String getFoodNameQuery() {
		return foodNameQuery;
	}
	public void setFoodNameQuery(String foodNameQuery) {
		this.foodNameQuery = foodNameQuery;
	}

}
