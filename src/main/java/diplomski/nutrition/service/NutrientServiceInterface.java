package diplomski.nutrition.service;

import java.util.List;

import diplomski.nutrition.entity.Nutrient;

public interface NutrientServiceInterface {

	 List<Nutrient> findNutrientsByUserId(Long userID);
	 
	 Nutrient findById(Long id);
	 
	 Nutrient update(Nutrient nutrient);
	 
}
