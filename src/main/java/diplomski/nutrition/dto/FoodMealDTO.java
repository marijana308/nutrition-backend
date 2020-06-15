package diplomski.nutrition.dto;

public class FoodMealDTO {

	private Float quantity;
	private String servingSize;
	private String food;
	
	public FoodMealDTO() {
		super();
	}
	
	public FoodMealDTO(Float quantity, String servingSize, String food) {
		super();
		this.quantity = quantity;
		this.servingSize = servingSize;
		this.food = food;
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
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}

}
