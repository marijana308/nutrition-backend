package diplomski.nutrition.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.Nutrient;
import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.repository.NutrientRepository;
import diplomski.nutrition.service.NutrientServiceInterface;

@Service
public class NutrientService implements NutrientServiceInterface{
	
	@Autowired
	NutrientRepository nutrientRepository;

	@Override
	public List<Nutrient> findNutrientsByUserId(Long userID) {
		List<Nutrient> nutrients = new ArrayList<Nutrient>();
		for(Nutrient n : nutrientRepository.findAll()) {
			if(n.getUser().getId().equals(userID)) {
				nutrients.add(n);
			}
		}
		return nutrients;
	}

	@Override
	public Nutrient findById(Long id) {
		return nutrientRepository.findById(id).orElse(null);
	}

	@Override
	public Nutrient update(Nutrient nutrient) {
		return nutrientRepository.save(nutrient);
	}
	
	public Float calculateDailyValue(Integer calories, Integer value) {
		//formula for calculating users required daily intake for a nutrient, based on users daily calories
		//"value" is the required daily intake of a nutrient for a standard 2000kcal diet
		Float dailyValue = (float) (calories * value / 2000);
		return dailyValue;
	}
	
	public Set<Nutrient> getNutrients(Integer calories, RegularUser user){
		Set<Nutrient> nutrients = new HashSet<Nutrient>();
		nutrients.add(new Nutrient(user, "totalFat", calculateDailyValue(calories, 65), "g"));
		nutrients.add(new Nutrient(user, "saturatedFat", calculateDailyValue(calories, 20), "g"));
		nutrients.add(new Nutrient(user, "cholesterol", calculateDailyValue(calories, 300), "mg"));
		nutrients.add(new Nutrient(user, "sodium", calculateDailyValue(calories, 2400), "mg"));
		nutrients.add(new Nutrient(user, "totalCarbs", calculateDailyValue(calories, 300), "g"));
		nutrients.add(new Nutrient(user, "fiber", calculateDailyValue(calories, 25), "g"));
		nutrients.add(new Nutrient(user, "sugars", calculateDailyValue(calories, 30), "g"));
		nutrients.add(new Nutrient(user, "protein", calculateDailyValue(calories, 50), "g"));
		nutrients.add(new Nutrient(user, "potasium", calculateDailyValue(calories, 4700), "mg"));
//		nutrients.add(new Nutrient(user, "vitaminA", calculateDailyValue(calories, 5000), "iu"));
//		nutrients.add(new Nutrient(user, "vitaminC", calculateDailyValue(calories, 60), "mg"));
//		nutrients.add(new Nutrient(user, "vitaminD", calculateDailyValue(calories, 400), "iu"));
//		nutrients.add(new Nutrient(user, "calcium", calculateDailyValue(calories, 1000), "mg"));
//		nutrients.add(new Nutrient(user, "iron", calculateDailyValue(calories, 18), "mg"));
//		nutrients.add(new Nutrient(user, "phosphorus", calculateDailyValue(calories, 1000), "mg"));
		return nutrients;
	}

	public Nutrient updateDailyValue(Long id, Integer calories) {
		Nutrient n = nutrientRepository.findById(id).orElse(null);
		if(n.getName().equals("totalFat")) {
			n.setDailyValue(calculateDailyValue(calories, 65));
		}
		if(n.getName().equals("saturatedFat")) {
			n.setDailyValue(calculateDailyValue(calories, 20));
		}
		if(n.getName().equals("cholesterol")) {
			n.setDailyValue(calculateDailyValue(calories, 300));
		}
		if(n.getName().equals("sodium")) {
			n.setDailyValue(calculateDailyValue(calories, 2400));
		}
		if(n.getName().equals("totalCarbs")) {
			n.setDailyValue(calculateDailyValue(calories, 300));
		}
		if(n.getName().equals("fiber")) {
			n.setDailyValue(calculateDailyValue(calories, 25));
		}
		if(n.getName().equals("sugars")) {
			n.setDailyValue(calculateDailyValue(calories, 30));
		}
		if(n.getName().equals("protein")) {
			n.setDailyValue(calculateDailyValue(calories, 50));
		}
		if(n.getName().equals("potasium")) {
			n.setDailyValue(calculateDailyValue(calories, 4700));
		}
//		if(n.getName().equals("vitaminA")) {
//			n.setDailyValue(calculateDailyValue(calories, 5000));
//		}
//		if(n.getName().equals("vitaminC")) {
//			n.setDailyValue(calculateDailyValue(calories, 60));
//		}
//		if(n.getName().equals("vitaminD")) {
//			n.setDailyValue(calculateDailyValue(calories, 400));
//		}
//		if(n.getName().equals("calcium")) {
//			n.setDailyValue(calculateDailyValue(calories, 1000));
//		}
//		if(n.getName().equals("iron")) {
//			n.setDailyValue(calculateDailyValue(calories, 18));
//		}
//		if(n.getName().equals("phosphorus")) {
//			n.setDailyValue(calculateDailyValue(calories, 1000));
//		}
		return nutrientRepository.save(n);
	}
}
