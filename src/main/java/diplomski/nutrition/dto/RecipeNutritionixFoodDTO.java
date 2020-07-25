package diplomski.nutrition.dto;

public class RecipeNutritionixFoodDTO {

	private Long id;
	private Float quantity;
	private String servingSize;
	private String name;
//	private String foodNameQuery;
	
	public RecipeNutritionixFoodDTO() {
		super();
	}

	public RecipeNutritionixFoodDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RecipeNutritionixFoodDTO(Long id, Float quantity, String servingSize, String name) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.servingSize = servingSize;
		this.name = name;
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
	
}
