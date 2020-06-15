package diplomski.nutrition.dto;

import diplomski.nutrition.entity.Food;

public class FoodDTO {

	private Long id;
	private String username;
	private String name;
	private Integer calories;
//	private String servingSize;
//	private Float servingSizeQuantity;
	private Float totalFat;
	private Float saturatedFat;
	private Float cholesterol;
	private Float sodium;
	private Float totalCarbs;
	private Float fiber;
	private Float sugars;
	private Float protein;
	private Float vitaminA;
	private Float vitaminC;
	private Float vitaminD;
	private Float calcium;
	private Float iron;
	private Float phosphorus;
	private Float potasium;
	
	public FoodDTO(Long id, String username, String name, Integer calories, Float totalFat, Float saturatedFat, Float cholesterol,
			Float sodium, Float totalCarbs, Float fiber, Float sugars, Float protein, Float vitaminA, Float vitaminC,
			Float vitaminD, Float calcium, Float iron, Float phosphorus, Float potasium) {
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
		this.vitaminA = vitaminA;
		this.vitaminC = vitaminC;
		this.vitaminD = vitaminD;
		this.calcium = calcium;
		this.iron = iron;
		this.phosphorus = phosphorus;
		this.potasium = potasium;
	}

	public FoodDTO(Food food) {
		this(food.getId(), food.getUser().getUsername(), food.getName(), food.getTotalCalories(), food.getTotalFat(), food.getSaturatedFat(), 
				food.getCholesterol(), food.getSodium(), food.getTotalCarbs(), food.getFiber(), food.getSugars(), food.getProtein(), 
				food.getVitaminA(), food.getVitaminC(), food.getVitaminD(), food.getCalcium(), food.getIron(), 
				food.getPhosphorus(), food.getPotasium());
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

//	public String getServingSize() {
//		return servingSize;
//	}
//
//	public void setServingSize(String servingSize) {
//		this.servingSize = servingSize;
//	}
//
//	public Float getServingSizeQuantity() {
//		return servingSizeQuantity;
//	}
//
//	public void setServingSizeQuantity(Float servingSizeQuantity) {
//		this.servingSizeQuantity = servingSizeQuantity;
//	}

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

	public Float getVitaminA() {
		return vitaminA;
	}

	public void setVitaminA(Float vitaminA) {
		this.vitaminA = vitaminA;
	}

	public Float getVitaminC() {
		return vitaminC;
	}

	public void setVitaminC(Float vitaminC) {
		this.vitaminC = vitaminC;
	}

	public Float getVitaminD() {
		return vitaminD;
	}

	public void setVitaminD(Float vitaminD) {
		this.vitaminD = vitaminD;
	}

	public Float getCalcium() {
		return calcium;
	}

	public void setCalcium(Float calcium) {
		this.calcium = calcium;
	}

	public Float getIron() {
		return iron;
	}

	public void setIron(Float iron) {
		this.iron = iron;
	}

	public Float getPhosphorus() {
		return phosphorus;
	}

	public void setPhosphorus(Float phosphorus) {
		this.phosphorus = phosphorus;
	}

	public Float getPotasium() {
		return potasium;
	}

	public void setPotasium(Float potasium) {
		this.potasium = potasium;
	}

}
