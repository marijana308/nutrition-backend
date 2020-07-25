package diplomski.nutrition.dto;

import diplomski.nutrition.entity.NutritionixExerciseDay;

public class DayNutritionixExerciseDTO {

	private Long id;
	private String exerciseQuery;
	
	public DayNutritionixExerciseDTO() {
		super();
	}
	public DayNutritionixExerciseDTO(Long id, String exerciseQuery) {
		super();
		this.id = id;
		this.exerciseQuery = exerciseQuery;
	}
	public DayNutritionixExerciseDTO(NutritionixExerciseDay e) {
		this(e.getId(), e.getExerciseQuery());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getExerciseQuery() {
		return exerciseQuery;
	}
	public void setExerciseQuery(String exerciseQuery) {
		this.exerciseQuery = exerciseQuery;
	}
}
