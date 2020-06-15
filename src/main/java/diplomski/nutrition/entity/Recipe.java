package diplomski.nutrition.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;
	
	private String name;
	
	private String directions;
	
	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RecipeFood> foods = new HashSet<RecipeFood>();
	
	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RecipeNutritionixFood> nutritionixFoods = new HashSet<RecipeNutritionixFood>();

	public Recipe() {
		super();
	}

	public Recipe(Long id, User user, String name, String directions, Set<RecipeFood> foods,
			Set<RecipeNutritionixFood> nutritionixFoods) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.directions = directions;
		this.foods = foods;
		this.nutritionixFoods = nutritionixFoods;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Set<RecipeFood> getFoods() {
		return foods;
	}

	public void setFoods(Set<RecipeFood> foods) {
		this.foods = foods;
	}

	public Set<RecipeNutritionixFood> getNutritionixFoods() {
		return nutritionixFoods;
	}

	public void setNutritionixFoods(Set<RecipeNutritionixFood> nutritionixFoods) {
		this.nutritionixFoods = nutritionixFoods;
	}
	
}
