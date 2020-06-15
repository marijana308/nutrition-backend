package diplomski.nutrition.dto;

import diplomski.nutrition.entity.Exercise;

public class ExerciseDTO {

	private Long id;
	private String username;
	private String name;
	private Integer caloriesBurned;
	private Integer time;
	
	public ExerciseDTO() {
		super();
	}

	public ExerciseDTO(Long id, String username, String name, Integer caloriesBurned, Integer time) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.caloriesBurned = caloriesBurned;
		this.time = time;
	}

	public ExerciseDTO(Exercise exercise) {
		this(exercise.getId(), exercise.getCreatedByUser().getUsername(), exercise.getName(), exercise.getCaloriesBurned(), exercise.getTime());
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

	public Integer getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(Integer caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	
}
