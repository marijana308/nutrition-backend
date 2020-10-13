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
	
	private Float numberOfServings;
	
	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RecipeFood> foods = new HashSet<RecipeFood>();
	
	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RecipeNutritionixFood> nutritionixFoods = new HashSet<RecipeNutritionixFood>();
	
	private Float servingWeight;
	private Integer calories;
	private Float totalCarbs;
	private Float sugars;
	private Float totalFat;
	private Float saturatedFat;
	private Float cholesterol;
	private Float protein;
	private Float sodium;
	private Float potasium;
	private Float fiber;

	public Recipe() {
		super();
	}

	public Recipe(Long id, User user, String name, String directions, Float numberOfServings, Set<RecipeFood> foods,
			Set<RecipeNutritionixFood> nutritionixFoods) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.directions = directions;
		this.numberOfServings = numberOfServings;
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

	public Float getNumberOfServings() {
		return numberOfServings;
	}

	public void setNumberOfServings(Float numberOfServings) {
		this.numberOfServings = numberOfServings;
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

	public Float getTotalCarbs() {
		return totalCarbs;
	}

	public void setTotalCarbs(Float totalCarbs) {
		this.totalCarbs = totalCarbs;
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
