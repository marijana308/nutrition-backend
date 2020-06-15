package diplomski.nutrition.dto;

import java.util.Date;

import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.enumeration.ActivityLevel;
import diplomski.nutrition.enumeration.Gender;

public class UserDTO {

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
	
	public UserDTO(String username, String password, String firstname, String lastname, Float height, Float weight,
			Gender gender, Date birthday, ActivityLevel activityLevel, Float goalWeight, Date goalDate) {
		super();
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
	}

	public UserDTO(RegularUser user) {
		this(user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getHeight(), user.getWeight(),
				user.getGender(), user.getBirthday(), user.getActivityLevel(), user.getGoalWeight(), user.getGoalDate());
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
}
