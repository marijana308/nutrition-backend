package diplomski.nutrition.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import diplomski.nutrition.enumeration.ActivityLevel;
import diplomski.nutrition.enumeration.Gender;

@Entity
public class RegularUser extends User{

	@Column(name="height", unique=false, nullable=false)
	private Float height;
	
	@Column(name="weight", unique=false, nullable=false)
	private Float weight;
	
	@Column(name="gender", unique=false, nullable=false)
	private Gender gender;
	
	@Column(name="birthday", unique=false, nullable=false)
	private Date birthday;
	
	@Enumerated(EnumType.STRING)
	private ActivityLevel activityLevel;
	
	@Column(name="goal_weight", unique=false, nullable=true)
	private Float goalWeight;
	
	@Column(name="goal_date", unique=false, nullable=true)
	private Date goalDate;
	
	@Column(name="BMI", unique=false, nullable=true)
	private Float BMI;
	
	@Column(name="BMR", unique=false, nullable=true)
	private Float BMR;
	
	@Column(name="daily_calories", unique=false, nullable=true)
	private Integer dailyCalories;
	
	@Column(name="streak", unique=false, nullable=false)
	private Integer streak;
	
	@Column(name="points", unique=false, nullable=false)
	private Integer points;
	
	@Column(name="premium", unique=false, nullable=false)
	private Boolean premium;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Day> days = new HashSet<>();
	
//	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	private Set<Meal> meals = new HashSet<Meal>();
//	
//	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	private Set<DayExercise> exercises = new HashSet<DayExercise>();
	
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	private Set<NutritionixExerciseUser> nutritionixExercises = new HashSet<NutritionixExerciseUser>();
	
//	@Column(name="total_water_intake", unique=false, nullable=true)
//	private WaterIntake totalWaterIntake;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Recipe> recipes = new HashSet<Recipe>();
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Food> createdFoods = new HashSet<Food>();
	
	@OneToMany(mappedBy="createdByUser", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Exercise> createdExercises = new HashSet<Exercise>();

	public RegularUser() {
		super();
	}

	public RegularUser(Float height, Float weight, Gender gender, Date birthday, ActivityLevel activityLevel,
			Float goalWeight, Date goalDate, Float bMI, Float bMR, Integer dailyCalories, Integer streak,
			Integer points, Boolean premium) {
		super();
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.birthday = birthday;
		this.activityLevel = activityLevel;
		this.goalWeight = goalWeight;
		this.goalDate = goalDate;
		this.BMI = bMI;
		this.BMR = bMR;
		this.dailyCalories = dailyCalories;
		this.streak = streak;
		this.points = points;
		this.premium = premium;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public ActivityLevel getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(ActivityLevel activityLevel) {
		this.activityLevel = activityLevel;
	}

	public Float getGoalWeight() {
		return goalWeight;
	}

	public void setGoalWeight(Float goalWeight) {
		this.goalWeight = goalWeight;
	}

	public Date getGoalDate() {
		return goalDate;
	}

	public void setGoalDate(Date goalDate) {
		this.goalDate = goalDate;
	}

	public Float getBMI() {
		return BMI;
	}

	public void setBMI(Float bMI) {
		this.BMI = bMI;
	}

	public Float getBMR() {
		return BMR;
	}

	public void setBMR(Float bMR) {
		this.BMR = bMR;
	}

	public Integer getDailyCalories() {
		return dailyCalories;
	}

	public void setDailyCalories(Integer dailyCalories) {
		this.dailyCalories = dailyCalories;
	}

	public Integer getStreak() {
		return streak;
	}

	public void setStreak(Integer streak) {
		this.streak = streak;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Boolean getPremium() {
		return premium;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
	}
	
}
