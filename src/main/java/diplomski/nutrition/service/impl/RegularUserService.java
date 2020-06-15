package diplomski.nutrition.service.impl;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.enumeration.ActivityLevel;
import diplomski.nutrition.enumeration.Gender;
import diplomski.nutrition.repository.RegularUserRepository;
import diplomski.nutrition.service.RegularUserServiceInterface;

@Service
public class RegularUserService implements RegularUserServiceInterface{

	@Autowired
	RegularUserRepository regularUserRepository;

	public Float calculateBMI(Float height, Float weight) {
//		System.out.println("calculate BMI, height: " + height);
//		System.out.println("calculate BMI, weight: " + weight);
		Float heightInMeters = height / 100;
		Float heightSquared = heightInMeters * heightInMeters;
		Float BMI = weight / heightSquared;
		return BMI;	
	}
	
	@SuppressWarnings("deprecation")
	public Float calculateBMR(Float height, Float weight, Date birthday, Gender gender) {
//		System.out.println("calculate BMR, height: " + height);
//		System.out.println("calculate BMR, weight: " + weight);
//		System.out.println("calculate BMR, birthday:" + birthday);
//		System.out.println("calculate BMR, gender: " + gender);
		long ageInMillis = new Date().getTime() - birthday.getTime();
	    Date ageDate = new Date(ageInMillis);
	    Integer age =  ageDate.getYear();
		Float BMR = (float) ((10 * weight) + (6.25 * height) - (5 * age));
		if (gender.equals(Gender.MALE)) {
			BMR = BMR + 5;
		}
		if (gender.equals(Gender.FEMALE)) {
			BMR = BMR - 161;
		}
		return BMR;
	}

	public static long getDayCount(Date start, Date end) {
	  long diff = Math.round((end.getTime() - start.getTime()) / (double) 86400000);
	  return diff;
	}
	
	public Integer calculateDailyCalories(Float BMR, ActivityLevel activityLevel, Float weight, Float goalWeight, Date goalDate) {
		Integer calories = 0;
		Date startDate = new Date();
		Long days = getDayCount(startDate, goalDate);
		Float kg = weight - goalWeight;
		Integer caloriesToBurnOrGain = (int) (kg * 6000);
		Integer dailyCaloriesToBurnOrGain = (int) (caloriesToBurnOrGain / days);
		
		if (activityLevel.equals(ActivityLevel.SEDENTARY)) {
			calories = (int) (BMR * 1.2) - dailyCaloriesToBurnOrGain;
		}
		if (activityLevel.equals(ActivityLevel.LOW)) {
			calories = (int) (BMR * 1.3) - dailyCaloriesToBurnOrGain;
		}
		if (activityLevel.equals(ActivityLevel.ACTIVE)) {
			calories = (int) (BMR * 1.4) - dailyCaloriesToBurnOrGain;
		}
		if (activityLevel.equals(ActivityLevel.VERYACTIVE)) {
			calories = (int) (BMR * 1.5) - dailyCaloriesToBurnOrGain;
		}
		return calories;
	}
	
	@Override
	public RegularUser save(RegularUser regularUser) {
		Float BMI = calculateBMI(regularUser.getHeight(), regularUser.getWeight());
		System.out.println("save, BMI: " + BMI);
		Float BMR = calculateBMR(regularUser.getHeight(), regularUser.getWeight(), regularUser.getBirthday(), regularUser.getGender());
		System.out.println("save, BMR: " + BMR);
		Integer calories = calculateDailyCalories(BMR, regularUser.getActivityLevel(), 
				regularUser.getWeight(), regularUser.getGoalWeight(), regularUser.getGoalDate());
		System.out.println("save, calories:" + calories);
		regularUser.setBMI(BMI);
		regularUser.setBMR(BMR);
		regularUser.setDailyCalories(calories);
		regularUser.setStreak(0);
		regularUser.setPoints(0);
		regularUser.setPremium(false);
		return regularUserRepository.save(regularUser);
	}
	
	@Override
	public RegularUser update(RegularUser regularUser) {
		Float BMI = calculateBMI(regularUser.getHeight(), regularUser.getWeight());
		System.out.println("update, BMI: " + BMI);
		Float BMR = calculateBMR(regularUser.getHeight(), regularUser.getWeight(), regularUser.getBirthday(), regularUser.getGender());
		System.out.println("update, BMR: " + BMR);
		Integer calories = calculateDailyCalories(BMR, regularUser.getActivityLevel(), 
				regularUser.getWeight(), regularUser.getGoalWeight(), regularUser.getGoalDate());
		System.out.println("update, calories:" + calories);
		regularUser.setBMI(BMI);
		regularUser.setBMR(BMR);
		regularUser.setDailyCalories(calories);
//		regularUser.setStreak(0);
//		regularUser.setPoints(0);
//		regularUser.setPremium(false);
		return regularUserRepository.save(regularUser);
	}

	@Override
	public RegularUser findById(Long id) {
		return regularUserRepository.findById(id).orElse(null);
	}
}
