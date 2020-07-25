package diplomski.nutrition.dto;

import diplomski.nutrition.entity.DayExercise;

public class DayExerciseDTO {
	
	private Long id;
	private Long exerciseId;
	private String exerciseName;
	private Float time;//in minutes
	private Float caloriesBurned;
	
	public DayExerciseDTO() {
		super();
	}
	public DayExerciseDTO(Long id, Long exerciseId, String exerciseName, Float time, Float caloriesBurned) {
		super();
		this.id = id;
		this.exerciseId = exerciseId;
		this.exerciseName = exerciseName;
		this.time = time;
		this.caloriesBurned = caloriesBurned;
	}
	public DayExerciseDTO(DayExercise e) {
		this(e.getId(), e.getExercise().getId(), e.getExercise().getName(), e.getTime(), e.getCaloriesBurned());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
	}
	public void setExerciseName(String name) {
		this.exerciseName = name;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public Float getTime() {
		return time;
	}
	public void setTime(Float time) {
		this.time = time;
	}
	public Float getCaloriesBurned() {
		return caloriesBurned;
	}
	public void setCaloriesBurned(Float caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

}
