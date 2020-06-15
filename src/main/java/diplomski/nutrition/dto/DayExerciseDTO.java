package diplomski.nutrition.dto;

public class DayExerciseDTO {
	
	private Long exerciseID;
	private Float time;//in minutes
	
	public DayExerciseDTO() {
		super();
	}
	public DayExerciseDTO(Long exerciseID, Float time) {
		super();
		this.exerciseID = exerciseID;
		this.time = time;
	}
	public Long getExerciseID() {
		return exerciseID;
	}
	public void setExerciseID(Long exerciseID) {
		this.exerciseID = exerciseID;
	}
	public Float getTime() {
		return time;
	}
	public void setTime(Float time) {
		this.time = time;
	}

}
