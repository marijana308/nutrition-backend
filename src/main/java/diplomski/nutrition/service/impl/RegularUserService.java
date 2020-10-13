package diplomski.nutrition.service.impl;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.Nutrient;
import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.enumeration.ActivityLevel;
import diplomski.nutrition.enumeration.Gender;
import diplomski.nutrition.repository.NutrientRepository;
import diplomski.nutrition.repository.RegularUserRepository;
import diplomski.nutrition.service.RegularUserServiceInterface;

@Service
public class RegularUserService implements RegularUserServiceInterface{

	@Autowired
	RegularUserRepository regularUserRepository;
	
	@Autowired
	NutrientRepository nutrientRepository;
	
	@Autowired
	NutrientService nutrientService;
	
	private Integer calculateAge(Date birthDate) {
	      int years = 0;
	      int months = 0;
	      //int days = 0;
	 
	      //create calendar object for birth day
	      Calendar birthDay = Calendar.getInstance();
	      birthDay.setTimeInMillis(birthDate.getTime());
	 
	      //create calendar object for current day
	      long currentTime = System.currentTimeMillis();
	      Calendar now = Calendar.getInstance();
	      now.setTimeInMillis(currentTime);
	 
	      //Get difference between years
	      years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
	      int currMonth = now.get(Calendar.MONTH) + 1;
	      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
	 
	      //Get difference between months
	      months = currMonth - birthMonth;
	 
	      //if month difference is in negative then reduce years by one 
	      //and calculate the number of months.
	      if (months < 0)
	      {
	         years--;
	         months = 12 - birthMonth + currMonth;
	         if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	            months--;
	      } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	      {
	         years--;
	         months = 11;
	      }
	 
//	      //Calculate the days
//	      if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
//	         days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
//	      else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
//	      {
//	         int today = now.get(Calendar.DAY_OF_MONTH);
//	         now.add(Calendar.MONTH, -1);
//	         days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
//	      } 
//	      else
//	      {
//	         days = 0;
//	         if (months == 12)
//	         {
//	            years++;
//	            months = 0;
//	         }
//	      }
	      //Create new Age object 
	      return years;
	   }

	private Float calculateBMI(Float height, Float weight) {
		Float heightInMeters = height / 100;
		Float heightSquared = heightInMeters * heightInMeters;
		Float BMI = weight / heightSquared;
		return BMI;	
	}
	
	private Float calculateBMR(Float height, Float weight, Date birthday, Gender gender) {
		Integer age = calculateAge(birthday);
		Float BMR = (float) ((10 * weight) + (6.25 * height) - (5 * age));
		if (gender.equals(Gender.MALE)) {
			BMR = BMR + 5;
		}
		if (gender.equals(Gender.FEMALE)) {
			BMR = BMR - 161;
		}
		return BMR;
	}

	private static long getDayCount(Date start, Date end) {
	  long diff = Math.round((end.getTime() - start.getTime()) / (double) 86400000);
	  return diff;
	}
	
	private Integer calculateDailyCalories(Float BMR, ActivityLevel activityLevel, Float weight, Float goalWeight, Date goalDate) {
		Integer calories = 0;
		Date startDate = new Date();
		Long days = getDayCount(startDate, goalDate);
		Float kg = weight - goalWeight;
		Integer caloriesToBurnOrGain = (int) (kg * 7500);
		Integer dailyCaloriesToBurnOrGain = (int) (caloriesToBurnOrGain / days);
		
		if (activityLevel.equals(ActivityLevel.SEDENTARY)) {
			calories = (int) (BMR * 1.2) - dailyCaloriesToBurnOrGain;
		}
		if (activityLevel.equals(ActivityLevel.LOW)) {
			calories = (int) (BMR * 1.375) - dailyCaloriesToBurnOrGain;
		}
		if (activityLevel.equals(ActivityLevel.ACTIVE)) {
			calories = (int) (BMR * 1.55) - dailyCaloriesToBurnOrGain;
		}
		if (activityLevel.equals(ActivityLevel.VERYACTIVE)) {
			calories = (int) (BMR * 1.725) - dailyCaloriesToBurnOrGain;
		}
		return calories;
	}
	
	@Override
	public RegularUser save(RegularUser regularUser) {
		Float BMI = calculateBMI(regularUser.getHeight(), regularUser.getWeight());
		Float BMR = calculateBMR(regularUser.getHeight(), regularUser.getWeight(), regularUser.getBirthday(), regularUser.getGender());
		Integer calories = calculateDailyCalories(BMR, regularUser.getActivityLevel(), 
				regularUser.getWeight(), regularUser.getGoalWeight(), regularUser.getGoalDate());
		regularUser.setBMI(BMI);
		regularUser.setBMR(BMR);
		regularUser.setDailyCalories(calories);
		regularUser.setDailyNutrients(nutrientService.getNutrients(calories, regularUser));
		regularUser.setStreak(0);
		regularUser.setPoints(0);
		regularUser.setPremium(false);
		regularUserRepository.save(regularUser);
		for(Nutrient n : regularUser.getDailyNutrients()) {
			nutrientRepository.save(n);
		}
		return regularUser;
	}
	
	@Override
	public RegularUser updateAndCalculate(RegularUser regularUser) {
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
//		regularUser.setDailyNutrients(getNutrients(calories, regularUser));
//		for(Nutrient n : regularUser.getDailyNutrients()) {
//			Nutrient nutrient = nutrientService.findById(n.getId());
//			nutrient.setDailyValue(n.getDailyValue());
//			nutrientService.update(nutrient);
//		}
		return regularUserRepository.save(regularUser);
	}
	
	@Override
	public RegularUser update(RegularUser regularUser) {
		return regularUserRepository.save(regularUser);
	}

	@Override
	public RegularUser findById(Long id) {
		return regularUserRepository.findById(id).orElse(null);
	}

}
