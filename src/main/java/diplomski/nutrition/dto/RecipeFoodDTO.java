package diplomski.nutrition.dto;

public class RecipeFoodDTO {

	private Long id;
	private Long foodid;
	private Float quantity;
	
	public RecipeFoodDTO() {
		super();
	}

	public RecipeFoodDTO(Long foodid, Float quantity) {
		super();
		this.foodid = foodid;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFoodID() {
		return foodid;
	}

	public void setFoodID(Long foodId) {
		this.foodid = foodId;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	
}
