package diplomski.nutrition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.RecipeNutritionixFood;
import diplomski.nutrition.repository.RecipeNutritionixFoodRepository;
import diplomski.nutrition.service.RecipeNutritionixFoodServiceInterface;

@Service
public class RecipeNutritionixFoodService implements RecipeNutritionixFoodServiceInterface{

	@Autowired
	RecipeNutritionixFoodRepository repository;
	
	@Override
	public RecipeNutritionixFood save(RecipeNutritionixFood rnf) {
		return repository.save(rnf);
	}

}
