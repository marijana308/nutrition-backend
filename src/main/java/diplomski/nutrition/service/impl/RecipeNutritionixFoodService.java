package diplomski.nutrition.service.impl;

import java.util.HashSet;
import java.util.Set;

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

	@Override
	public RecipeNutritionixFood findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Set<RecipeNutritionixFood> findFoodsByRecipeId(Long recipeid) {
		Set<RecipeNutritionixFood> foods = new HashSet<RecipeNutritionixFood>();
		for(RecipeNutritionixFood rnf: repository.findAll()) {
			if(rnf.getRecipe().getId() == recipeid) {
				foods.add(rnf);
			}
		}
		return foods;
	}

}
