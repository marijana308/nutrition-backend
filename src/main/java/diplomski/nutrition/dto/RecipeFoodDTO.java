package diplomski.nutrition.dto;

public class RecipeFoodDTO {

	private Long foodID;
	private Float amount;
	
	public RecipeFoodDTO() {
		super();
	}

	public RecipeFoodDTO(Long foodID, Float amount) {
		super();
		this.foodID = foodID;
		this.amount = amount;
	}

	public Long getFoodID() {
		return foodID;
	}

	public void setFoodID(Long foodID) {
		this.foodID = foodID;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
}
