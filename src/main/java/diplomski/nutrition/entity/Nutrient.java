package diplomski.nutrition.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Nutrient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)//cascade = CascadeType.REFRESH, 
	private RegularUser user;
	
	private String name;
	
	private Float dailyValue;
	
	private String measure;
	
	public Nutrient() {
		super();
	}
	
	public Nutrient(RegularUser user, String name, Float dailyValue, String measure) {
		super();
		this.user = user;
		this.name = name;
		this.dailyValue = dailyValue;
		this.measure = measure;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegularUser getUser() {
		return user;
	}

	public void setUser(RegularUser user) {
		this.user = user;
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
