package diplomski.nutrition.dto;

import diplomski.nutrition.entity.Nutrient;

public class NutrientDTO {
	
	private Long id;
	private String username;
	private String name;
	private Float dailyValue;
	private String measure;
	
	public NutrientDTO() {
		super();
	}
	public NutrientDTO(Long id, String username, String name, Float dailyValue, String measure) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.dailyValue = dailyValue;
		this.measure = measure;
	}
	
	public NutrientDTO(Nutrient nutrient) {
		this(nutrient.getId(), nutrient.getUser().getUsername(), nutrient.getName(), nutrient.getDailyValue(), nutrient.getMeasure());
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
	public Float getDailyValue() {
		return dailyValue;
	}
	public void setDailyValue(Float dailyValue) {
		this.dailyValue = dailyValue;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}

}
