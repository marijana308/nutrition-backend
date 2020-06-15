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
public class Exercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User createdByUser;
	
	@OneToMany(mappedBy="exercise", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<DayExercise> days = new HashSet<DayExercise>();
	
	private String name;
	
	private Integer caloriesBurned;
	
	private Integer time;

	public Exercise() {
		super();
	}

	public Exercise(Long id, User createdByUser, String name, Integer caloriesBurned, Integer time) {
		super();
		this.id = id;
		this.createdByUser = createdByUser;
		this.name = name;
		this.caloriesBurned = caloriesBurned;
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
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
