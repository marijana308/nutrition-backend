package diplomski.nutrition.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NutritionixExerciseDay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Day day;
	
	//query for the nutritionix API that is sent in body 
	//https://trackapi.nutritionix.com/v2/natural/exercise
	private String exerciseQuery;

	public NutritionixExerciseDay() {
		super();
	}

	public NutritionixExerciseDay(Long id, Day day, String exerciseQuery) {
		super();
		this.id = id;
		this.day = day;
		this.exerciseQuery = exerciseQuery;
	}

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

	public String getExerciseQuery() {
		return exerciseQuery;
	}

	public void setExerciseQuery(String exerciseQuery) {
		this.exerciseQuery = exerciseQuery;
	}
}
