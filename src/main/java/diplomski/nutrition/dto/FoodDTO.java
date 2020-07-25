package diplomski.nutrition.dto;

import diplomski.nutrition.entity.Food;

public class FoodDTO {

	private Long id;
	private String username;
	private String name;
	private Integer calories;
	private Float totalFat;
	private Float saturatedFat;
	private Float cholesterol;
	private Float sodium;
	private Float totalCarbs;
	private Float fiber;
	private Float sugars;
	private Float protein;
	private Float potasium;
	
	public FoodDTO(Long id, String username, String name, Integer calories, Float totalFat, Float saturatedFat, Float cholesterol,
			Float sodium, Float totalCarbs, Float fiber, Float sugars, Float protein, Float potasium) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.calories = calories;
		this.totalFat = totalFat;
		this.saturatedFat = saturatedFat;
		this.cholesterol = cholesterol;
		this.sodium = sodium;
		this.totalCarbs = totalCarbs;
		this.fiber = fiber;
		this.sugars = sugars;
		this.protein = protein;
		this.potasium = potasium;
	}

	public FoodDTO(Food food) {
		this(food.getId(), food.getUser().getUsername(), food.getName(), food.getTotalCalories(), food.getTotalFat(), food.getSaturatedFat(), 
				food.getCholesterol(), food.getSodium(), food.getTotalCarbs(), food.getFiber(), food.getSugars(), food.getProtein(), food.getPotasium());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
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

	public Float getSodium() {
		return sodium;
	}

	public void setSodium(Float sodium) {
		this.sodium = sodium;
	}

	public Float getTotalCarbs() {
		return totalCarbs;
	}

	public void setTotalCarbs(Float totalCarbs) {
		this.totalCarbs = totalCarbs;
	}

	public Float getFiber() {
		return fiber;
	}

	public void setFiber(Float fiber) {
		this.fiber = fiber;
	}

	public Float getSugars() {
		return sugars;
	}

	public void setSugars(Float sugars) {
		this.sugars = sugars;
	}

	public Float getProtein() {
		return protein;
	}

	public void setProtein(Float protein) {
		this.protein = protein;
	}

	public Float getPotasium() {
		return potasium;
	}

	public void setPotasium(Float potasium) {
		this.potasium = potasium;
	}

}
