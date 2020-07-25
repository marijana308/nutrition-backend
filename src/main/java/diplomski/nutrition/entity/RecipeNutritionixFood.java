package diplomski.nutrition.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RecipeNutritionixFood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Recipe recipe;
	
	private Float quantity;
	private String servingSize;
	
	private String query;

	public RecipeNutritionixFood() {
		super();
	}

	public RecipeNutritionixFood(Long id, Recipe recipe, String query) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.query = query;
	}

	public RecipeNutritionixFood(Long id, Recipe recipe, Float quantity, String servingSize, String query) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.quantity = quantity;
		this.servingSize = servingSize;
		this.query = query;
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

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
