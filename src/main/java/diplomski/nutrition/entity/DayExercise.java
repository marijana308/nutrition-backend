package diplomski.nutrition.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import diplomski.nutrition.dto.ExerciseDTO;

@Entity
public class DayExercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Day day;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Exercise exercise;
	
	private Float time;
	
	private Float caloriesBurned;

	public DayExercise() {
		super();
	}

	public DayExercise(Long id, Day day, Exercise exercise, Float time, Float caloriesBurned) {
		super();
		this.id = id;
		this.day = day;
		this.exercise = exercise;
		this.time = time;
		this.caloriesBurned = caloriesBurned;
	}
	
//	public ExerciseDTO toDayExerciseDTO() {
//		return new ExerciseDTO(this.exercise.getName(), this.time);
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
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
