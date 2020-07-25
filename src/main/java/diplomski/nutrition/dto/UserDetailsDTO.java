package diplomski.nutrition.dto;

import java.util.Date;

import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.enumeration.ActivityLevel;
import diplomski.nutrition.enumeration.Gender;
import diplomski.nutrition.enumeration.Role;

public class UserDetailsDTO {
	
	private String token;
	private Role role;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private Float height;
	private Float weight;
	private Gender gender;
	private Date birthday;
	private ActivityLevel activityLevel;
	private Float goalWeight;
	private Date goalDate;
	private Float BMI;
	private Float BMR;
	private Integer dailyCalories;
	private Integer streak;
	private Integer points;
	private Boolean premium;
	
	public UserDetailsDTO() {
		super();
	}
	
	public UserDetailsDTO(String token, Role role, String username, String password, String firstname, String lastname) {
		super();
		this.token = token;
		this.role = role;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public UserDetailsDTO(String token, Role role, String username, String password, String firstname, String lastname, Float height,
			Float weight, Gender gender, Date birthday, ActivityLevel activityLevel, Float goalWeight, Date goalDate,
			Float bMI, Float bMR, Integer dailyCalories, Integer streak, Integer points, Boolean premium) {
		super();
		this.token = token;
		this.role = role;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
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
	
	public UserDetailsDTO(Role role, String username, String password, String firstname, String lastname, Float height,
			Float weight, Gender gender, Date birthday, ActivityLevel activityLevel, Float goalWeight, Date goalDate,
			Float bMI, Float bMR, Integer dailyCalories, Integer streak, Integer points, Boolean premium) {
		super();
		this.role = role;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
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
	
	public UserDetailsDTO(RegularUser u, String token) {
		this(token, u.getRole(), u.getUsername(), u.getPassword(), u.getFirstname(), u.getLastname(), u.getHeight(), u.getWeight(), u.getGender(),
				u.getBirthday(), u.getActivityLevel(), u.getGoalWeight(), u.getGoalDate(), u.getBMI(), u.getBMR(),
				u.getDailyCalories(), u.getStreak(), u.getPoints(), u.getPremium());
	}
	
	public UserDetailsDTO(RegularUser u) {
		this(u.getRole(), u.getUsername(), u.getPassword(), u.getFirstname(), u.getLastname(), u.getHeight(), u.getWeight(), u.getGender(),
				u.getBirthday(), u.getActivityLevel(), u.getGoalWeight(), u.getGoalDate(), u.getBMI(), u.getBMR(),
				u.getDailyCalories(), u.getStreak(), u.getPoints(), u.getPremium());
	}
	
	public UserDetailsDTO(User u, String token) {
		this(token, u.getRole(), u.getUsername(), u.getPassword(), u.getFirstname(), u.getLastname());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
		BMI = bMI;
	}

	public Float getBMR() {
		return BMR;
	}

	public void setBMR(Float bMR) {
		BMR = bMR;
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
